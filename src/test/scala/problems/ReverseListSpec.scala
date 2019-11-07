package problems

import org.scalatest.FunSuite
import scala.annotation.tailrec

class ReverseListSpec extends FunSuite {

  def recursive[A](l: List[A]): List[A] = {
    l match {
      case Nil => Nil
      case head :: tail =>recursive(tail) ::: List(head)
    }
  }

  def tailRecursive[A](list: List[A]): List[A] = {
    @tailrec
    def repeat[A](l: List[A], acc: List[A]): List[A] = {
      l match {
        case Nil => acc
        case head :: tail => repeat(tail, head +: acc)
      }
    }

    repeat(list, List())
  }

  def functional[A](list: List[A]): List[A] = {
    list.foldLeft(List[A]()){ (acc, item) => item :: acc }
  }

  test("test") {
    val mylist = List(2, 4, 6, 33, 55, 66, 22)
    assert(List(22, 66, 55, 33, 6, 4, 2) == recursive(mylist))
    assert(List(22, 66, 55, 33, 6, 4, 2) == tailRecursive(mylist))
    assert(List(22, 66, 55, 33, 6, 4, 2) == functional(mylist))
  }
}
