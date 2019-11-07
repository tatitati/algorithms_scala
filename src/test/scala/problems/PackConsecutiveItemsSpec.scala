package problems

import org.scalatest.FunSuite

class PackConsecutiveItemsSpec extends FunSuite {

  def recursive[A](l: List[A]): List[List[A]] = {
    if(l.isEmpty) List()
    else {
      val (packed, unpacked) = l span (_ == l.head)
      packed :: recursive(unpacked)
    }
  }

  test("test") {
    val mylist = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
    assert(List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e)) == recursive(mylist))
  }

}
