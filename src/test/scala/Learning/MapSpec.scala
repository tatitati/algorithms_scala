package Learning

import org.scalatest.FunSuite

class MapSpec extends FunSuite {

  test("Can remove/add items from map") {
    val a = Map(
      "a" -> List("AA1", "AA2", "AA3"),
      "b" -> List("BB1", "BB2", "BB3"),
      "c" -> List("CC1", "CC2", "CC3")
    )

    // remove
    assert(Map(
        "a" -> List("AA1", "AA2", "AA3"),
        "c" -> List("CC1", "CC2", "CC3")
    ) == a - "b")

    // add
    assert(Map(
      "a" -> List("AA1", "AA2", "AA3"),
      "b" -> List("BB1", "BB2", "BB3"),
      "c" -> List("CC1", "CC2", "CC3"),
      "d" -> List("DD1", "DD2", "DD3")
    ) == a ++ Map("d" -> List("DD1", "DD2", "DD3")))
  }

  test("Can filter by keys") {
    val a = Map(
      "A"    -> "sumi",
      "B"     -> "2",
      "C" -> "0",
      "D"  -> "1",
      "E"    -> "govind singh"
    )

    val b = a.filterKeys(_ != "A")
    assert(Map(
      "E" -> "govind singh",
      "B" -> "2",
      "C" -> "0",
      "D" -> "1") == b)
  }
}
