package graph.traversal.BFS

import org.scalatest.FunSuite
import DataStructure.queue.Queue
import scala.collection.immutable.ListMap

class BfsWithColorSpec extends FunSuite {

  def bsf_iterate(
           Adj: ListMap[NodeColor, List[NodeColor]],
           s: NodeColor
      ): Unit = {
    val queue = new Queue[NodeColor]()
    s.color = "gray"
    queue.nque(s)

    while(!queue.isEmpty()) {
      var u = queue.dque()  // all items in the queue are gray (discovered, ready to explore)

      for(v <- Adj.get(u).get) {
        if(v.color == "white") {
          v.color = "gray"
          queue.nque(v)
        }
      }

      u.color = "black" // we have discovered all the inmediate-destinations of the current node, so it has been processed
    }
  }


  test("I can modify object inside of inmutable List") {
    val node1 = NodeColor("a value")
    assert(node1.color === "white")
    val adjacencyList = List(
      node1
    )

    for(node <- adjacencyList) {
      node.color = "gray"
    }

    assert(adjacencyList(0).color === "gray")
  }

  test("I can iterate") {
    val nodeA = NodeColor("A")
    val nodeB = NodeColor("B")
    val nodeC = NodeColor("C")
    val nodeD = NodeColor("D")
    val nodeE = NodeColor("E")
    val nodeF = NodeColor("F")
    val nodeG = NodeColor("G")
    val nodeH = NodeColor("H")
    val nodeI = NodeColor("I")

    val adjacencyList = ListMap(
      nodeA -> List(nodeB, nodeC),
      nodeB -> List(nodeA, nodeF),
      nodeC -> List(nodeA),
      nodeD -> List(nodeA, nodeG),
      nodeE -> List(nodeA),
      nodeF -> List(nodeB,nodeH),
      nodeG -> List(nodeD,nodeI),
      nodeH -> List(nodeF),
      nodeI -> List(nodeG)
    )

    bsf_iterate(adjacencyList, nodeA)
  }
}
