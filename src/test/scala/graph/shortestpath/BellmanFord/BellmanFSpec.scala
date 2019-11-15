package graph.shortestpath.BellmanFord

import org.scalatest.FunSuite
import DataStructure.queue.Queue
import graph.traversal.BFS.NodeColor
import scala.collection.SortedMap
import scala.collection.mutable.ListMap
import scala.collection.mutable.ArrayBuffer

object Edge {
  def apply(givenSpec: String): Edge = {
    val Array(fromto , in) = givenSpec.split("/")
    val Array(from , to) = fromto.split(">")
    Edge(from ,to , in.toInt)
  }
}

object Node {
  def apply(nodeName: String, in: Double, givenEdges: List[String] = List[String]()): Node = {
      Node(nodeName, givenEdges.map(Edge(_)), in)
  }
}

case class Edge(from: String, to: String, w: Double)
case class Node(id: String, edgesDest: List[Edge], var d: Double)


class BellmanFSpec extends FunSuite {

  val inf = Double.PositiveInfinity
  val graph: SortedMap[String, Node] = SortedMap(
    "A" -> Node("A", 0,   List("A>B/-1", "A>C/4")),
    "B" -> Node("B", inf, List("B>C/3", "B>D/2", "B>E/2")),
    "C" -> Node("C", inf),
    "D" -> Node("D", inf, List("D>B/1", "D>C/5")),
    "E" -> Node("E", inf, List("E>D/-3"))
  )


  def bellmanford(graph: SortedMap[String, Node]) = {
    // relax
    for((keyVertex, u) <- graph) {
      for(edge <- u.edgesDest) { // u = current vertex (from vertex)
        var v = graph.get(edge.to).get // v = adj vertext (to vertex)

        if(u.d != inf && u.d + edge.w < v.d) {
          v.d = u.d + edge.w
        }

      }
    }


    //TODO check negative cycles
  }


  test("Bellman ford") {
    bellmanford(graph)

    // summarize result
    var vertextWithWeights = SortedMap[String, Double]()
    for((keyVertex, fromVertex) <- graph) {
      vertextWithWeights = vertextWithWeights + (keyVertex -> fromVertex.d)
    }

    assert(vertextWithWeights === Map(
      "A" -> 0.0,
      "B" -> -1.0,
      "C" -> 2.0,
      "D" -> -2.0,
      "E" -> 1.0
      )
    )
  }

  test("I can get the required information from a friendly list"){
    val edge1 = Edge("p" ,"q" , 90)
    val edge2 = Edge("p>q/90")
    assert(edge1 == edge2)
  }

  test("Can create Nodes as well"){
    val n = Node("A", 0, List("p>q/90", "p>r/100"))

    assert(Node(
      "A",
      List(Edge("p","q",90.0), Edge("p","r",100.0)),
      0.0
    ) == n)

  }
}
