package interviews_problems

import org.scalatest.FunSuite

import scala.annotation.tailrec

class DecimalSpec extends FunSuite {

    def isAll9(num: String): Boolean = {
      num.filter(_ == '9').length == num.length
    }

    def initSize(num: String): String = {
      if(isAll9(num)) {
        "0" ++ num
      } else {
        num
      }
    }

    @tailrec
    def increaseDigit(num: String, sumAcc: String = "0"): String = {
      num.reverse.head match {
        case '9' =>
        case _ && lastSum != 10 => _
        case _ && lastSum == 10 => _ + 1
      }
    }


    test("can figure out how many 9 are in the number") {
      val a = "999"
      val b = "998"
      assert(true == isAll9(a))
      assert(false == isAll9(b))
    }

    test("Can determine the right size of the results") {
      val a = "999"
      val b = "998"
      assert("0999" == initSize(a))
      assert("998" == initSize(b))
    }

    test("solution"){
      val a = "111"
      val b = "129"
      assert("222" == increaseDigit(a))
      assert("130" == increaseDigit(b))
    }


}
