package DataStructure.queue

import scala.collection.mutable.ArrayBuffer

class MaxPriorityQueue() {
  private var q = ArrayBuffer[Node]()

  def getQ() = q

  def nqueue(newitem: Node) = {
    var inserted = false
    for((node, ix) <- q.zipWithIndex if inserted == false) {
      if(newitem.getPriority > node.getPriority){
        q.insert(ix, newitem)
        inserted = true
      }
    }

    if (inserted == false) {
      q += newitem
    }
  }

  def dqueue(): Node = {
    q.remove(0)
  }

  def isEmpty(): Boolean = {
    q.size == 0
  }
}