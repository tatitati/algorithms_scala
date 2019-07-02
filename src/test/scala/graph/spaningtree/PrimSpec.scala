package graph.spaningtree

import org.scalatest.FunSuite
import scala.collection.mutable.ListMap
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

  test("I can get the index of the minimum one") {
    val a = List(6, X, 8, X, X, 2)
    assert(5 === a.zipWithIndex.min._2)
  }

  test("Prim") {

    def prim(start: Int, graph: List[List[Double]]): Unit = {

    }

    val minSpanningTree = prim(4, graphWeighted)


  }
}
