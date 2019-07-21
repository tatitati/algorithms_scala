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

  test("I can update an specific item in a matrix") {
    var map1 = ListMap(
      "a" -> ListMap("a" -> 1, "b" -> 2, "c" -> 3),
      "b" -> ListMap("a" -> 1, "b" -> 2, "c" -> 3),
      "c" -> ListMap("a" -> 1, "b" -> 2, "c" -> 3)
    )

    map1("a")("c") = 99999

    assert(map1 === ListMap(
      "a" -> ListMap("a" -> 1, "b" -> 2, "c" -> 99999),
      "b" -> ListMap("a" -> 1, "b" -> 2, "c" -> 3),
      "c" -> ListMap("a" -> 1, "b" -> 2, "c" -> 3)
    ))
  }
}
