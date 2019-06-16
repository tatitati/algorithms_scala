package recursion

import org.scalatest.FunSuite

class FibonacciSpec extends FunSuite {

  private def fib(n: Int): Int = {
    n match {
      case 0 => 0
      case 1 => 1
      case _ => fib(n-1) + fib(n-2)
    }
  }

  test("fibonnaci") {
    assert(fib(1) === 1)
    assert(fib(2) === 1)
    assert(fib(4) === 3)
  }
}
