package problems

import org.scalatest.FunSuite

class ReverseListSpec extends FunSuite {

  //def recursive[A](l: List[A]): List[A] = {
  //  l match {
  //    case Nil => Nil
  //    case head :: tail =>
  //  }
  //}

  test("::: vs ::") {
    val a = List(2, 4, 6, 33, 55, 66, 22)
    val b = List(2, 5, 6, 7, 8, 9)

    assert(List(2, 4, 6, 33, 55, 66, 22, 2, 5, 6, 7, 8, 9) == a ::: b)
    assert(List(List(2, 4, 6, 33, 55, 66, 22), 2, 5, 6, 7, 8, 9) == a :: b)
  }
}
