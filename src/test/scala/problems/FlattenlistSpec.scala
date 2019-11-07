package problems

import org.scalatest.FunSuite

class FlattenlistSpec extends FunSuite {

  private def recursive(listOfLists: List[Any]): List[Any] = {
    listOfLists flatMap {
      case a: List[_] => recursive(a)
      case x => List(x)
    }
  }

  test(" Flatten a nested list structure.") {
    assert(List(1, 1, 2, 3, 5, 8) == recursive(List(List(1, 1), 2, List(3, List(5, 8)))))
  }
}
