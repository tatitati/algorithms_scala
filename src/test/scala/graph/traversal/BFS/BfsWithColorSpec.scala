package graph.traversal.BFS

import org.scalatest.FunSuite

class BfsWithColorSpec extends FunSuite {

  test("I can modify object inside of inmutable List") {
    val node1 = NodeColor("a value")
    val adjacencyList = List(
      node1
    )

    for(node <- adjacencyList) {
      node.color = "modified"
    }

    assert(adjacencyList(0).color === "modified")
  }

  test("I can iterate") {
    val nodeA = NodeColor("A")
    val nodeB = NodeColor("B")
    val nodeC = NodeColor("C")
    val nodeD = NodeColor("D")
    val nodeE = NodeColor("E")
    val nodeF = NodeColor("F")
    val nodeG = NodeColor("F")
    val nodeH = NodeColor("H")
    val nodeI = NodeColor("I")


    val adjacencyList = List(
      nodeA.conn(nodeB).conn(nodeC),
      nodeB.conn(nodeA).conn(nodeF),
      nodeC.conn(nodeA),
      nodeD.conn(nodeA).conn(nodeG),
      nodeE.conn(nodeA),
      nodeF.conn(nodeB).conn(nodeH),
      nodeG.conn(nodeD).conn(nodeI),
      nodeH.conn(nodeF),
      nodeI.conn(nodeG)
    )
  }

}
