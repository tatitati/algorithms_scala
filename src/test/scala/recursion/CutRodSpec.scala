//package recursion
//
//import org.scalatest.FunSuite
//
//class CutRodSpec extends FunSuite {
//
//    test("cut rod") {
//
//      val revenueByLength: Map[Int, Int] = Map(
//        // length -> revenue
//        1->1,
//        2->5,
//        3->8,
//        4->9,
//        5->10,
//        6->17,
//        7->17,
//        8->20,
//        9->24,
//        10->30
//      )
//
//      def calculateRevenue(length: Int, levelRecursion: Int): Int = {
//        var revenue = 0
//        length match {
//          case 0 => 0
//          case 1 => revenueByLength(1)
//          case _ => {
//            for(i <- 1 to length) {
//              revenue = List(
//                revenue,
//                revenueByLength(i) + calculateRevenue(length - i, i)
//              ).max
//            }
//
//            revenue
//          }
//        }
//      }
//
//      assert(
//        calculateRevenue(8, 0) === 22,
//        "I can get the max revenue for a length after cutting, however I don't know how long are these pieces"
//      )
//    }
//
//
//}
