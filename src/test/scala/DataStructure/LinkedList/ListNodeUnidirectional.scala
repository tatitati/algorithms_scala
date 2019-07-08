package DataStructure.LinkedList

case class ListNodeUnidirectional(
                     val value: String,
                     var next: Option[ListNodeUnidirectional] = None
    ) {
  def addNext(newNode: ListNodeUnidirectional): ListNodeUnidirectional = {
    next match {
      case Some(node) => node.addNext(newNode)
      case None => next = Some(newNode)
    }

    this
  }

  def count(): Int = {
    var i = 1

    var currentNode = this
    while(currentNode.next != None) {
      currentNode = currentNode.next.get
      i+=1
    }

    i
  }
}
