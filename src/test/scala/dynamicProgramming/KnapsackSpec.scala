package dynamicProgramming

import org.scalatest.FunSuite

import scala.collection.mutable.ListBuffer

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
          items: List[Item],
          subBags: List[Int]
      ): List[List[Cell]] = {

    val emptyCell = Cell(List())
    var matrix = ListBuffer[List[Cell]]() // List is immutable!!!!!
    for(item <- items) {
      matrix += List.fill(subBags.size)(emptyCell)
    }
    matrix.toList
  }

  def findCell(
          matrix: List[List[Cell]],
          position: Map[Int, Int])
      : Cell = {
    val itemName = position.keys.head
    val cellColumn = position.values.head
    matrix(itemName)(cellColumn)
  }

  test("init matrix") {
    val emptyCell = Cell(List())
    val givenGuitar = Item("guitar", 1, 1500)
    val givenStereo = Item("stereo", 4, 3000)
    val givenLaptop = Item("laptop", 3, 2000)
    val matrix = initMatrix(
      List(givenGuitar, givenStereo, givenLaptop),
      List(1,2,3,4)
    )

    assert(matrix === List(
      List(emptyCell,emptyCell,emptyCell,emptyCell),
      List(emptyCell,emptyCell,emptyCell,emptyCell),
      List(emptyCell,emptyCell,emptyCell,emptyCell)
    ))
  }

  test("can extract an specific cell") {

    val emptyCell = Cell(List())
    val givenGuitar = Item("guitar", 1, 1500)
    val givenStereo = Item("stereo", 4, 3000)
    val givenMatrix = List(
      List(emptyCell,Cell(List(givenGuitar)),   emptyCell,                            emptyCell),
      List(emptyCell,emptyCell,                 Cell(List(givenGuitar, givenStereo)), emptyCell),
      List(emptyCell,emptyCell,                 emptyCell,                            emptyCell)
    )

    assert(
      Cell(List(givenGuitar, givenStereo)) === findCell(givenMatrix, Map(1 -> 2))
    )

    assert(
      Cell(List(givenGuitar)) === findCell(givenMatrix, Map(0 -> 1))
    )

    assert(
      Cell(List()) === findCell(givenMatrix, Map(2 -> 1))
    )
  }


}
