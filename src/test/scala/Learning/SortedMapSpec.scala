package Learning

import org.scalatest.FunSuite
import scala.collection.SortedMap

class SortedMapSpec extends FunSuite {

  test("SortedMap sort by key the map") {
    var map1 = SortedMap(
      "c" -> 5,
      "z" -> 5,
      "a" -> 5,
      "x" -> 5
    )

    assert(map1 == SortedMap(
      "a" -> 5,
      "c" -> 5,
      "x" -> 5,
      "z" -> 5
    ))
  }

  test("Can add new items") {
    var map1 = SortedMap(
      "c" -> 5,
      "z" -> 5,
      "a" -> 5,
      "x" -> 5
    )

    map1 += ("somethingnew" -> 4)

    assert(map1 == SortedMap(
      "a" -> 5,
      "c" -> 5,
      "somethingnew" -> 4,
      "x" -> 5,
      "z" -> 5
    ))
  }

  test("Can update an item ") {
    var map1 = SortedMap(
      "c" -> 5,
      "z" -> 5,
      "a" -> 5,
      "x" -> 5
    )

    map1 += ("c" -> 10)

    assert(map1 == SortedMap(
      "a" -> 5,
      "c" -> 10,
      "x" -> 5,
      "z" -> 5
    ))
  }
}
