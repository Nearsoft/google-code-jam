import scala.collection.mutable.ListBuffer
import scala.io.StdIn.readLine
import scala.math.Numeric.Implicits.infixNumericOps

object Solution {

  def main(args: Array[String]): Unit = {

    val T = readLine().toInt
    val P = readLine().toInt

    val totalPlayers = 100
    val totalQuestions = 10000

    for (ti <- 0 until T) {

      val results = Array.ofDim[Int](totalPlayers, totalQuestions)

      var questions = Array.ofDim[Question](totalQuestions)

      for (playerIndex: Int <- 0 until totalPlayers) {
        val answers = readLine()
        for (questionIndex: Int <- 0 until totalQuestions) {
          if (questions(questionIndex) == null) {
            questions(questionIndex) = new Question(questionIndex)
          }
          if (answers(questionIndex).equals('1')) {
            questions(questionIndex).count += 1
          }
          results(playerIndex)(questionIndex) = answers(questionIndex).toString.toInt
        }
      }

      questions = questions.sorted

      val ranks = Array.ofDim[Int](totalQuestions)
      for (rank <- 0 until totalQuestions) {
        val question = questions(rank)
        ranks(question.ID) = rank
      }

      val playersMeanRanks = Array.ofDim[Double](totalPlayers)

      for (playerIndex <- 0 until totalPlayers) {
        val values = new ListBuffer[Int]()
        for (questionIndex <- 0 until totalQuestions) {
          if (results(playerIndex)(questionIndex) == 1) {
            values += ranks(questionIndex)
          }
        }
        playersMeanRanks(playerIndex) = stdDev(values)
      }

      println(s"Case #${ti + 1}: ${playersMeanRanks.indexOf(playersMeanRanks.max) + 1}")

    }

  }

  def mean[T: Numeric](xs: Iterable[T]): Double = xs.sum.toDouble / xs.size
  def variance[T: Numeric](xs: Iterable[T]): Double = {
    val avg = mean(xs)
    xs.map(_.toDouble).map(a => math.pow(a - avg, 2)).sum / xs.size
  }
  def stdDev[T: Numeric](xs: Iterable[T]): Double = math.sqrt(variance(xs))

}

class Question(var ID: Int, var count: Int = 0) extends Ordered[Question] {
  override def compare(that: Question): Int = this.count.compare(that.count)
}