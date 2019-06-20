package binarySearchTree

import org.scalatest.FunSuite

import scala.collection.mutable.ArrayBuffer

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

  test("Can compare, get left, and get parent") {
    val node1 = new Node(None, None, 1)
    val node4 = new Node(None, None, 4)
    val node3 = new Node(Some(node1), Some(node4), 3)

    assert(node1.getParent() === Some(node3))
    assert(node3.getLeft() === Some(node1))
    assert(node4.isBiggerThan(node1) === true)
  }

  def buildTree(): Node = {
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

  test("can find the minimum") {
    var tree = buildTree()

    while(tree.getLeft() != None) {
      tree = tree.getLeft().get
    }

    assert(tree.value === 1)
  }

  test("can find the maximum") {
    var tree = buildTree()

    while(tree.getRight() != None) {
      tree = tree.getRight().get
    }

    assert(tree.value === 9)
  }

  test("can search") {
    def search(tree: Node, n: Int, trace: ArrayBuffer[Int]): ArrayBuffer[Int] = {
      trace += tree.value

      if (n < tree.value) {
        if(tree.getLeft() == None) {
          throw new Exception("Trying to found a number that doesnt exist in the tree")
        }

        return search(tree.getLeft().get, n, trace)
      }

      if (n > tree.value) {
        if(tree.getRight() == None) {
          throw new Exception("Trying to found a number that doesnt exist in the tree")
        }

        return search(tree.getRight().get, n, trace)
      }

      return trace
    }


    var tree = buildTree()
    val n = 6
    var trace = ArrayBuffer[Int]()
    assert(search(tree, n, trace) === ArrayBuffer(5, 7, 6))
  }

}
