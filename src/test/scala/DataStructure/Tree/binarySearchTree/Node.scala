package DataStructure.Tree.binarySearchTree

import scala.collection.mutable.ArrayBuffer

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

  def findMin(): Int = {
    this.getLeft() match {
      case Some(node) => node.getLeft().get.findMin()
      case None => this.value
    }
  }

  def findMax(): Int = {
    this.getRight() match {
      case Some(node) => node.getRight().get.findMax()
      case None => this.value
    }
  }

  def searchValueInTree(n: Int, trace: ArrayBuffer[Int]): ArrayBuffer[Int] = {
    trace += this.value

    this match {
      case t if (n < t.value) => t.getLeft().get.searchValueInTree(n, trace)
      case t if (n > t.value) => t.getRight().get.searchValueInTree(n, trace)
      case _ => trace
    }
  }

  def inOrderTraversal(result: ArrayBuffer[Int]): ArrayBuffer[Int] = {
    // left
    if(this.getLeft() != None) {
      this.getLeft().get.inOrderTraversal(result)
    }

    // parent
    result += this.value

    // right
    if(this.getRight() != None) {
      this.getRight().get.inOrderTraversal(result)
    }

    result
  }

  private def setParent(node: Option[Node]): Node = {
    parent = node
    this
  }
}
