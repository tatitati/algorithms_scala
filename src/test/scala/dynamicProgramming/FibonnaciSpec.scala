package dynamicProgramming

import org.scalatest.{BeforeAndAfterEach, FunSuite}

object Memoizator {
  var cache: Map[Int, Int] = Map()

  def exist(key: Int): Boolean = {
    cache.contains(key)
  }

  def get(key: Int): Int = {
    cache.get(key).get
  }

  def add(newvalue :Map[Int, Int]): Unit = {
    cache = cache ++ newvalue
  }

  def clear(): Unit = {
    cache = Map()
  }
}

class FibonnaciSpec extends FunSuite with BeforeAndAfterEach {

  test("fibonnaci TOP-DOWN with moemoization") {

    def fib(n: Int): Int = {
      Memoizator.exist(n) match {
        case true => Memoizator.get(n)
        case false => n match {
          case 0 => 0
          case 1 => 1
          case _ => {
            val result = fib(n-1) + fib(n-2)
            Memoizator.add(Map(n -> result))
            result
          }
        }
      }
    }

    assert(fib(1) === 1)
    assert(fib(2) === 1)
    assert(fib(4) === 3)
  }


  override def beforeEach(): Unit = {
    Memoizator.clear()
  }

}
