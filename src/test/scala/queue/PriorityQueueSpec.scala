package queue

import org.scalatest.FunSuite

import scala.collection.mutable.ArrayBuffer

case class Node(
    val msg: String,
    val priority: Int
) {
  private var next: Option[Node] = None
  private var previous: Option[Node] = None

  def setNext(nextNode: Option[Node]): Unit = {
    if(nextNode != None) {
      nextNode.get.setPrevious(Some(this))
    }
    
    next = nextNode
  }

  def setPrevious(previousNode: Option[Node]): Unit = {
    if(previousNode != None) {
      previousNode.get.setNext(Some(this))
    }

    previous = previousNode
  }

  def getNext(): Option[Node] = next
  def getPrevious(): Option[Node] = previous
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

//    val nodeC = Node("C", 7)
    val nodeB = Node("B", 6)
    val nodeA = Node("A", 5).setNext(Some(nodeB))
    println(nodeA)

//    assert(traverse(Some(nodeA), ArrayBuffer()) === ArrayBuffer("A", "B", "C"))
  }


}
