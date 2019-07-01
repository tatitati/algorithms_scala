package graph.traversal

import org.scalatest.FunSuite
import queue.Queue

import scala.collection.immutable.ListMap
import scala.collection.mutable.ArrayBuffer

class BfsIterativeSpec extends FunSuite {

  def bfs_traversal[A](graph: ListMap[A, List[A]]) = {
    val queue = new Queue[A]()
    var journey: ArrayBuffer[A] = ArrayBuffer()

    // set start
    val (start, _) = graph.head
    queue.nque(start)
    journey += start

    // traverse rest of graph from start
    while(!queue.isEmpty()) {
      val neighboors = graph.get(queue.dque()).get

      for(n <- neighboors if !journey.contains(n)){
          queue.nque(n)
          journey += n
      }
    }

    journey
  }

  test("BFS 1") {
    val graph = ListMap(  // NOTE: Map doesnt keep the order when iterating!!!!!!!
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
      bfs_traversal(graph) === ArrayBuffer("A", "B", "C", "D", "E", "F", "G", "H", "I")
    )
  }

  test("BFS 2") {
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
      bfs_traversal(graphDirected) === ArrayBuffer(1,2,3,4,5,6,7)
    )
  }
}
