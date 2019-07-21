package graph.shortestpath.FloydWarshall

import org.scalatest.FunSuite
import scala.collection.mutable.Map
import scala.collection.immutable.ListMap
import scala.collection.mutable.ArrayBuffer

class FloydWarshallSpec extends FunSuite {

  def floydWarshall(G: ListMap[String, ListMap[String, Int]]): Unit = {
      val infinity = Double.PositiveInfinity
      var dist: Map[String, Map[String, Double]] = Map()
      var pred: Map[String, Map[String, String]] = Map()

      // init
      for((u, adjs) <- G) {
        var distsU: Map[String, Double] = Map()
        var predU: Map[String, String] = Map()

        for((u, _) <- G) {
          if (u == u) {
            distsU += (u -> 0)
          } else {
            distsU += (u -> infinity)
          }

          predU += (u -> "")
        }

        pred += (u -> predU)
        dist += (u -> distsU)

        // set initial/original distances
        for((v, w) <- adjs) {
          dist(u).update(v, w)
        }

        // main 
      }
  }

  test("Floyd-Warshall"){
    val graph = ListMap(
        "1" -> ListMap("3" -> -2),
        "2" -> ListMap("1" -> 4, "3" -> 3),
        "3" -> ListMap("4" -> 2),
        "4" -> ListMap("2" -> -1)
    )

    floydWarshall(graph)


  }

}
