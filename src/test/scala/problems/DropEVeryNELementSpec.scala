package problems

import org.scalatest.FunSuite

class DropEVeryNELementSpec extends FunSuite {
  
  def recursive[A](n: Int, l: List[A]): List[A] = {
    def repeat[A](i: Int, l: List[A]): List[A] = {
      l match {
        case Nil => List()
        case h :: t if i == 0 => repeat(n, t)
        case h :: t if i > 0 => h :: repeat(i - 1, t)
      }
    }

    repeat(n, l)
  }

  test("testdef repeat[A](n: Int, l: List[A]): List[A] = {") {
    val mylist = List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)
    assert(List('a, 'b, 'c, 'e, 'f, 'g, 'i, 'j, 'k) == recursive(3, mylist))
  }
}
