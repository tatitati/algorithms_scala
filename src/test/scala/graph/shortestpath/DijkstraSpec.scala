package graph.shortestpath

import org.scalatest.FunSuite

import scala.collection.SortedMap
import DataStructure.Stack.Stack

class DijkstraSpec extends FunSuite {

  val inf = Double.PositiveInfinity
  val graph = SortedMap(
    "A" -> SortedMap("B" -> 3, "C" -> 5, "D" -> 9),
    "B" -> SortedMap("A" -> 3, "C" -> 3, "D" -> 4, "E" -> 7),
    "C" -> SortedMap("A" -> 5, "B" -> 3, "D" -> 2, "E" -> 6, "F" -> 3),
    "D" -> SortedMap("A" -> 9, "B" -> 4, "C" -> 2, "E" -> 2, "F" -> 2),
    "E" -> SortedMap("B" -> 7, "C" -> 6, "D" -> 2, "F" -> 5),
    "F" -> SortedMap("C" -> 3, "D" -> 2, "E" -> 5)
  )

  def dijkstra(): (SortedMap[String, Double], SortedMap[String, String]) = {
    var dist = SortedMap[String, Double]()
    var pred = SortedMap[String, String]()

    //init
    for((vKey, _ )<- graph) {
      dist += (vKey -> inf)
      pred += (vKey -> "")
    }
    dist += ("A" -> 0)


    // process
    for((u, adjs) <- graph) { // u = current node
      for((v, d) <- adjs) {   // v = adjs nodes
        if(dist(u) + d < dist(v)) {
          dist += (v -> (dist(u) + d))
          pred += (v -> u)
        }
      }
    }

    (dist, pred)
  }

  test("Dijkstra") {
    val (dist, pred) = dijkstra()

    assert(dist == SortedMap("A" -> 0.0, "B" -> 3.0, "C" -> 5.0, "D" -> 7.0, "E" -> 9.0, "F" -> 8.0))
    assert(pred == SortedMap("A" -> "" , "B" -> "A", "C" -> "A", "D" -> "B", "E" -> "D", "F" -> "C"))
  }
}
