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
    private def increaseDigit(num: String, rest: Int = 0, resultAcc: String = ""): String = {
      num.toList.reverse match {
        case Nil => resultAcc
        case n => {
          if (rest > 0 || resultAcc.isEmpty) {
            val (representation, newrest) = sum1(n.head)
            increaseDigit(n.tail.reverse.mkString(""), newrest, representation++resultAcc)
          } else {
            increaseDigit(n.tail.reverse.mkString(""), 0, n.head.toString++resultAcc)
          }
        }
      }
    }

    def sum1(digit: Char): (String,Int) = {
      if(digit == '9'){
        ("0", 1)
      } else {
        ((1 + digit.asDigit).toString, 0)
      }
    }

    def solution(num: String): String = {
      val resized = initSize(num)
      increaseDigit(resized)
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

    test("solution") {
      assert("124" == solution("123"))
      assert("121" == solution("120"))
      assert("130" == solution("129"))
      assert("1" == solution("0"))
      assert("1000" == solution("999"))
    }

    test("add1") {
      assert(("0", 1) ==  sum1('9'), "9+1 equals 0 and rest=1")
      assert(("3", 0) ==  sum1('2'), "2+1 equals 3 and rest=0")
    }

}
