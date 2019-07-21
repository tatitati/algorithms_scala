package graph.shortestpath.FloydWarshall

import org.scalatest.FunSuite
import scala.collection.mutable.Map
import scala.collection.immutable.ListMap

class FloydWarshallSpec extends FunSuite {

  def floydWarshall(G: ListMap[String, ListMap[String, Int]]): Unit = {
      val infinity = Double.PositiveInfinity
      var dist: Map[String, Map[String, Double]] = Map()
      var pred: Map[String, Map[String, String]] = Map()

      // init
      for((u, adjs) <- G) {
        var distsU: Map[String, Double] = Map()
        var predU: Map[String, String] = Map()

        for((v, _) <- G) {
          if (u == v) {
            distsU += (v -> 0)
          } else {
            distsU += (v -> infinity)
          }

          predU += (v -> "")
        }

        pred += (u -> predU)
        dist += (u -> distsU)

        // set initial/original distances
        println(dist)
        for((v, w) <- adjs) {
          dist(u).update(v, w)
          pred(u).update(v, u)
        }

      }

      // main
      for((through_k, _) <- G) {
        for((u, _) <- G) {
          for((v, _) <- G) {
              var newdist = dist(u)(through_k) + dist(through_k)(v)
              if (newdist < dist(u)(v)) {
                dist(u).update(v, newdist)
                pred(u).update(v, pred(through_k)(v))
              }
          }
        }
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
