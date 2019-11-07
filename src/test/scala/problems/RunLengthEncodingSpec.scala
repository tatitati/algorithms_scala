package problems

import org.scalatest.FunSuite

class RunLengthEncodingSpec extends FunSuite {

  def recursive[A](l: List[A]): List[(Int,A)] = {
    if(l.isEmpty) List()
    else {
      val (packed, unpacked) = l span (_ == l.head)
      (packed.length, l.head) :: recursive(unpacked)
    }
  }

  test("test"){
    val mylist = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
    assert(List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e)) == recursive(mylist))
  }
}
