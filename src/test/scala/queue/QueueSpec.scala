package queue

import org.scalatest.FunSuite
import scala.collection.mutable.ArrayBuffer

class Queue[A]() {
  var q: ArrayBuffer[A] = ArrayBuffer()

  def nque(item: A) = {
    q += item
  }

  def dque(): A = {
    q.remove(0)
  }

  def show(): ArrayBuffer[A] = {
    q
  }

  def isEmpty(): Boolean = {
    q.size match {
      case 0 => true
      case _ => false
    }
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

  test("can check if is empty") {

    val q = new Queue[Int]()

    q.nque(3)
    assert(q.isEmpty() === false)
    q.dque()
    assert(q.isEmpty() === true)
  }
}
