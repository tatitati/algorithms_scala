package graph.spaningtree

import org.scalatest.FunSuite
import scala.collection.mutable.ListMap
import scala.collection.mutable.ArrayBuffer

class PrimSpec extends FunSuite {

  test("Understand max/min in ListMap") {
    val list1 = ListMap(
      "A" -> 10,
      "B" -> 1,
      "C" -> 5,
      "D" -> 8
    )

    assert(list1.min == ("A", 10))
    assert(list1.max == ("D", 8))
    assert(list1.valuesIterator.max == 10)
    assert(list1.valuesIterator.min == 1)
  }

  test("I can filter and select min") {
    val list1 = ListMap(
      "A" -> 10,
      "B" -> 1,
      "C" -> 5,
      "D" -> 8
    )

    val keysToFilter = ArrayBuffer("A", "C")

    val result = list1.filter{ case (k,v) => keysToFilter.contains(k) }

    assert(result == Map("C" -> 5, "A" -> 10))
  }

  def prim[A](graph: ListMap[String, ListMap[String,Int]]): ArrayBuffer[String] = {

    // start
    var primtree: ArrayBuffer[String] = ArrayBuffer()
    val (startKey, conns) = graph.head //(A,Map(B -> 1, C -> 5))
    primtree += startKey


    var (nextKey, nextDis) = conns.min
    primtree += nextKey

    primtree
  }



  test("prim") {
    val graph = ListMap(
      "A" -> ListMap(
        "B" -> 1,
        "C" -> 5
      ),
      "B" -> ListMap(
        "A" -> 1,
        "C" -> 4,
        "D" -> 8,
        "E" -> 7
      ),
      "C" -> ListMap(
        "A" -> 5,
        "B" -> 4,
        "D" -> 6,
        "F" -> 2
      ),
      "D" -> ListMap(
        "B" -> 8,
        "C" -> 6,
        "E" -> 11,
        "F" -> 9
      ),
      "E" -> ListMap(
        "B" -> 7,
        "D" -> 11,
        "F" -> 3,
        "G" -> 10
      ),
      "F" -> ListMap(
        "C" -> 2,
        "D" -> 9,
        "E" -> 3,
        "G" -> 12
      ),
      "G" -> ListMap(
        "E" -> 10,
        "F" -> 12
      )
    )

    prim(graph)
  }
}
