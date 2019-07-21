package graph.shortestpath.FloydWarshall

import org.scalatest.FunSuite
import scala.collection.mutable.Map
import scala.collection.mutable.ListMap

class FloydWarshallSpec extends FunSuite {

  def floydWarshall(G: ListMap[String, ListMap[String, Int]]):
        (
          ListMap[String, ListMap[String, Double]],
          ListMap[String, ListMap[String, String]]
        )
      = {
      val infinity = Double.PositiveInfinity
      var dist: ListMap[String, ListMap[String, Double]] = ListMap()
      var pred: ListMap[String, ListMap[String, String]] = ListMap()

      // init
      for((u, adjs) <- G) {
        var distsU: ListMap[String, Double] = ListMap()
        var predU: ListMap[String, String] = ListMap()

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

    (dist, pred)

  }

  test("Floyd-Warshall"){
    val graph = ListMap(
        "1" -> ListMap("3" -> -2),
        "2" -> ListMap("1" -> 4, "3" -> 3),
        "3" -> ListMap("4" -> 2),
        "4" -> ListMap("2" -> -1)
    )

    var (dist, pred) = floydWarshall(graph)

    assert(dist == ListMap(
      "1" -> ListMap("2" -> -1.0, "1" -> 0.0, "4" -> 0.0, "3" -> -2.0),
      "2" -> ListMap("2" -> 0.0,  "1" -> 4.0, "4" -> 4.0, "3" -> 2.0),
      "3" -> ListMap("2" -> 1.0,  "1" -> 5.0, "4" -> 2.0, "3" -> 0.0),
      "4" -> ListMap("2" -> -1.0, "1" -> 3.0, "4" -> 0.0, "3" -> 1.0)
    )
    )


    assert(pred == ListMap(
      "1" -> ListMap("2" -> "4", "1" -> "",  "4" -> "3", "3" -> "1"),
      "2" -> ListMap("2" -> "",  "1" -> "2", "4" -> "3", "3" -> "1"),
      "3" -> ListMap("2" -> "4", "1" -> "2", "4" -> "3", "3" -> ""),
      "4" -> ListMap("2" -> "4", "1" -> "2", "4" -> "",  "3" -> "1")
    ))
  }

}
