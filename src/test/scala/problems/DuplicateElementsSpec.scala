package problems

import org.scalatest.FunSuite

class DuplicateElementsSpec extends FunSuite {

  def recursive[A](l: List[A]): List[A] = {
    l flatMap{ x => List(x, x)}
  }

  test("test") {
    val mylist = List(1, 2, 3, 5, 8)
    assert(List(1, 1, 2, 2, 3, 3, 5, 5, 8, 8) == recursive(mylist))
  }
}
