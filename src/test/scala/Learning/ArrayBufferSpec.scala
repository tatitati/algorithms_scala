package Learning

import org.scalatest.FunSuite

import scala.collection.mutable.ArrayBuffer

class ArrayBufferSpec extends FunSuite {
  test("arraybuffer is mutable, returns a new item when adding items") {
    var a = ArrayBuffer("aa", "bb")

    a ++ ArrayBuffer("cc")
    var c = a ++ ArrayBuffer("cc")

    assert(a == ArrayBuffer("aa", "bb"))
    assert(c == ArrayBuffer("aa", "bb", "cc"))
  }

  test("I can append items with ++=") {
    var a = ArrayBuffer("aa", "bb")

    a ++= ArrayBuffer("cc")

    assert(a == ArrayBuffer("aa", "bb", "cc"))
  }

  test("Can append List to a List IN PLACE") {
    var all: ArrayBuffer[ArrayBuffer[String]] = ArrayBuffer(
      ArrayBuffer("aa", "bb"),
      ArrayBuffer("cc", "dd")
    )

    all += ArrayBuffer("ee", "ff")

    assert(all == ArrayBuffer(
      ArrayBuffer("aa", "bb"),
      ArrayBuffer("cc", "dd"),
      ArrayBuffer("ee", "ff")
    ))
  }
}
