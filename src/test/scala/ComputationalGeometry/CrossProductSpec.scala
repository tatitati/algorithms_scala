package ComputationalGeometry

import org.scalatest.FunSuite

class CrossProductSpec extends FunSuite {

  def crossproductTwoPoints(p1: List[Int], p2: List[Int]): Int = {
    p1(0) * p2(1) - p1(1) * p2(0)
  }

  test("cross product of two points") {
    val p1 = List(2, 4)
    val p2 = List(4, 1)

    assert(crossproductTwoPoints(p1, p2) < 0)
  }

  test("Order matte in cross product of two points") {
    val p1 = List(2, 4)
    val p2 = List(4, 1)

    assert(crossproductTwoPoints(p2, p1) > 0)
  }

  test("cross product of two segments turning LEFT in p2") {
    val p1 = List(2, 1)
    val p2 = List(5, 3)
    val p3 = List(1, 4)

    val p2_ = List(p2(0) - p1(0), p2(1) - p1(1))
    val p3_ = List(p3(0) - p1(0), p3(1) - p1(1))

    assert(crossproductTwoPoints(p3_, p2_) < 0) // order matter. From p2 to p3 I have to turn let (p3 x p2 < 0)
  }

  test("cross product of two segments turning RIGHT on p2") {
    val p1 = List(2, 1)
    val p2 = List(4, 3)
    val p3 = List(7, 2)

    val p2_ = List(p2(0) - p1(0), p2(1) - p1(1))
    val p3_ = List(p3(0) - p1(0), p3(1) - p1(1))

    assert(crossproductTwoPoints(p3_, p2_) > 0) // from p2 to p3 I have to turn right ( p3 x p2 > 0)
  }
}
