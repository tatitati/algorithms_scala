package DataStructure.queue

import org.scalatest.FunSuite
import scala.collection.mutable.ArrayBuffer

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
