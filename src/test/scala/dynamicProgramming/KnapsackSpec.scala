package dynamicProgramming

import org.scalatest.FunSuite

import scala.collection.mutable.ListBuffer

class KnapsackSpec extends FunSuite {

  case class Item(val name: String, val weight: Int, val value: Int)

  object Cell {
    def apply(items: ListBuffer[Item], limitWeight: Int): Cell = {
      new Cell(items, limitWeight)
    }
  }

  class Cell() {
    private var usedWeight: Int = 0
    private var limitWeight: Int = 0
    private var totalValue: Int = 0
    private var items: ListBuffer[Item] = ListBuffer()

    def this(newItems: ListBuffer[Item], newlimitWeight: Int) = {
      this()
      this.limitWeight = newlimitWeight
      for(newitem <- newItems) {
        this.add(newitem)
      }
    }

    override def equals(obj: Any): Boolean = {
      obj.toString === this.toString()
    }

    override def toString(): String = {
      s"""
         |      totalValue: $totalValue
         |      limitWeight: $limitWeight
         |      usedWeight: $usedWeight
         |      items:
         |            $items
       """.stripMargin
    }


    def getTotalValue(): Int = totalValue

    def getUsedWeight(): Int = usedWeight

    private def unusedWeight(): Int = limitWeight - usedWeight

    def itemFits(item: Item): Boolean = unusedWeight() >= item.weight

    def add(item: Item): Unit = {
      if(itemFits(item)) {
        this.items += item
        this.usedWeight += item.weight
        this.totalValue += item.value
      }
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
      // overrideing toString() I can use the === to use my custom equal(...) function
      Cell(ListBuffer(givenGuitar), 2) === findCell(givenMatrix, Map(0 -> 1))
    )

    assert(
      Cell(ListBuffer(givenGuitar, givenStereo), 3) === findCell(givenMatrix, Map(1 -> 2))
    )

    assert(
      Cell(ListBuffer(), 2) === findCell(givenMatrix, Map(2 -> 1))
    )
  }


  test("can add items to a cell") {
    var withCell = Cell(ListBuffer(), 6)
    assert(withCell.getUsedWeight() === 0)

    withCell.add(Item("guitar", 1, 1500))
    assert(withCell.getUsedWeight() === 1)

    withCell.add(Item("stereo", 3, 1500))
    assert(withCell.getUsedWeight() === 4)
  }


  test("can add items to a cell inside a matrix") {
    val matrix = initMatrix(
      List(
        Item("guitar", 1, 1500),
        Item("stereo", 4, 3000),
        Item("laptop", 3, 2000)),
      List(1,2,3,4)
    )

    val radio = Item("radio", 2, 200)
    findCell(matrix, Map(2 -> 3)).add(radio)
    assert(findCell(matrix, Map(2 -> 3)) === Cell(ListBuffer(radio), 4))
  }
}
