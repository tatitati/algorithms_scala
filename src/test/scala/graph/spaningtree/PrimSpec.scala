package graph.spaningtree

import org.scalatest.FunSuite
import scala.collection.mutable.ListMap
import scala.collection.mutable.ArrayBuffer

class PrimSpec extends FunSuite {

  // adjacency matrix
  val graphWeighted = List(
  //To:  0  1  2  3  4  5  // From;
    List(0, 3, 1, 6, 0, 0),// 0
    List(3, 0, 5, 0, 3, 0),// 1
    List(1, 5, 0, 5, 6, 4),// 2
    List(6, 0, 5, 0, 0, 2),// 3
    List(0, 3, 6, 0, 0, 6),// 4
    List(0, 0, 4, 2, 6, 0) // 5
  )

  val start = 4

  test("I can get the minimum distance in a list") {
    val a = List(6, 0, 5, 0, 0, 2)
    assert(2 === a.min)

  }


}
