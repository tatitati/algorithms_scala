package queue

import org.scalatest.FunSuite
import scala.collection.mutable.ArrayBuffer

class Node(
            val data: String = "",
            val priority: Int = 0
) {
  private var next: Option[Node] = None

  def getNext(): Option[Node] = next
  def setNext(nextNode: Option[Node]): Node = {
    next = nextNode
    this
  }

  def addNode(newNode: Node): Node = {
    if(newNode.priority > this.priority){
      newNode.setNext(Some(this))
      newNode
    } else {
      this.getNext() match {
        case Some(nextNode) => nextNode.addNode(newNode)
        case None => this.setNext(Some(newNode))
      }
      this
    }
  }

  def traverse(journey: ArrayBuffer[String]): ArrayBuffer[String] = {
    journey += this.data

    this.getNext() match {
      case Some(node) => node.traverse(journey)
      case None => journey
    }
  }
}

class PriorityQueueSpec extends FunSuite {

  test("I can traverse across multiple nodes") {
    val nodeC = new Node("C", 0)
    val nodeB = new Node("B", 2).setNext(Some(nodeC))
    val nodeA = new Node("A", 100).setNext(Some(nodeB))

    assert(nodeA.traverse(ArrayBuffer()) === ArrayBuffer("A", "B", "C"))
  }

  test("can place new node in proper place") {
    val nodeD10 = new Node("D10", 10)
    val nodeC3 = new Node("C3", 3)
    val nodeB8 = new Node("B8", 8)
    val nodeA5 = new Node("A5", 5)
    val nodeF7 = new Node("F7", 7)
    val nodeE6 = new Node("E6", 6)

    val queue = nodeA5
      .addNode(nodeB8)
      .addNode(nodeC3)
      .addNode(nodeD10)
      .addNode(nodeF7)
      .addNode(nodeE6)

    assert(queue.traverse(ArrayBuffer()) === ArrayBuffer("D10", "B8", "F7", "E6", "A5", "C3"))
  }
}
