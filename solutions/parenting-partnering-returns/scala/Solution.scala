import scala.io.StdIn._
import scala.util.Sorting
import scala.util.control._
import scala.collection.mutable.ArrayBuffer

object Solution {
    def main(args: Array[String]) {
        val inloop = new Breaks;
        var n_activities = scala.io.StdIn.readInt()

        for( i <- 1 to n_activities) {
            var n = scala.io.StdIn.readInt()
            var activities = new Array[Activity](n)
            
            for( x <- 0 until n) {
                var reader = scala.io.StdIn.readLine()
                var temp = reader.split(" ")
                var activityTepm: Activity = new Activity(x.toInt, temp(0).toInt, temp(1).toInt)
                activities(x) = activityTepm
            }
            
            Sorting.quickSort(activities)(TimeOrdering)

            var parents = Array("C", "J")
            var currentParent: Int = 0
            var finishTime = new Array[Int](n)
            var ans = new Array[String](n)

            ans(activities(0).index) = "C";
            finishTime(0)=activities(0).endTime

            var isPossible: Boolean = true

            inloop.breakable {
                for( j <- 1 until n) {
                    var finish: Int = activities(j-1).endTime
                    var start: Int = activities(j).startTime

                    if(start >= finish) {
                        ans(activities(j).index) = parents(currentParent)
                        finishTime(currentParent) = activities(j).endTime
                    } else {
                        if(currentParent == 0) {
                            currentParent = 1
                        } else {
                            currentParent = 0
                        }
                        if(start >= finishTime(currentParent)) {
                            ans(activities(j).index) = parents(currentParent)
                            finishTime(currentParent) = activities(j).endTime
                        } else {
                            isPossible = false
                            inloop.break
                        }
                    }
                }
            }

            var answerString: String = ""
            if(isPossible) {
                for(whoIs <- ans) {
                    answerString += whoIs
                }
            } else {
                answerString = "IMPOSSIBLE"
            }
            println(s"Case #$i: $answerString")
        }
    }

    class Activity(val index: Int, val startTime: Int, val endTime: Int) {
        var ind: Int = index
        var fTime: Int = startTime
        var eTime: Int = endTime
    }

    object TimeOrdering extends Ordering[Activity] {
        def compare(a: Activity, b: Activity) = a.endTime compare b.endTime
    }

}