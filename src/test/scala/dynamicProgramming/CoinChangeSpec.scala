package dynamicProgramming

import org.scalatest.FunSuite

/**
  *  Amount = 4  and coins = {1,2,3}, there are four solutions: {1,1,1,1}, {1,1,2}, {2,2}, {1,3}.
  *  Amount = 5  and coins = {1,2,3}, there are four solutions: {1,1,1,1,1} {1,1,1,2}, {1,2,2}, {1,1,3} {2,3}
  *  Amount = 11 and coins = {2,4,5}, there are two  solutions: {2,2,2,5}, {2,4,5}
  */
class CoinChangeSpec extends FunSuite {

  test("coin change") {
    val coins = List(2,4,5)

    def coinchange(total: Int): Int = {

      total match {
        case 0 => 0
        case _ => {
          for(coin <- coins) {


          }
        }
      }
    }

    assert(coinchange(11) === 2)
  }
}
