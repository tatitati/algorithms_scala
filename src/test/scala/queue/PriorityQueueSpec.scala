package queue

import org.scalatest.FunSuite
import scala.collection.mutable.ArrayBuffer

class Node(
    val msg: String = "",
    val priority: Int = 0
) {
  private var next: Option[Node] = None

  override def toString(): String = {
    s"""
       |
       |priority: $priority
       |msg: $msg
       |next:
       |  $next
       |
     """.stripMargin
  }

  def setNext(nextNode: Option[Node]): Node = {
    next = nextNode
    this
  }

  def getNext(): Option[Node] = next

  def traverse(msgs: ArrayBuffer[String]): ArrayBuffer[String] = {
    msgs += this.msg

    this.getNext() match {
      case Some(node) => node.traverse(msgs)
      case None => msgs
    }
  }

  def addNode(newNode: Node): Node = {
    if(this.priority > newNode.priority){
      this.getNext() match {
        case Some(nextNode) => nextNode.addNode(newNode)
        case None => this.setNext(Some(newNode))
      }
      this
    } else {
      newNode.setNext(Some(this))
      newNode
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
    val nodeD = new Node("D10", 10)
    val nodeC = new Node("C3", 3)
    val nodeB = new Node("B8", 8)
    val nodeA = new Node("A5", 5)

    var result1 = nodeA.addNode(nodeB)
    var result2 = result1.addNode(nodeC)
    var result3 = result2.addNode(nodeD)

    assert(result3.traverse(ArrayBuffer()) === ArrayBuffer("D10", "B8", "A5", "C3"))
  }
}
