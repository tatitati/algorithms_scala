package Learning

import org.scalatest.FunSuite
import scala.collection.mutable.ListMap

class ListMapSpec extends FunSuite {

  test("ListMap ") {
    var map1 = ListMap(
      "c" -> 5,
      "z" -> 5,
      "a" -> 5,
      "x" -> 5
    )


    assert(map1 == ListMap(
      "c" -> 5,
      "a" -> 5,
      "z" -> 5,
      "x" -> 5
    ), "when comparing, doesnt matter the orther")
  }
}
