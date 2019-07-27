package ComputationalGeometry

import org.scalatest.FunSuite

class CrossProductSpec extends FunSuite {

  def crossproduct(p1: List[Int], p2: List[Int]): Int = {
    p1(0) * p2(1) - p1(1) * p2(0)
  }

  test("cross product of two points") {
    val p1 = List(2, 4)
    val p2 = List(4, 1)

    assert(crossproduct(p1, p2) < 0)
  }

  test("Order matte in cross product of two points") {
    val p1 = List(2, 4)
    val p2 = List(4, 1)

    assert(crossproduct(p2, p1) > 0)
  }


  test("cross product of two segments turning left in p2") {
    val p1 = List(2, 1)
    val p2 = List(5, 3)
    val p3 = List(1, 4)

    val p1p2 = List(p2(0) - p1(0), p2(1) - p1(1))
    val p2p3 = List(p3(0) - p2(0), p3(1) - p2(1))

    assert(crossproduct(p1p2, p2p3) > 0)
  }

  test("cross product of two segments turning right on p2") {
    val p1 = List(2, 1)
    val p2 = List(4, 3)
    val p3 = List(7, 2)

    val p1p2 = List(p2(0) - p1(0), p2(1) - p1(1))
    val p2p3 = List(p3(0) - p2(0), p3(1) - p2(1))

    assert(crossproduct(p1p2, p2p3) < 0)
  }
}
