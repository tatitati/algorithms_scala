package DataStructure.queue

import org.scalatest.FunSuite

import scala.collection.mutable.ArrayBuffer

class MinPriorityQueueSpec extends FunSuite {
  test("I can insert most basic cases") {
    val q = new MinPriorityQueue()
    val nodeD10 = Node("D10", 10)
    val nodeC3 = Node("C3", 3)

    q.nqueue(nodeD10)
    q.nqueue(nodeC3)

    assert(q.getQ() === ArrayBuffer(
      nodeC3,
      nodeD10)
    )

  }

  test("I can set new nodes in the proper position") {
    val q = new MinPriorityQueue()
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
      nodeC3,
      nodeA5,
      nodeE6,
      nodeF7,
      nodeB8,
      nodeD10)
    )

  }

  test("Can dequeue the most priority") {
    val q = new MinPriorityQueue()
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

    assert(q.dqueue() == nodeC3)
    assert(q.dqueue() == nodeA5)
  }
}
