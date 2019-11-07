package problems

import org.scalatest.FunSuite

class ReverseListSpec extends FunSuite {

  def recursive[A](l: List[A]): List[A] = {
    l match {
      case Nil => Nil
      case head :: tail =>recursive(tail) ::: List(head)
    }
  }

  test("test") {
    val mylist = List(2, 4, 6, 33, 55, 66, 22)
    assert(List(22, 66, 55, 33, 6, 4, 2) == recursive(mylist))
  }
}
