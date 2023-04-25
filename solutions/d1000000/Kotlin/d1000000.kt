import java.io.BufferedReader
import java.io.InputStreamReader

fun maxStraightLength(list: List<Int>): Int {
    var maxLength = 0
    val sortedList = list.sorted()

    for (j in sortedList.indices) {
        if (sortedList[j] > maxLength) {
            maxLength++
        }
    }
    return maxLength
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val testCases = reader.readLine().toInt()

    for (i in 1..testCases) {
        reader.readLine() // Skip the number of dice input as it's never used
        val diceIn = reader.readLine()

        val dice = diceIn.split(" ").map { it.toInt() }
        val res = maxStraightLength(dice)
        println("Case #$i: $res")
    }
}
