package graph.shortestpath.Dijkstra

import scala.collection.SortedMap
import scala.collection.immutable.ListMap

trait Dijkstra {

  def solve(graph: ListMap[String, ListMap[String, Int]]): (SortedMap[String, Double], SortedMap[String, String]) = {
    val infinity = Double.PositiveInfinity
    var dist = SortedMap[String, Double]()
    var pred = SortedMap[String, String]()

    //init
    for((vKey, _ )<- graph) {
      dist += (vKey -> infinity)
      pred += (vKey -> "")
    }
    dist += ("A" -> 0)


    // process
    for((u, adjs) <- graph) { // u = current node
      println("- " + u + " (" + dist(u) + " )")

      for((v, d) <- adjs if v != "") {   // v = adjs nodes
        println("       ------- "+ d + " --->" + v)

        if(dist(u) + d < dist(v)) {
          dist += (v -> (dist(u) + d))
          pred += (v -> u)
        }
      }
    }

    (dist, pred)
  }
}
