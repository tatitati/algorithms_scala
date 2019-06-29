package graph

import org.scalatest.FunSuite
import scala.collection.immutable.ListMap
import scala.collection.mutable.ArrayBuffer

class Stack[A]() {
  private var q: ArrayBuffer[A] = ArrayBuffer()

  def push(item: A) = {
    q += item
  }

  def push(items: List[A]) = {
    q ++= items
  }

  def pop(): A = {
    q.remove(q.size-1)
  }

  def show(): ArrayBuffer[A] = {
    q
  }

  def isEmpty(): Boolean = {
    q.isEmpty
  }
}


class DepthFirstSearchSpec extends FunSuite {

  def dfs_traversal(graph: ListMap[String, List[String]]): ArrayBuffer[String] = {
    val stack = new Stack[String]()
    var journey: ArrayBuffer[String] = ArrayBuffer()

    // set start
    val (start, neighboors) = graph.head
    stack.push(start)
    stack.push(neighboors)
    journey += start

    while(!stack.isEmpty()) {
        val neighboors = graph.get(stack.pop()).get

        for(n <- neighboors if !journey.contains(n)){
          stack.push(n)
          journey += n
        }
    }
    
    journey
  }


  test("BFS") {
    val graph = ListMap( // NOTE: Map doesnt keep the order when iterating!!!!!!!
      "A" -> List("B", "C", "D", "E"),
      "B" -> List("A", "F"),
      "C" -> List("A"),
      "D" -> List("A", "G"),
      "E" -> List("A"),
      "F" -> List("B", "H"),
      "G" -> List("D", "I"),
      "H" -> List("F"),
      "I" -> List("G")
    )

    assert(
      dfs_traversal(graph) === ArrayBuffer("A", "B", "F", "H", "C", "D", "G", "I", "E")
    )
  }
}
