package DataStructure.Stack

import org.scalatest.FunSuite
import scala.collection.mutable.ArrayBuffer

class StackSpec extends FunSuite {

  test("can push and pop") {
    val q = new Stack[Int]()

    q.push(3)
    q.push(5)
    assert(q.show() === ArrayBuffer(3,5))

    q.push(6)
    assert(q.show() === ArrayBuffer(3,5,6))

    val result = q.pop()
    assert(result === 6)
    assert(q.show() === ArrayBuffer(3,5))
  }

  test("can push multiple items") {
    val q = new Stack[Int]()

    q.push(List(3, 5, 6))

    assert(q.show() === ArrayBuffer(3, 5, 6))
  }
}
