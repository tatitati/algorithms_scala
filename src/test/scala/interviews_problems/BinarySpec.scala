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

    def getFirstZeroFromStart(chars: String): Int = {
      val a = initSize(chars)
      (a.length - a.reverse.indexOfSlice("0")) - 1
    }

    def increase(number: String): String = {
      val validA = initSize(number)
      val idx = getFirstZeroFromStart(validA)

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
      assert(4 == getFirstZeroFromStart(a))
      assert(0 == getFirstZeroFromStart(b))
      assert(0 == getFirstZeroFromStart(c))
    }

    test("can incrase by 1"){
      assert("1010" == increase("1001"))
      assert("10000" == increase("1111"))
      assert("0001" == increase("0000"))
      assert("1" == increase("0"))
    }
}
