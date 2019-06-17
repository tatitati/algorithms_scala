package dynamicProgramming

import org.scalatest.FunSuite

class KnapsackSpec extends FunSuite {

  case class Item(
                   val name: String,
                   val weight: Int,
                   val value: Int
  )
  case class Cell(val items: List[Item]) {
    def getTotalValue(): Int = {
        var value = 0
        for(item <- items) {
          value += item.value
        }
        value
      }

    def getTotalWeight(): Int = {
        var weight = 0
        for(item <- items) {
          weight += item.weight
        }
        weight
    }
  }

  def initMatrix(
          nameItems: List[String],
          subBags: List[Int]
      ): Map[String, List[Cell]] = {

    val emptyCell = Cell(List())
    var matrix = Map[String, List[Cell]]()
    for(nameItem <- nameItems) {
      matrix += nameItem -> List.fill(subBags.size)(emptyCell)
    }
    matrix
  }

  def getCell(
          matrix: Map[String, List[Cell]],
          position: Map[String, Int])
      : Cell = {
    val itemName = position.keys.head
    val cellColumn = position.values.head
    matrix(itemName)(cellColumn)
  }

  test("init matrix") {
    val emptyCell = Cell(List())
    val matrix = initMatrix(
      List("guitar", "stereo", "laptop"),
      List(1,2,3,4)
    )

    assert(matrix === Map(
      "guitar" -> List(emptyCell,emptyCell,emptyCell,emptyCell),
      "stereo" -> List(emptyCell,emptyCell,emptyCell,emptyCell),
      "laptop" -> List(emptyCell,emptyCell,emptyCell,emptyCell)
    ))
  }

  test("can extract an specific cell") {

    val emptyCell = Cell(List())
    val givenGuitar = Item("guitar", 1, 1500)
    val givenStereo = Item("stereo", 4, 3000)
    val givenMatrix = Map(
      "guitar" -> List(emptyCell,Cell(List(givenGuitar)),   emptyCell,                            emptyCell),
      "stereo" -> List(emptyCell,emptyCell,                 Cell(List(givenGuitar, givenStereo)), emptyCell),
      "laptop" -> List(emptyCell,emptyCell,                 emptyCell,                            emptyCell)
    )

    assert(
      Cell(List(givenGuitar, givenStereo)) === getCell(givenMatrix, Map("stereo" -> 2))
    )

    assert(
      Cell(List(givenGuitar)) === getCell(givenMatrix, Map("guitar" -> 1))
    )

    assert(
      Cell(List()) === getCell(givenMatrix, Map("laptop" -> 1))
    )
  }


}
