package problems

import org.scalatest.FunSuite

class RemoveConsecutiveSpec extends FunSuite {

  def compress(lst: List[Any]): List[Any] = {

  }

  test("Eliminate consecutive duplicates of list elementss") {
    assert(List('a, 'b, 'c, 'a, 'd, 'e) == compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))
  }
}
