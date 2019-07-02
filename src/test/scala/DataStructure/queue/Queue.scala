package DataStructure.queue

import scala.collection.mutable.ArrayBuffer

class Queue[A]() {
  var q: ArrayBuffer[A] = ArrayBuffer()

  def nque(item: A) = {
    q += item
  }

  def dque(): A = {
    q.remove(0)
  }

  def show(): ArrayBuffer[A] = {
    q
  }

  def isEmpty(): Boolean = {
    q.isEmpty
  }
}