package Learning

import org.scalatest.FunSuite

class ListSpec extends FunSuite {
  test("list is immutable, returns a new item when adding items") {
    var a = List("aa", "bb")

    a ++ List("cc")
    var c = a ++ List("cc")


    assert(a == List("aa", "bb"))
    assert(c == List("aa", "bb", "cc"))
  }

  test("I can add items to a list with ++=") {
    var a = List("aa", "bb")

    a ++= List("cc")

    assert(a == List("aa", "bb", "cc"))
  }
}
