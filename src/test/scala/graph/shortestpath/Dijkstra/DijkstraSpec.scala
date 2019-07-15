package graph.shortestpath.Dijkstra

import org.scalatest.FunSuite

import scala.collection.SortedMap
import scala.collection.immutable.ListMap

class DijkstraSpec extends FunSuite with Dijkstra {

//  test("Dijkstra 1") {
//    val graph = ListMap(
//      "A" -> ListMap("B" -> 3, "C" -> 5, "D" -> 9),
//      "B" -> ListMap("A" -> 3, "C" -> 3, "D" -> 4, "E" -> 7),
//      "C" -> ListMap("A" -> 5, "B" -> 3, "D" -> 2, "E" -> 6, "F" -> 3),
//      "D" -> ListMap("A" -> 9, "B" -> 4, "C" -> 2, "E" -> 2, "F" -> 2),
//      "E" -> ListMap("B" -> 7, "C" -> 6, "D" -> 2, "F" -> 5),
//      "F" -> ListMap("C" -> 3, "D" -> 2, "E" -> 5)
//    )
//    val (dist, pred) = solve(graph)
//
//    assert(dist == SortedMap("A" -> 0.0, "B" -> 3.0, "C" -> 5.0, "D" -> 7.0, "E" -> 9.0, "F" -> 8.0))
//    assert(pred == SortedMap("A" -> "" , "B" -> "A", "C" -> "A", "D" -> "B", "E" -> "D", "F" -> "C"))
//  }

//  test("Dijkstra 2") {
//    val graph = ListMap(
//      "A"   -> ListMap("LP" -> 5, "Poster" -> 0),
//      "LP"     -> ListMap("Poster" -> -7),
//      "Poster" -> ListMap("Drums" -> 35),
//      "Drums"  -> ListMap("" -> 0)
//    )
//    val (dist, pred) = solve(graph)
//
//    assert(dist == SortedMap("A" -> 0.0, "B" -> 3.0, "C" -> 5.0, "D" -> 7.0, "E" -> 9.0, "F" -> 8.0))
//    assert(pred == SortedMap("A" -> "" , "B" -> "A", "C" -> "A", "D" -> "B", "E" -> "D", "F" -> "C"))
//  }

  test("Dijkstra 3") {
    val graph = ListMap(
      "A"   -> ListMap("B" -> 1, "C" -> 2),
      "B" -> ListMap("" -> 0),
      "C" -> ListMap("B" -> -1)
    )
    val (dist, pred) = solve(graph)

    assert(dist == SortedMap("A" -> 0.0, "B" -> 3.0, "C" -> 5.0, "D" -> 7.0, "E" -> 9.0, "F" -> 8.0))
    assert(pred == SortedMap("A" -> "" , "B" -> "A", "C" -> "A", "D" -> "B", "E" -> "D", "F" -> "C"))
  }
}
