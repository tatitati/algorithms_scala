package dynamicProgramming

import org.scalatest.FunSuite

import scala.collection.mutable.ListBuffer

class KnapsackSpec extends FunSuite {

  case class Item(
                   val name: String,
                   val weight: Int,
                   val value: Int
  )
  case class Cell(val items: ListBuffer[Item]) {
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
      ): ListBuffer[ListBuffer[Cell]] = {

    val emptyCell = Cell(ListBuffer())
    var matrix = ListBuffer[ListBuffer[Cell]]() // List is immutable!!!!!
    for(item <- items) {
      matrix += ListBuffer.fill(subBags.size)(emptyCell)
    }
    matrix
  }

  def findCell(
          matrix: ListBuffer[ListBuffer[Cell]],
          position: Map[Int, Int])
      : Cell = {
    val rowIndex = position.keys.head
    val columnIndex = position.values.head
    matrix(rowIndex)(columnIndex)
  }

  def updateCell(
          cell: Cell,
          matrix: ListBuffer[ListBuffer[Cell]],
          position: Map[Int, Int]
      ): ListBuffer[ListBuffer[Cell]] = {
    val rowIndex = position.keys.head
    val columnIndex = position.values.head

    val row = matrix(rowIndex)
    val updatedRow = row.updated(columnIndex, cell)
    val matrixUpdated = matrix.updated(rowIndex, updatedRow)
    matrixUpdated
  }

  test("init matrix") {
    val emptyCell = Cell(ListBuffer())
    val givenGuitar = Item("guitar", 1, 1500)
    val givenStereo = Item("stereo", 4, 3000)
    val givenLaptop = Item("laptop", 3, 2000)
    val matrix = initMatrix(
      List(givenGuitar, givenStereo, givenLaptop),
      List(1,2,3,4)
    )

    assert(matrix === ListBuffer(
      ListBuffer(emptyCell,emptyCell,emptyCell,emptyCell),
      ListBuffer(emptyCell,emptyCell,emptyCell,emptyCell),
      ListBuffer(emptyCell,emptyCell,emptyCell,emptyCell)
    ))
  }

  test("can extract an specific cell") {

    val emptyCell = Cell(ListBuffer())
    val givenGuitar = Item("guitar", 1, 1500)
    val givenStereo = Item("stereo", 4, 3000)
    val givenMatrix = ListBuffer(
      ListBuffer(emptyCell,Cell(ListBuffer(givenGuitar)), emptyCell,                                  emptyCell),
      ListBuffer(emptyCell,emptyCell,                     Cell(ListBuffer(givenGuitar, givenStereo)), emptyCell),
      ListBuffer(emptyCell,emptyCell,                     emptyCell,                                  emptyCell)
    )

    assert(
      Cell(ListBuffer(givenGuitar)) === findCell(givenMatrix, Map(0 -> 1))
    )

    assert(
      Cell(ListBuffer(givenGuitar, givenStereo)) === findCell(givenMatrix, Map(1 -> 2))
    )

    assert(
      Cell(ListBuffer()) === findCell(givenMatrix, Map(2 -> 1))
    )
  }

  test("can update an specific cell") {

    val emptyCell = Cell(ListBuffer())
    val givenMatrix = ListBuffer(
      ListBuffer(emptyCell,emptyCell,emptyCell,emptyCell),
      ListBuffer(emptyCell,emptyCell,emptyCell,emptyCell),
      ListBuffer(emptyCell,emptyCell,emptyCell,emptyCell)
    )

    val withGitar = Item("guitar", 1, 1500)
    val withStereo = Item("stereo", 4, 3000)
    val withCell = Cell(ListBuffer(withGitar, withStereo))
    val matrixUpdated = updateCell(withCell, givenMatrix, Map(1->2))

    assert(
      findCell(matrixUpdated, Map(1->2)) === withCell
    )
  }

}
