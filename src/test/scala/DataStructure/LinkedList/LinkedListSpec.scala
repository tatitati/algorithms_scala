package DataStructure.LinkedList

import org.scalatest.FunSuite

class LinkedListSpec extends FunSuite {

  test("I can create a node with some value") {
    val nodeA = ListNodeUnidirectional("some value")
    assert(nodeA.value === "some value")
    assert(nodeA.next === None)
  }

  test("I can chain nodes") {
    val root = ListNodeUnidirectional("node A")
    val nodeB = ListNodeUnidirectional("node B")
    val nodeC = ListNodeUnidirectional("node C")
    root.addNext(nodeB).addNext(nodeC)

    assert(root.count === 3)
  }
}
