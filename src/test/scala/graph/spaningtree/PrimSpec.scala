package graph.spaningtree

import DataStructure.queue.{MinPriorityQueue, Node}
import org.scalatest.FunSuite
import scala.collection.mutable.ArrayBuffer

class PrimSpec extends FunSuite {

  // adjacency matrix
  val X = Double.PositiveInfinity
  val graphWeighted = List(
  //To:  0  1  2  3  4  5  // From;
    List(X, 3, 1, 6, X, X),// 0
    List(3, X, 5, X, 3, X),// 1
    List(1, 5, X, 5, 6, 4),// 2
    List(6, X, 5, X, X, 2),// 3
    List(X, 3, 6, X, X, 6),// 4
    List(X, X, 4, 2, 6, X) // 5
  )

  test("I can get the minimum distance in a list") {
    val a = List(6, X, 5, X, X, 2)
    assert(2 === a.min)
  }

  test("I can get the index (the number of the next node) of the minimum one") {
    val a = List(6, X, 8, X, X, 2)
    assert(5 === a.zipWithIndex.min._2) // next node would be 5
    assert(
      List(
        (6.0,0),
        (X,1),
        (8.0,2),
        (X,3),
        (X,4),
        (2.0,5)
      ) == a.zipWithIndex, "zipWithIndex return a list of (val, index)")
  }

  test("Prim") {

    def prim(start: Int, graph: List[List[Double]]): ArrayBuffer[Int] = {
      // start
      var primTree = ArrayBuffer[Int]()
      var minq = new MinPriorityQueue()
      minq.nqueue(Node(start, 0))

      // main
      while(!minq.isEmpty()) {
        var idnode = minq.dqueue().data.asInstanceOf[Int]
        if (!primTree.contains(idnode)) {
          primTree += idnode

          var row = graph(idnode)
          for((weight, idnode) <- row.zipWithIndex if weight != X) {
            minq.nqueue(Node(idnode, weight))
          }
        }
      }

      primTree
    }

    assert(
      prim(4, graphWeighted) == List(4, 1, 0, 2, 5, 3)
    )


  }
}
