package problems

import org.scalatest.FunSuite

import scala.annotation.tailrec

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

  def tailRecursive[A](n: Int, l: List[A]): List[A] = {
    @tailrec
    def repeat(i: Int, l: List[A], acc: List[A]): List[A] = {
      l match {
        case Nil => acc
        case h :: t if i == 0 => repeat(n, t, acc)
        case h :: t if i > 0 => repeat(i-1, t, acc :+ h)
      }
    }

    repeat(n, l, List())
  }

  def functional[A](n: Int, l: List[A]): List[A] = {
    val r: List[(A, Int)] = l.zipWithIndex
    val r2: List[(A, Int)] = r.filter{ tuple =>
      tuple._2 == 0 || (tuple._2 + 1) % (n + 1) != 0
    }

    r2.map(_._1)
  }

  test("test") {
    val mylist = List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)
    assert(List('a, 'b, 'c, 'e, 'f, 'g, 'i, 'j, 'k) == recursive(3, mylist))
    assert(List('a, 'b, 'c, 'e, 'f, 'g, 'i, 'j, 'k) == tailRecursive(3, mylist))
    assert(List('a, 'b, 'c, 'e, 'f, 'g, 'i, 'j, 'k) == functional(3, mylist))
  }
}
