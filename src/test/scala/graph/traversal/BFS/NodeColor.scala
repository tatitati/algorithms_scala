package graph.traversal.BFS

case class NodeColor(val value: String, var color: String = "undescovered", var next: Option[NodeColor] = None) {

    def conn(newNode: NodeColor): NodeColor = {
      next match {
        case Some(nextNode) => nextNode.conn(newNode)
        case None => next = Some(newNode)
      }

      this
    }
}