package Stack

import org.scalatest.FunSuite

import scala.collection.mutable.ArrayBuffer

class Stack[A]() {
  private var q: ArrayBuffer[A] = ArrayBuffer()

  def push(item: A) = {
    q += item
  }

  def pop(): A = {
    q.remove(q.size-1)
  }

  def show(): ArrayBuffer[A] = {
    q
  }
}


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
}
