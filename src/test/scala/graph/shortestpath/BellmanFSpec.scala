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
   weight: Double
)

case class Vertex(
   var distance: Double,
   edgesDest: List[Edge]
)


class BellmanFSpec extends FunSuite {

  val inf = Double.PositiveInfinity
  val graph: SortedMap[String, Vertex] = SortedMap(
    "A" -> Vertex(
      0,
      List(Edge("A", "B", -1), Edge("A", "C", 4)) // from this vertex, I can go (through these edges) to these vertexes
    ),
    "B" -> Vertex(
      inf,
      List(Edge("B", "C", 3),Edge("B", "D", 2), Edge("B", "E", 2))
    ),
    "C" -> Vertex(
      inf,
      List()
    ),
    "D" -> Vertex(
      inf,
      List(Edge("D", "B", 1),Edge("D", "C", 5))
    ),
    "E" -> Vertex(
      inf,
      List(Edge("E", "D", -3))
    )
  )


  def initialize_single_source(graph: SortedMap[String, Vertex], start: String) = {
    for((keyVertex, fromVertex) <- graph) {
      for(edge <- fromVertex.edgesDest) {
        var toVertex = graph.get(edge.to).get

        if(fromVertex.distance != inf || fromVertex.distance + edge.weight < toVertex.distance) {
          toVertex.distance = fromVertex.distance + edge.weight
        }


      }
    }
  }


  test("I can modify object inside of inmutable List") {
    initialize_single_source(graph, "A")
  }
}
