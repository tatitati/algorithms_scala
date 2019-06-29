package Stack

import scala.collection.mutable.ArrayBuffer

class Stack[A]() {
  private var q: ArrayBuffer[A] = ArrayBuffer()

  def push(item: A) = {
    q += item
  }

  def push(items: List[A]) = {
    q ++= items
  }

  def pop(): A = {
    q.remove(q.size-1)
  }

  def show(): ArrayBuffer[A] = {
    Thread.sleep(2000)
    q
  }

  def isEmpty(): Boolean = {
    q.isEmpty
  }

  def contains(item: A): Boolean = {
    q.contains(item)
  }
}