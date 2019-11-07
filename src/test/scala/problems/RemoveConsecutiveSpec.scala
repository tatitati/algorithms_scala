//package problems
//
//import org.scalatest.FunSuite
//import scala.annotation.tailrec
//
//class RemoveConsecutiveSpec extends FunSuite {
//
//  // standard recursive
//  def compressRecursive(ls: List[Any]): List[Any] = ls match {
//    case Nil => Nil
//    case v :: tail => v :: compressRecursive(tail.dropWhile(_ == v))
//  }
//
//  // tail recursive
//  def compressTailRecursive(ls: List[Any]): List[Any] = {
//    @tailrec
//    def run(ls: List[Any], result: List[Any]): List[Any] = {
//      ls match {
//        case Nil => result
//        case v :: tail => run(tail.dropWhile(_ == v), result ++ List(v))
//      }
//    }
//
//    run(ls, List())
//  }
//
//  // functional
//  def inFunctional(ls: List[Any]): List[Any] = ???
//
//  test("Eliminate consecutive duplicates of list elementss") {
//
//    assert(List('a, 'b, 'c, 'a, 'd, 'e) == compressRecursive(    List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))
//    assert(List('a, 'b, 'c, 'a, 'd, 'e) == compressTailRecursive(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))
//    assert(List('a, 'b, 'c, 'a, 'd, 'e) == inFunctional(         List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))
//  }
//
//  test(".dropWhile()") {
//    val a = List("b", "b", "b", "b", "c", "c")
//
//    val result1 = a.dropWhile(_ == "b")
//    val result2 = a.dropWhile(_ == "c")
//
//    assert(List("c", "c") == result1)
//    assert(List("b", "b", "b", "b", "c", "c") == result2)
//  }
//
//  test(".foldRight()") {
//    val a = List("a", "b", "b", "b", "c", "c")
//    val result = a.foldRight(""){ (x, y) =>
//
//    }
//    println(result)
//    //assert(List("b", "b", "b", "b", "c", "c") == result)
//  }
//}
