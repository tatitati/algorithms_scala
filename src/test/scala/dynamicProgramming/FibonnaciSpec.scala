package dynamicProgramming

import Tools.Memoizator
import org.scalatest.{BeforeAndAfterEach, FunSuite}

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

  test("fibonnaci BOTOM-TOP") {

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
