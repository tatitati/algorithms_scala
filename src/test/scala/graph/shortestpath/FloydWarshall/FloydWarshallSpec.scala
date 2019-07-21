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
      for((vx, adjs) <- G) {
        dist(vx) = ListMap()
        pred(vx) = ListMap()

        for((vy, _) <- G) {
          dist(vx)(vy) = infinity
          dist(vx)(vx) = 0
          pred(vx)(vy) = ""
        }


        // set initial/original distances
        for((v, w) <- adjs) {
          dist(vx)(v) = w
          pred(vx)(v) = vx
        }

      }

      // main
      for((through_k, _) <- G) {
        for((u, _) <- G) {
          for((v, _) <- G) {
              var newdist = dist(u)(through_k) + dist(through_k)(v)
              if (newdist < dist(u)(v)) {
                dist(u)(v) = newdist
                pred(u)(v) = pred(through_k)(v)
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
      "1" -> ListMap("1" -> 0.0, "2" -> -1.0, "3" -> -2.0, "4" -> 0.0),
      "2" -> ListMap("1" -> 4.0, "2" -> 0.0,  "3" -> 2.0,  "4" -> 4.0),
      "3" -> ListMap("1" -> 5.0, "2" -> 1.0,  "3" -> 0.0,  "4" -> 2.0),
      "4" -> ListMap("1" -> 3.0, "2" -> -1.0, "3" -> 1.0,  "4" -> 0.0)
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
