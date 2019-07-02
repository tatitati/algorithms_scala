package DataStructure.Tree.binarySearchTree

import org.scalatest.FunSuite
import scala.collection.mutable.ArrayBuffer

class BinarySearchTreeSpec extends FunSuite {

  def buildTree(): NodeBinary = {
    // leaves
    val node1 = new NodeBinary(None, None, 1)
    val node4 = new NodeBinary(None, None, 4)
    val node6 = new NodeBinary(None, None, 6)
    val node9 = new NodeBinary(None, None, 9)
    // parent leaves
    val node3 = new NodeBinary(Some(node1), Some(node4), 3)
    val node7 = new NodeBinary(Some(node6), Some(node9), 7)
    // root
    new NodeBinary(Some(node3), Some(node7), 5)
  }

  test("Can compare, get left, and get parent") {
    val node1 = new NodeBinary(None, None, 1)
    val node4 = new NodeBinary(None, None, 4)
    val node3 = new NodeBinary(Some(node1), Some(node4), 3)

    assert(node1.getParent() === Some(node3))
    assert(node3.getLeft() === Some(node1))
    assert(node4.isBiggerThan(node1) === true)
  }

  test("can find the minimum") {
    var tree = buildTree()
    assert(tree.findMin() === 1)
  }

  test("can find the maximum") {
    var tree = buildTree()
    assert(tree.findMax() === 9)
  }

  test("can search") {
    var tree = buildTree()
    val n = 6
    var trace = ArrayBuffer[Int]()

    assert(tree.searchValueInTree(n, trace) === ArrayBuffer(5, 7, 6))
  }

  test("can inorder traversal") {
      val tree = buildTree()
      val result = tree.inOrderTraversal(new ArrayBuffer())

      assert(result === ArrayBuffer(1,3,4,5,6,7,9))
  }

}