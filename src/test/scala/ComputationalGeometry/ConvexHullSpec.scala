package ComputationalGeometry

import org.scalatest.FunSuite

class ConvexHullSpec extends FunSuite {

    def convexhull(P: List[List[Int]]): Unit = {
      val n = P.size
      var upper = List(P(0), P(1))

      for(i <- 2 to n-1) {
        upper ++: P(i)
      }

      println(upper)

    }

    test("Convex-hull") {

      // to-do the sorting should happen into the algorithm as well...
      val P = List(
        List(1, 2),
        List(2, 1),
        List(2, 2),
        List(2, 3),
        List(3, 3),
        List(3, 4),
        List(4, 1),
        List(4, 3),
        List(4, 5),
        List(5, 2)
      )

      convexhull(P)
    }
}
