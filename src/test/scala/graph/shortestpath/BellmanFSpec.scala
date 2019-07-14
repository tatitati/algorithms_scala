package graph.shortestpath

import org.scalatest.FunSuite
import DataStructure.queue.Queue
import graph.traversal.BFS.NodeColor
import scala.collection.SortedMap
import scala.collection.mutable.ListMap
import scala.collection.mutable.ArrayBuffer


case class Edge(
                 from: String,
                 to: String,
                 w: Double
)

case class Vertex(
                   id: String,
                   var d: Double,
                   edgesDest: List[Edge]
)


class BellmanFSpec extends FunSuite {

  val inf = Double.PositiveInfinity
  val graph: SortedMap[String, Vertex] = SortedMap(
    "A" -> Vertex(
      "A",
      0,
      List(Edge("A", "B", -1), Edge("A", "C", 4)) // from this vertex, I can go (through these edges) to these vertexes
    ),
    "B" -> Vertex(
      "B",
      inf,
      List(Edge("B", "C", 3),Edge("B", "D", 2), Edge("B", "E", 2))
    ),
    "C" -> Vertex(
      "C",
      inf,
      List()
    ),
    "D" -> Vertex(
      "D",
      inf,
      List(Edge("D", "B", 1),Edge("D", "C", 5))
    ),
    "E" -> Vertex(
      "E",
      inf,
      List(Edge("E", "D", -3))
    )
  )


  def bellmanford(graph: SortedMap[String, Vertex]) = {
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


  test("I can modify object inside of inmutable List") {
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
}
