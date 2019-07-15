package graph.shortestpath.Dijkstra

import DataStructure.queue.{MinPriorityQueue, Node}

import scala.collection.SortedMap
import scala.collection.immutable.ListMap
import scala.collection.mutable.ArrayBuffer

trait Dijkstra {

  def solve(graph: ListMap[String, ListMap[String, Int]]): (SortedMap[String, Double], SortedMap[String, String]) = {
    val infinity = Double.PositiveInfinity
    var dist = SortedMap[String, Double]()
    var pred = SortedMap[String, String]()
    var q = new MinPriorityQueue()
    var visited: ArrayBuffer[String] = ArrayBuffer()

    //init
    for((vKey, _ )<- graph) {
      dist += (vKey -> infinity)
      pred += (vKey -> "")
    }
    dist += ("A" -> 0)
    q.nqueue(Node("A", 0))

    // process
    while(!q.isEmpty()) {
      var node = q.dqueue()
      var u = node.data.asInstanceOf[String]

      println("- " + u + " (" + dist(u) + " )")

      for((v, d) <- graph(u) if (v != "" && !visited.contains(v))) {   // v = adjs nodes
        println("       ------- "+ d + " --->" + v)

        if(dist(u) + d < dist(v)) {
          dist += (v -> (dist(u) + d))
          pred += (v -> u)

          if(!visited.contains(v)) {
            q.nqueue(Node(v, dist(v)))
          }
        }
      }

      visited += u
    }

    (dist, pred)
  }
}
