package problems

import org.scalatest.FunSuite
import scala.annotation.tailrec

class RemoveConsecutiveSpec extends FunSuite {

  def recursive[A](ls: List[A]): List[A] = ls match {
    case Nil => Nil
    case h :: tail => h :: recursive(tail.dropWhile(_ == h))
  }

  def tailrecursive[A](ls: List[A]): List[A] = {
    @tailrec
    def run(ls: List[A], acc: List[A]): List[A] = {
      ls match {
        case Nil => acc
        case h :: tail => run(tail.dropWhile(_ == h), acc ++ List(h))
      }
    }

    run(ls, List())
  }

  def inFunctional[A](ls: List[A]): List[A] = {
    ls.foldLeft(List[A]()){ (acc, item) =>
      if(acc.isEmpty || acc.last != item) {
        acc :+ item
      } else {
        acc
      }
    }
  }

  test("Eliminate consecutive duplicates of list elementss") {
    val mylist = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
    assert(List('a, 'b, 'c, 'a, 'd, 'e) == recursive(mylist))
    assert(List('a, 'b, 'c, 'a, 'd, 'e) == tailrecursive(mylist))
    assert(List('a, 'b, 'c, 'a, 'd, 'e) == inFunctional(mylist))

    val mylist1 = List(33, 34, 34,2, 342)
    val mylist2 = List(1,2, 3, 4)

    assert(List(List(33, 34, 34, 2, 342), 1, 2, 3, 4) == mylist1 +: mylist2)
  }

}
