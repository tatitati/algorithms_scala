package problems

import org.scalatest.FunSuite

import scala.annotation.tailrec

class FindNumberOfElementsInListSpec extends FunSuite {

    def recursive(lst: List[Any]) : Int = {
        lst match {
          case Nil => 0
          case _ :: tail => 1 + recursive(tail)
        }
    }

    def tailRecursive(lst: List[Any]) : Int = {
      @tailrec
      def repeat(l: List[Any], counter: Int): Int = {
        l match {
          case Nil => counter
          case _ :: tail => repeat(tail, counter + 1)
        }
      }

      repeat(lst, 0)
    }

    def functional(lst: List[Any]) : Int = {
      lst.foldLeft(0) { (acc, item) => acc + 1} // from left to right....
    }

    test("test") {
      val mylist = List(2, 4, 6, 33, 55, 66, 22)
      assert(7 == recursive(mylist))
      assert(7 == tailRecursive(mylist))
      assert(7 == functional(mylist))
    }
}
