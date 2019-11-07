package problems

import org.scalatest.FunSuite

// P01 (*) Find the last element of a list.
//     Example:
//     scala> last(List(1, 1, 2, 3, 5, 8))
//     res0: Int =

class FindLastElementSpec extends FunSuite {

    def builtinLast[A](l: List[A]): A = l.last

    def recursiveLast[A](l: List[A]): A = {
      l match {
        case last :: Nil => last
        case _ :: tail => recursiveLast(tail)
        case Nil =>  throw new NoSuchElementException
      }
    }

  test("last") {
    val mylist = List(2, 4, 6, 33, 55, 66, 22)
    assert(22 == builtinLast(mylist))
    assert(22 == recursiveLast(mylist))
  }
}
