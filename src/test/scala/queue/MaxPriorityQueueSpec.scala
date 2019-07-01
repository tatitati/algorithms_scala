package queue

import org.scalatest.FunSuite
import scala.collection.mutable.ArrayBuffer

class MaxPriorityQueueSpec extends FunSuite {
  test("ArrayBuffer inser") {
    val a = ArrayBuffer(1,3,7,9)
    a.insert(2,100)
    assert(a === ArrayBuffer(1, 3, 100, 7, 9))
  }

  test("I can get indexes in a for loop") {
    val a = ArrayBuffer(1, 3, 100, 7, 9)
    for((item, ix) <- a.zipWithIndex) {
      println(ix + " => " + item)
      //      0 => 1
      //      1 => 3
      //      2 => 100
      //      3 => 7
      //      4 => 9
    }
  }

  test("I can insert most basic cases") {
    val q = new MaxPriorityQueue()
    val nodeD10 = Node("D10", 10)
    val nodeC3 = Node("C3", 3)

    q.nqueue(nodeD10)
    q.nqueue(nodeC3)

    assert(q.getQ() === ArrayBuffer(
      nodeD10,
      nodeC3)
    )

  }

  test("I can set new nodes in the proper position") {
    val q = new MaxPriorityQueue()
    val nodeD10 = Node("D10", 10)
    val nodeC3 = Node("C3", 3)
    val nodeB8 = Node("B8", 8)
    val nodeA5 = Node("A5", 5)
    val nodeF7 = Node("F7", 7)
    val nodeE6 = Node("E6", 6)

    q.nqueue(nodeA5)
    q.nqueue(nodeB8)
    q.nqueue(nodeC3)
    q.nqueue(nodeD10)
    q.nqueue(nodeF7)
    q.nqueue(nodeE6)

    assert(q.getQ() === ArrayBuffer(
      nodeD10,
      nodeB8,
      nodeF7,
      nodeE6,
      nodeA5,
      nodeC3)
    )

  }

  test("Can dequeue the most priority") {
    val q = new MaxPriorityQueue()
    val nodeD10 = Node("D10", 10)
    val nodeC3 = Node("C3", 3)
    val nodeB8 = Node("B8", 8)
    val nodeA5 = Node("A5", 5)
    val nodeF7 = Node("F7", 7)
    val nodeE6 = Node("E6", 6)

    q.nqueue(nodeA5)
    q.nqueue(nodeB8)
    q.nqueue(nodeC3)
    q.nqueue(nodeD10)
    q.nqueue(nodeF7)
    q.nqueue(nodeE6)

    assert(q.dqueue() == nodeD10)
    assert(q.dqueue() == nodeB8)
  }
}
