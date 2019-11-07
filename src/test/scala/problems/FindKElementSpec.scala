package problems

import org.scalatest.FunSuite

class FindKElementSpec extends FunSuite {

  def builtin[A](k: Int, l: List[A]): A = {
    if (k < 0 || k > l.length -1 ) throw new NoSuchElementException
    l(k)
  }

  def recursive[A](k: Int, l: List[A]): A = (k, l) match {
    case (0, head :: _) => head
    case (n, _ :: tail) => recursive(n-1, tail)
    case (_ ,  Nil) => throw new NoSuchElementException
  }

  test("test") {
    val mylist = List(2, 4, 6, 33, 55, 66, 22)
    assert(55 == builtin(4, mylist))
    assert(55 == recursive(4, mylist))
  }
}
