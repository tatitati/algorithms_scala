package queue

import org.scalatest.FunSuite

import scala.collection.mutable.ArrayBuffer

case class Node(
    val msg: String,
    val priority: Int,
    private var next: Option[Node] = None
) {
  def setNext(node: Option[Node]): Unit = next = node
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

    val nodeC = Node("C", 7)
    val nodeB = Node("B", 6, Some(nodeC))
    val nodeA = Node("A", 5, Some(nodeB))

    assert(traverse(Some(nodeA), ArrayBuffer()) === ArrayBuffer("A", "B", "C"))
  }

//  test("higher priority are placed at the start of the queue") {
//    def insert(list: Node, node: Node): Node = {
//
//    }
//
//    val nodeC = Node("C", 7)
//    val nodeB = Node("B", 6)
//    val nodeA = Node("A", 5)
//
//
//  }
}
