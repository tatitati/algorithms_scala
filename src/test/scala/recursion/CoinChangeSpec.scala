//package recursion
//
//import org.scalatest.FunSuite
//
///**
//  *  Amount = 4 and coins = {1,2,3}, there are four solutions: {1,1,1,1}, {1,1,2}, {2,2}, {1,3}.
//  *  Amount = 5 and coins = {1,2,3}, there are four solutions: {1,1,1,1,1} {1,1,1,2}, {1,2,2}, {1,1,3} {2,3}
//  */
//
//class CoinChangeSpec extends FunSuite
//{
//
//  def  change(amount: Int, coins: List[Int]): List[List[Int]] = {
//      amount match {
//        case 1 => List[List[1]]
//      }
//  }
//
//
//  test("Coin change") {
//    assert(
//      change(amount = 5, coins = List(1, 2, 3)) ===
//      List(
//        List(1,1,1,1,1),
//        List(1,1,1,2),
//        List(1,2,2),
//        List(1,1,3),
//        List(2,3),
//      )
//    )
//  }
//}
