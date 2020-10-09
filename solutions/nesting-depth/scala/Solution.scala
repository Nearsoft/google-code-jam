import scala.io.StdIn.readLine
//Solution because that i how google jam ask for it
object Solution {
  //define main with no defined return
  def main(args: Array[String]): Unit = {
    val number = readLine()
    for (t <- 1 to number.toInt) {
      val test = readLine()
      println("Case #" + t + ": " + deep(test))
    }
  }
  def deep(N: String): String = {
    var const: Int = 0
    var newstr: String = ""
    var i: Int = 0
    for (num <- N) {
      i = num.toInt - 48
      if (i > const) {
        newstr = newstr + ("(" * (i - const))
      }else if (i < const) {
        newstr = newstr + (")" * (const - i))
      }
      const = i
      newstr = newstr + num
    }
    newstr = newstr + (")" * i)
    newstr
  }
}