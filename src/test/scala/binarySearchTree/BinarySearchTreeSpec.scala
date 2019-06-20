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
            valueNode: Int
            ) {

      this()

      value = valueNode

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

    def isBiggerThan(node: Node): Boolean = {
      this.value > node.value
    }

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
    val node1 = new Node(None, None, 1)
    val node4 = new Node(None, None, 4)
    val node3 = new Node(Some(node1), Some(node4), 3)

    assert(node1.getParent() === Some(node3))
  }

  test("Can access to left or right childs") {
    val node1 = new Node(None, None, 1)
    val node4 = new Node(None, None, 4)
    val node3 = new Node(Some(node1), Some(node4), 3)

    assert(node3.getLeft() === Some(node1))
  }

  test("can compare two nodes") {
    val node1 = new Node(None, None, 1)
    val node4 = new Node(None, None, 4)

    assert(node4.isBiggerThan(node1) === true)
  }

  def tree(): Node = {
    // leaves
    val node1 = new Node(None, None, 1)
    val node4 = new Node(None, None, 4)
    val node6 = new Node(None, None, 6)
    val node9 = new Node(None, None, 9)
    // parent leaves
    val node3 = new Node(Some(node1), Some(node4), 3)
    val node7 = new Node(Some(node6), Some(node9), 7)
    // root
    new Node(Some(node3), Some(node7), 5)
  }

  test("can print whole tree") {
    val tree = tree()

    
  }

}
