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

  def findMin(): NodeBinary = {
    this.getLeft() match {
      case Some(node) => node.findMin()
      case None => this
    }
  }

  def findMax(): NodeBinary = {
    this.getRight() match {
      case Some(node) => node.findMax()
      case None => this
    }
  }

  def searchNodeInTree(value: Int): NodeBinary = {
    this match {
      case t if (value < t.value) => t.getLeft().get.searchNodeInTree(value)
      case t if (value > t.value) => t.getRight().get.searchNodeInTree(value)
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

  def precesor(i: Int): NodeBinary = {
    val node = searchNodeInTree(i)
    node.getLeft() match {
      case Some(node) => node.findMax()
      case _ => this
    }
  }

  def sucesor(i: Int): NodeBinary = {
    val node = searchNodeInTree(i)
    node.getRight() match {
      case Some(node) => node.findMin()
      case _ => this
    }
  }

  private def setParent(node: Option[NodeBinary]): NodeBinary = {
    parent = node
    this
  }
}
