package binarySearchTree

import org.scalatest.FunSuite

class BinarySearchTreeSpec extends FunSuite {

  class Node {
    private var left: Option[Node] = None
    private var right: Option[Node] = None
    var value: Int = -1
    private var parent: Option[Node] = None

    def this(
            leftNode: Option[Node],
            rightNode: Option[Node],
            valueNode: Int,
            parentNode: Option[Node],
            ) {

      this()

      value = valueNode
      parent = parentNode

      leftNode match {
        case Some(node) => node.setParent(Some(this))
        case _ =>
      }

      rightNode match {
        case Some(node) => node.setParent(Some(this))
        case _ =>
      }

      left = leftNode
      right = rightNode
    }

    def getLeft(): Option[Node] = left
    def getRight(): Option[Node] = right
    def getParent(): Option[Node] = parent

    def setLeft(leftNode: Option[Node]): Node = {
      leftNode match {
        case Some(node) => node.setParent(Some(this))
        case None =>
      }

      left = leftNode
      this
    }

    def setRight(rightNode: Option[Node]): Node = {
      rightNode match {
        case Some(node) => node.setParent(Some(this))
        case None =>
      }
      right = rightNode
      this
    }

    def setParent(node: Option[Node]): Node = {
      parent = node
      this
    }
  }

  test("Can add child to a parent node") {
    val node1 = new Node(None, None, 1, None)
    val node4 = new Node(None, None, 4, None)
    val node3 = new Node(Some(node1), Some(node4), 3, None)

    assert(node1.getParent() === Some(node3))
  }

}
