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
    assert(tree.findMin().value === 1)
  }

  test("can find the maximum") {
    var tree = buildTree()
    assert(tree.findMax().value === 9)
  }

  test("Can find Node") {
    val tree = buildTree()
    var node = tree.searchNodeInTree(6)

    assert(node.value === 6)
  }

  test("can inorder traversal") {
      val tree = buildTree()
      val result = tree.inOrderTraversal(new ArrayBuffer())

      assert(result === ArrayBuffer(1,3,4,5,6,7,9))
  }

  test("can find precessor") {
    var tree = buildTree()
    assert(tree.precesor(5).value === 4)
  }

  test("can find sucessor") {
    var tree = buildTree()
    assert(tree.sucesor(5).value === 6)
  }

  test("I can insert a node to the tree") {
    var root = new NodeBinary(None, None, 5)
    var node7 = new NodeBinary(None, None, 7)
    var node8 = new NodeBinary(None, None, 8)
    var node4 = new NodeBinary(None, None, 4)
    var node3 = new NodeBinary(None, None, 3)

    root.insert(node7)
    root.insert(node8)
    root.insert(node4)
    root.insert(node3)

    val result = root.inOrderTraversal(new ArrayBuffer())
    assert(result == ArrayBuffer(3, 4, 5, 7, 8))
  }
}
