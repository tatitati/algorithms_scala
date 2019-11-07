package problems

import org.scalatest.FunSuite
import scala.annotation.tailrec

class RemoveConsecutiveSpec extends FunSuite {

  def recursive(ls: List[Any]): List[Any] = ls match {
    case Nil => Nil
    case v :: tail => v :: recursive(tail.dropWhile(_ == v))
  }

  def tailrecursive(ls: List[Any]): List[Any] = {
    @tailrec
    def run(ls: List[Any], result: List[Any]): List[Any] = {
      ls match {
        case Nil => result
        case v :: tail => run(tail.dropWhile(_ == v), result ++ List(v))
      }
    }

    run(ls, List())
  }

  // functional
  def inFunctional(ls: List[Any]): List[Any] = ???

  test("Eliminate consecutive duplicates of list elementss") {
    val mylist = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
    assert(List('a, 'b, 'c, 'a, 'd, 'e) == recursive(mylist))
    assert(List('a, 'b, 'c, 'a, 'd, 'e) == tailrecursive(mylist))
    assert(List('a, 'b, 'c, 'a, 'd, 'e) == inFunctional(mylist))
  }
}
