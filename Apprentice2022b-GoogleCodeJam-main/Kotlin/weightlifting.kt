import kotlin.math.min

fun main() {
    val tc = readLine()!!.toInt()
    for (c in 1..tc) { // iterate test cases
        val (e, w) = readLine()!!.split(" ").map { it.toInt() } // read e and w
        val needed = List(e) { readLine()!!.split(" ").map(String::toInt) } // List of list
        val commonArr = Array(e) { Array(e) { listOf<Int>() } } // Array of arrays
        val difference = Array(e) { IntArray(e) } // Another array of arrays
        for (l in difference.indices.reversed()) { // this is going to give us our working array
            for (r in l..difference.lastIndex) {
                if (l == r) { // if l, r are equal then
                    commonArr[l][r] = needed[l] // commonArr in l,r == needed[l]
                } else {
                    commonArr[l][r] = commonArr[l][r - 1].zip(needed[r], ::min) // else combine and fill commonArr
                }
                val common = commonArr[l][r].sum() // this is going to sum all values in our array and the final element will have our common weights
                if (l == r) { // this is going to check for equality
                    difference[l][r] = common // dp in that place is = common
                } else {
                    difference[l][r] = (l until r).map { mid -> difference[l][mid] + difference[mid + 1][r] }.minOrNull()!! // if they are not the same this is the brute force approach, pretty much you count the differences
                    difference[l][r] -= common // Here you subtract the common elements
                }
            }
        }
        println("Case #$c: ${2 * difference.first().last()}") // the sum of all differences is the amount of times we add, and we multiply to remove those weights so we end up with the answer
    }
}
