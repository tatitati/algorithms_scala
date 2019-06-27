package Stack

import org.scalatest.FunSuite

import scala.collection.mutable.ArrayBuffer

class Stack[A]() {
  private var q: ArrayBuffer[A] = ArrayBuffer()

  def push(item: A) = {
    q += item
  }

  def pop(): A = {
    val result = q.last
    q.remove(q.size-1)

    result

  }

  def show(): ArrayBuffer[A] = {
    q
  }
}


class StackSpec extends FunSuite {

  test("can enqueue-dequeue") {
    val q = new Stack[Int]()
    q.push(3)
    q.push(5)
    q.push(6)


    assert(q.show() === ArrayBuffer(3,5,6))

    q.pop()

    assert(q.show() === ArrayBuffer(3,5))
  }
}
