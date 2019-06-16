package Tools

object Memoizator {
  var cache: Map[Int, Int] = Map()

  def exist(key: Int): Boolean = {
    cache.contains(key)
  }

  def get(key: Int): Int = {
    cache.get(key).get
  }

  def add(newvalue :Map[Int, Int]): Unit = {
    cache = cache ++ newvalue
  }

  def clear(): Unit = {
    cache = Map()
  }
}
