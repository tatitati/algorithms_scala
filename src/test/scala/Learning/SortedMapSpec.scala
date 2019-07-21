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
}
