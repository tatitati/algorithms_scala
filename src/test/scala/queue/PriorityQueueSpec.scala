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
}

class PriorityQueueSpec extends FunSuite {

  test("I can traverse across multiple nodes") {
    def traverse(node: Option[Node], msgs: ArrayBuffer[String]): ArrayBuffer[String] = {
      node match {
        case Some(node) => {
          msgs += node.msg
          traverse(node.getNext(), msgs)
        }
        case _ => msgs
      }

    }

    val nodeC = new Node("C", 0)
    val nodeB = new Node("B", 2).setNext(Some(nodeC))
    val nodeA = new Node("A", 100).setNext(Some(nodeB))

    assert(traverse(Some(nodeA), ArrayBuffer()) === ArrayBuffer("A", "B", "C"))
  }

  test("can place new node in proper place") {
    def addNode(currentNode: Node, newNode: Node): Node = {
      if(currentNode.priority > newNode.priority){
        currentNode.getNext() match {
          case Some(nextNode) => addNode(nextNode, newNode)
          case None => {
            currentNode.setNext(Some(newNode))
          }
        }
        currentNode
      } else {
        newNode.setNext(Some(currentNode))
        newNode
      }
    }

    def traverse(node: Option[Node], msgs: ArrayBuffer[String]): ArrayBuffer[String] = {
      node match {
        case Some(node) => {
          msgs += node.msg
          traverse(node.getNext(), msgs)
        }
        case _ => msgs
      }

    }

    val nodeD = new Node("D10", 10)
    val nodeC = new Node("C3", 3)
    val nodeB = new Node("B8", 8)
    val nodeA = new Node("A5", 5)

    var result1 = addNode(nodeA, nodeB)
    var result2 = addNode(result1, nodeC)
    var result3 = addNode(result2, nodeD)

    assert(traverse(Some(result3), ArrayBuffer()) === ArrayBuffer("D10", "B8", "A5", "C3"))
  }
}
