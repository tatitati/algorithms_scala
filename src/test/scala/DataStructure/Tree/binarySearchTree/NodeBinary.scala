package DataStructure.Tree.binarySearchTree

import scala.collection.mutable.ArrayBuffer

class NodeBinary {
  private var left: Option[NodeBinary] = None
  private var right: Option[NodeBinary] = None
  var value: Int = -1
  private var parent: Option[NodeBinary] = None

  def this(
            leftNode: Option[NodeBinary],
            rightNode: Option[NodeBinary],
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

  def getLeft(): Option[NodeBinary] = left
  def getRight(): Option[NodeBinary] = right
  def getParent(): Option[NodeBinary] = parent

  def isBiggerThan(node: NodeBinary): Boolean = {
    this.value > node.value
  }

  def setLeft(leftNode: Option[NodeBinary]): NodeBinary = {
    leftNode match {
      case Some(node) => node.setParent(Some(this))
      case None =>
    }

    left = leftNode
    this
  }

  def setRight(rightNode: Option[NodeBinary]): NodeBinary = {
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

  def searchNodeInTree(value: Int, n: NodeBinary): NodeBinary = {
    this match {
      case t if (value < t.value) => t.getLeft().get.searchNodeInTree(value, n)
      case t if (value > t.value) => t.getRight().get.searchNodeInTree(value, n)
      case _ => this
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

//  def precesor(i: Int): Int = {
//
//  }

  private def setParent(node: Option[NodeBinary]): NodeBinary = {
    parent = node
    this
  }
}
