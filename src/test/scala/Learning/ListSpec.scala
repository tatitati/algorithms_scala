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

  test("Can append List to a List, BUT NO IN PLACE, LIST ARE IMMUTABLE") {
    var all: List[List[String]] = List(
      List("aa", "bb"),
      List("cc", "dd")
    )

    val c = all :+ List("ee", "ff")

    println(all)

    assert(c == List(
      List("aa", "bb"),
      List("cc", "dd"),
      List("ee", "ff")
    ))
  }
}
