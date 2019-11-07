package interviews_problems

import org.scalatest.FunSuite

class BinarySpec extends FunSuite {
    def invertChar(c: Char): Char = {
      c match{
          case '0' => '1'
          case '1' => '0'
      }
    }

    def initSize(chars: String): String = {
      if(chars.reverse.indexOfSlice("0") == -1) {
        "0" ++ chars
      } else {
        chars
      }
    }

    def getIdxFirstZeroFromStart(chars: String): Int = {
      val a = initSize(chars)
      (a.length - a.reverse.indexOfSlice("0")) - 1
    }

    def solution(number: String): String = {
      val validA = initSize(number)
      val idx = getIdxFirstZeroFromStart(validA)

      validA.zipWithIndex.map{ case (v, ix) =>
        if(ix >= idx) {
          invertChar(v)
        } else {
          v
        }
      }.mkString("")
    }

    test("can expand number with zeros"){
      val a = "01011"
      val b = "1111"

      assert(a == initSize(a))
      assert("01111" == initSize(b))
    }

    test("I got idx from start") {
      val a = "101101"
      val b = "011111"
      val c = "11111"
      assert(4 == getIdxFirstZeroFromStart(a))
      assert(0 == getIdxFirstZeroFromStart(b))
      assert(0 == getIdxFirstZeroFromStart(c))
    }

    test("solution"){
      assert("1010" == solution("1001"))
      assert("10000" == solution("1111"))
      assert("0001" == solution("0000"))
      assert("1" == solution("0"))
    }
}
