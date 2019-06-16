package recursion

import org.scalatest.FunSuite

class CutRodSpec extends FunSuite {

    test("cut rod") {

      def cutRod(prices: Map[Int, Int], length: Int): Int = {
        var revenue = 0
        length match {
          case 0 => 0
          case _ => {
            for(i <- 1 to length) {
              revenue = List(revenue, prices(i) + cutRod(prices, length - i)).max
              println("length: " + length )
              println("revenue: " + revenue )
              println("======")
            }

            revenue
          }
        }
      }



      val lengthMapRevenue = Map(
        // length -> revenue
        1->1,
        2->5,
        3->8,
        4->9,
        5->10,
        6->17,
        7->17,
        8->20,
        9->24,
        10->30
      )


      assert(cutRod(lengthMapRevenue, 7) === 18)
    }


}
