package dynamicProgramming

import org.scalatest.FunSuite

import scala.collection.mutable.ListBuffer

class KnapsackSpec extends FunSuite {

  case class Item(
                   val name: String,
                   val weight: Int,
                   val value: Int
  )
  case class Cell(val items: ListBuffer[Item], limitWeight: Int) {
    def getTotalValue(): Int = {
        var value = 0
        for(item <- items) {
          value += item.value
        }
        value
      }

    def usedWeight(): Int = {
        var weight = 0
        for(item <- items) {
          weight += item.weight
        }
        weight
    }

    private def unusedWeight(): Int = {
      limitWeight - usedWeight()
    }

    def itemFits(item: Item): Boolean = {
      unusedWeight() >= item.weight
    }
  }

  def initMatrix(
          items: List[Item],
          subBags: List[Int]
      ): ListBuffer[ListBuffer[Cell]] = {
    var matrix = ListBuffer[ListBuffer[Cell]]() // List is immutable!!!!!
    for(item <- items) {
      var row = ListBuffer[Cell]()
      for(i <- 1 to subBags.size) {
        val emptyCell = Cell(ListBuffer(), limitWeight = i)
        row += emptyCell
      }

      matrix += row
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

    val givenGuitar = Item("guitar", 1, 1500)
    val givenStereo = Item("stereo", 4, 3000)
    val givenLaptop = Item("laptop", 3, 2000)
    val matrix = initMatrix(
      List(givenGuitar, givenStereo, givenLaptop),
      List(1,2,3,4)
    )

    assert(matrix === ListBuffer(
      ListBuffer(Cell(ListBuffer(), 1),Cell(ListBuffer(), 2),Cell(ListBuffer(), 3),Cell(ListBuffer(), 4)),
      ListBuffer(Cell(ListBuffer(), 1),Cell(ListBuffer(), 2),Cell(ListBuffer(), 3),Cell(ListBuffer(), 4)),
      ListBuffer(Cell(ListBuffer(), 1),Cell(ListBuffer(), 2),Cell(ListBuffer(), 3),Cell(ListBuffer(), 4))
    ))
  }

  test("can extract an specific cell") {
    val givenGuitar = Item("guitar", 1, 1500)
    val givenStereo = Item("stereo", 4, 3000)
    val givenMatrix = ListBuffer(
      ListBuffer(Cell(ListBuffer(), 1),Cell(ListBuffer(givenGuitar), 2), Cell(ListBuffer(), 3),                         Cell(ListBuffer(), 4)),
      ListBuffer(Cell(ListBuffer(), 1),Cell(ListBuffer(), 2),            Cell(ListBuffer(givenGuitar, givenStereo), 3), Cell(ListBuffer(), 4)),
      ListBuffer(Cell(ListBuffer(), 1),Cell(ListBuffer(), 2),            Cell(ListBuffer(), 3),                         Cell(ListBuffer(), 4))
    )

    assert(
      Cell(ListBuffer(givenGuitar), 2) === findCell(givenMatrix, Map(0 -> 1))
    )

    assert(
      Cell(ListBuffer(givenGuitar, givenStereo), 3) === findCell(givenMatrix, Map(1 -> 2))
    )

    assert(
      Cell(ListBuffer(), 2) === findCell(givenMatrix, Map(2 -> 1))
    )
  }

  test("can update an specific cell") {
    val givenMatrix = ListBuffer(
      ListBuffer(Cell(ListBuffer(), 1),Cell(ListBuffer(), 2),Cell(ListBuffer(), 3),Cell(ListBuffer(), 4)),
      ListBuffer(Cell(ListBuffer(), 1),Cell(ListBuffer(), 2),Cell(ListBuffer(), 3),Cell(ListBuffer(), 4)),
      ListBuffer(Cell(ListBuffer(), 1),Cell(ListBuffer(), 2),Cell(ListBuffer(), 3),Cell(ListBuffer(), 4))
    )

    val withGitar = Item("guitar", 1, 1500)
    val withStereo = Item("stereo", 4, 3000)
    val withCell = Cell(ListBuffer(withGitar, withStereo), 2)
    val matrixUpdated = updateCell(withCell, givenMatrix, Map(1->2))

    assert(
      findCell(matrixUpdated, Map(1->2)) === withCell
    )
  }

}
