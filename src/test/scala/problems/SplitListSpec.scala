package problems

import org.scalatest.FunSuite

import scala.annotation.tailrec

// P17 (*) Split a list into two parts.
//     The length of the first part is given.  Use a Tuple for your result.
//
//     Example:
//     scala> split(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
//     res0: (List[Symbol], List[Symbol]) = (List('a, 'b, 'c),List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k))

class SplitListSpec extends FunSuite {

  def recursive[A](n: Int, l: List[A]): (List[A], List[A]) = {
    l match {
      case Nil => (Nil, Nil)
      case h :: t if n == 0 =>  (Nil, l)
      case h :: t if n > 0 =>
        val (pre, post) = recursive(n-1, t)
        (h :: pre, post)
    }
  }


  def tailrecursive[A](n: Int, l: List[A]): (List[A], List[A]) = {
    @tailrec
    def repeat(i: Int, l: List[A], accPre: List[A]): (List[A], List[A]) = {
      l match {
        case Nil => (accPre, Nil)
        case h :: t if i == 0 => (accPre, l)
        case h :: t if i > 0 => repeat(i - 1, t, accPre :+ h)
      }
    }

    repeat(n, l, List())
  }

  def functional[A](n: Int, l: List[A]): (List[A], List[A]) = {
    (l.take(n), l.drop(n))
  }

  test("test") {
    val mylist = List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)
    assert((List('a, 'b, 'c),List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) == recursive(3, mylist))
    assert((List('a, 'b, 'c),List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) == tailrecursive(3, mylist))
    assert((List('a, 'b, 'c),List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) == functional(3, mylist))
  }
}
