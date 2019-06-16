package dynamicProgramming

import Tools.Memoizator
import org.scalatest.{BeforeAndAfterEach, FunSuite}

class FibonnaciSpec extends FunSuite with BeforeAndAfterEach {

  test("fibonnaci TOP-DOWN with moemoization (recursive)") {

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

  test("fibonnaci BOTOM-TOP (no recursive)") {

    def fib(n: Int): Int = {
      Memoizator.add(Map(0 -> 0))
      Memoizator.add(Map(1 -> 1))

      for(i <- 2 to n) {
        Memoizator.add(
          Map(
            i -> (Memoizator.get(i-1) + Memoizator.get(i-2))
          )
        )
      }
      
      Memoizator.get(n)
    }

    assert(fib(1) === 1)
    assert(fib(2) === 1)
    assert(fib(4) === 3)
    assert(fib(5) === 5)
  }


  override def beforeEach(): Unit = {
    Memoizator.clear()
  }
}
