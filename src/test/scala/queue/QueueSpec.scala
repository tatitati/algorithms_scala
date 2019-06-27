package queue

import org.scalatest.FunSuite
import scala.collection.mutable.ArrayBuffer

class Queue[A]() {
  var q: ArrayBuffer[A] = ArrayBuffer()

  def nque(item: A) = {
    q += item
  }

  def dque(): A = {
    val result = q(0)
    q.remove(0)
    result
  }

  def show(): ArrayBuffer[A] = {
    q
  }

}

class QueueSpec extends FunSuite {
  test("can enqueue and dqueue") {

    val q = new Queue[Int]()

    q.nque(3)
    q.nque(5)
    assert(q.show() === ArrayBuffer(3,5))

    q.nque(8)
    assert(q.show() === ArrayBuffer(3,5, 8))

    val item = q.dque()
    assert(item === 3)
    assert(q.show() === ArrayBuffer(5, 8))

  }
}
