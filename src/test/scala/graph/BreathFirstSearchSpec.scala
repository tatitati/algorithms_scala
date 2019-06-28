package graph

import org.scalatest.FunSuite
import scala.collection.immutable.ListMap
import scala.collection.mutable.ArrayBuffer

class Queue[A]() {
  var q: ArrayBuffer[A] = ArrayBuffer()

  def nque(item: A) = {
    q += item
  }

  def dque(): A = {
    q.remove(0)
  }

  def show(): ArrayBuffer[A] = {
    q
  }

  def isEmpty(): Boolean = {
    q.size match {
      case 0 => true
      case _ => false
    }
  }

}

class BreathFirstSearchSpec extends FunSuite {

  def bfs_traversal(graph: ListMap[String, List[String]]) = {
    val queue = new Queue[String]()
    var journey: ArrayBuffer[String] = ArrayBuffer()

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

  test("BFS") {
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
}
