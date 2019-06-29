package graph

import Stack.Stack
import org.scalatest.FunSuite
import scala.collection.immutable.ListMap
import scala.collection.mutable.ArrayBuffer

class DepthFirstSearchSpec extends FunSuite {

  def dfs_traversal[A](graph: ListMap[A, List[A]]): ArrayBuffer[A] = {
    val stack = new Stack[A]()
    var journey: ArrayBuffer[A] = ArrayBuffer()

    // set start
    val (start, _) = graph.head
    stack.push(start)

    while(!stack.isEmpty()) {
        val node = stack.pop()
        val neighboors = graph.get(node).get
        journey += node

        for(n <- neighboors if(!stack.contains(n) && !journey.contains(n))) {
          stack.push(n)
        }
    }

    journey
  }


  test("DFS 1") {
    val graphNoDirected = ListMap( // NOTE: Map doesnt keep the order when iterating!!!!!!!
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
      dfs_traversal(graphNoDirected) === ArrayBuffer("A", "E", "D", "G", "I", "C", "B", "F", "H")
    )
  }

  test("DFS 2") {
    val graphDirected = ListMap( // NOTE: Map doesnt keep the order when iterating!!!!!!!
      1 -> List(2, 3),
      2 -> List(4, 5),
      3 -> List(5),
      4 -> List(6),
      5 -> List(6),
      6 -> List(7),
      7 -> List()
    )

    assert(
      dfs_traversal(graphDirected) === ArrayBuffer(1, 3, 5, 6, 7, 2, 4)
    )
  }

  test("DSF 2: interesting result when I indicate ALL the neighboors of a node") {
    val graph = ListMap( // NOTE: Map doesnt keep the order when iterating!!!!!!!
      1 -> List(2, 3),
      2 -> List(1, 4, 5),
      3 -> List(1, 5),
      4 -> List(2, 6),
      5 -> List(3, 2, 6),
      6 -> List(4, 5, 7),
      7 -> List(6)
    )

    assert(
      dfs_traversal(graph) === ArrayBuffer(1, 3, 5, 6, 7, 4, 2) // it finishes in 4,2 (which is wrong)
    )
  }
}
