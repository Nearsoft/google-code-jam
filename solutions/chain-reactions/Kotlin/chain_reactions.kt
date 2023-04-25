import java.util.Scanner
import kotlin.math.*

fun main() {
    val sc = Scanner(System.`in`)
    var tests = sc.nextInt()
    var i = 1
    while (tests-- > 0) {
        var n = sc.nextInt()
        var funValue = IntArray(n + 1)
        var v = Array(n + 1) { mutableListOf<Int>() }
        var answer: Long = 0
        
        for (i in 1..n) {
            funValue[i] = sc.nextInt()
        }
        
        for (i in 1..n) {
            var temp = sc.nextInt()
            v[temp].add(i)
        }
        fun dfs(node: Int): Int {
            if (v[node].isEmpty()) {
                answer += funValue[node]
                return funValue[node]
            } else {
                var minP = Int.MAX_VALUE
                for (x in v[node]) {
                    minP = min(minP, dfs(x))
                }
                answer += max(0, funValue[node] - minP)
                return max(minP, funValue[node])
            }
        }
        for (x in v[0]) {
            dfs(x)
        }
        println("Case #$i: $answer")
        i++
    }
}