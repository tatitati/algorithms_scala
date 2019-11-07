package problems

import org.scalatest.FunSuite

class FindLastButOneSpec extends FunSuite {

  def builtinFind[A](l: List[A]): A = {
    if (l.isEmpty) throw new NoSuchElementException
    l.init.last
  }

  def recursive[A](l: List[A]): A = {
    l match {
      case Nil => throw new NoSuchElementException
      case butlast :: _ :: Nil => butlast
      case _ :: tail => recursive(tail)
    }
  }

  test("find last but one") {
    val mylist = List(2, 4, 6, 33, 55, 66, 22)
    assert(66 == builtinFind(mylist))
    assert(66 == recursive(mylist))
  }
}
