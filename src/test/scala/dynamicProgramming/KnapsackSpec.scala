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
         |      Cell
         |      ====
         |      totalValue: $totalValue
         |      limitWeight: $limitWeight
         |      usedWeight: $usedWeight
         |      items:
         |            $items
       """.stripMargin
    }

    def getItems(): ListBuffer[Item] = items
    def getTotalValue(): Int = totalValue
    def getLimitWeight(): Int = limitWeight

    def getUsedWeight(): Int = usedWeight

    def unusedWeight(): Int = limitWeight - usedWeight

    def itemFits(item: Item): Boolean = unusedWeight() >= item.weight

    def add(item: Item): Unit = {
      if(itemFits(item)) {
        this.items += item
        this.usedWeight += item.weight
        this.totalValue += item.value
      } else {
        throw new Exception("adding item that doesnt fit")
      }
    }

    def importItemsFromCell(cell: Cell) = {
      val items = cell.getItems()
      for(item <- items) {
        this.add(item)
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
      for(subbag <- subBags) {
        val emptyCell = Cell(ListBuffer(), limitWeight = subbag)
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

  def findBestForWeight(matrix: ListBuffer[ListBuffer[Cell]], subweightColumn: Int ): Cell = {
    var maxCell = Cell(ListBuffer(), 0)

    for(row <- matrix) {
      if (row(subweightColumn).getTotalValue() > maxCell.getTotalValue()) {
        maxCell = row(subweightColumn)
      }
    }

    maxCell
  }

  def updateCell(
          matrix: ListBuffer[ListBuffer[Cell]],
          cell: Cell,
          position: Map[Int, Int])
      : ListBuffer[ListBuffer[Cell]] = {
    val rowIndex = position.keys.head
    val columnIndex = position.values.head

    matrix(rowIndex).update(columnIndex, cell)
    matrix
  }

  def knapsack(): Unit = {
    val items = List(
      Item("guitar", weight=1, 1500),
      Item("stereo", weight=4, 3000),
      Item("laptop", weight=3, 2000)
    )
    val subweights = List(1,2,3,4)


    var matrix = initMatrix(items, subweights)

      for(r <- (0 to matrix.size - 1)) {
        val item = items(r)
        val row = matrix(r)
        for(c <- (0 to row.size - 1)) {
          println(s"${r} -> ${c}")
          var cell = row(c)

          if(cell.itemFits(item) && r == 0) {
            cell.add(item)
            matrix = updateCell(matrix, cell, Map(r->c))
            println(cell)

          } else if(!cell.itemFits(item) && r != 0) {
            var currentBestCell = findBestForWeight(matrix, c) // take the one above
            matrix = updateCell(matrix, currentBestCell, Map(r->c))
            println(currentBestCell)

            // TAKE THE ONE ABOVE?
          } else if (cell.itemFits(item) && r != 0) {
            var bestRemainingWeight = Cell(ListBuffer(), 0)
            if(cell.getLimitWeight() - item.weight != 0) {
              bestRemainingWeight = findBestForWeight(matrix, cell.getLimitWeight() - item.weight)
            }

            // CALCULATE MAX OF THESE TWO OPTIONS
            var bestCurrentWeight = findBestForWeight(matrix, c)
            if ((bestRemainingWeight.getTotalValue() + item.value) > bestCurrentWeight.getTotalValue()) {
              // VALUE OF CURRENT ITEM + VALUE OF REMAINING SPACE
              cell.importItemsFromCell(bestRemainingWeight)
              cell.add(item)
              println(cell)
              matrix = updateCell(matrix, cell, Map(r->c))
            } else {
              // TAKE THE ONE ABOVE
              println(bestCurrentWeight)
              matrix = updateCell(matrix, bestCurrentWeight, Map(r->c))
            }
          }
        }
      }
  }



  test("can init a matrix") {
    val givenGuitar = Item("guitar", 1, 1500)
    val givenStereo = Item("stereo", 4, 3000)
    val givenLaptop = Item("laptop", 3, 2000)
    val matrix = initMatrix(
      List(givenGuitar, givenStereo, givenLaptop),
      List(10,20,30,40)
    )

    assert(matrix === ListBuffer(
      ListBuffer(Cell(ListBuffer(), 10),Cell(ListBuffer(), 20),Cell(ListBuffer(), 30),Cell(ListBuffer(), 40)),
      ListBuffer(Cell(ListBuffer(), 10),Cell(ListBuffer(), 20),Cell(ListBuffer(), 30),Cell(ListBuffer(), 40)),
      ListBuffer(Cell(ListBuffer(), 10),Cell(ListBuffer(), 20),Cell(ListBuffer(), 30),Cell(ListBuffer(), 40))
    ))
  }

  test("can find an specific cell in the matrix") {
    val givenGuitar = Item("guitar", 1, 1500)
    val givenStereo = Item("stereo", 2, 3000)
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


  test("can add items to an standalone cell") {
    var withCell = Cell(ListBuffer(), 6)
    assert(withCell.getUsedWeight() === 0)

    withCell.add(Item("guitar", 1, 1500))
    assert(withCell.getUsedWeight() === 1)

    withCell.add(Item("stereo", 3, 1500))
    assert(withCell.getUsedWeight() === 4)
  }


  test("can add items to a cell that is part of a matrix") {
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

  test("can find best solution for a sub-bag") {
    val givenGuitar = Item("guitar", 1, 1500)
    val givenStereo = Item("stereo", 2, 3000)
    val givenMatrix = ListBuffer(
      ListBuffer(Cell(ListBuffer(), 1),Cell(ListBuffer(givenGuitar), 2), Cell(ListBuffer(), 3),                         Cell(ListBuffer(), 4)),
      ListBuffer(Cell(ListBuffer(), 1),Cell(ListBuffer(), 2),            Cell(ListBuffer(givenGuitar, givenStereo), 3), Cell(ListBuffer(), 4)),
      ListBuffer(Cell(ListBuffer(), 1),Cell(ListBuffer(), 2),            Cell(ListBuffer(), 3),                         Cell(ListBuffer(), 4))
    )

    assert(findBestForWeight(givenMatrix, 2) === Cell(ListBuffer(givenGuitar, givenStereo), 3))
  }

  test("matrix can update a cell") {
    val givenGuitar = Item("guitar", 1, 1500)
    val givenStereo = Item("stereo", 2, 3000)
    val givenMatrix = ListBuffer(
      ListBuffer(Cell(ListBuffer(), 1),Cell(ListBuffer(givenGuitar), 2), Cell(ListBuffer(), 3),                         Cell(ListBuffer(), 4)),
      ListBuffer(Cell(ListBuffer(), 1),Cell(ListBuffer(), 2),            Cell(ListBuffer(givenGuitar, givenStereo), 3), Cell(ListBuffer(), 4)),
      ListBuffer(Cell(ListBuffer(), 1),Cell(ListBuffer(), 2),            Cell(ListBuffer(), 3),                         Cell(ListBuffer(), 4))
    )

    assert(
      Cell(ListBuffer(givenGuitar), 2) === findCell(givenMatrix, Map(0 -> 1))
    )
    updateCell(givenMatrix, Cell(ListBuffer(givenStereo),2), Map(0->1))
    assert(
      Cell(ListBuffer(givenStereo), 2) === findCell(givenMatrix, Map(0 -> 1))
    )

  }


  knapsack()

}
