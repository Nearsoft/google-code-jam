import kotlin.random.Random
import kotlin.math.*
import kotlin.text.*
import kotlin.collections.*

var _Dim1 = 0
var _Dim2 = 0

fun main(args: Array<String>) {
    var t = Integer.valueOf(readLine())
    for (z in 1..t) {
        var a = Integer.valueOf(readLine())
        findSquareDimensions(a)

        var targets = mutableMapOf<String,Int>()
        for(i in 2.._Dim1 - 1) {
            for(j in 2.._Dim2 - 1) {
                var coordinate = java.lang.String.format("%d %d",i,j)
                targets.put(coordinate,9)
            }
        }

        var x = -1
        var y = -1
        var nextTarget = ""

        var alreadyPrepared = mutableListOf<String>()
        
        while (x != 0 && y != 0) {
            var highestMissingSquares = 0
            for ((target, missingSquares) in targets) {
                if ( missingSquares > highestMissingSquares) {
                    nextTarget = target
                    highestMissingSquares = missingSquares
                }
            }

            println(nextTarget)

            var newCoord = readLine()
            x = (newCoord!!.split(" ")[0]).toInt()
            y = (newCoord!!.split(" ")[1]).toInt()

            if (newCoord !in alreadyPrepared) {
                alreadyPrepared.add(newCoord)
                for (coordinate in targets.keys) {
                    var target1 = (coordinate!!.split(" ")[0]).toInt()
                    var target2 = (coordinate!!.split(" ")[1]).toInt()
                    if ((x >= target1 - 1 && target1 + 1 >= x) && (target2 + 1 >= y && y >= target2 - 1)) {
                        targets.put(coordinate, targets.getValue(coordinate) - 1)
                    }
                }
            }
        }
    }

    println("Exits while!")
    return 
}

fun findSquareDimensions(a: Int) {
    var smallestDifference = a
    var div = a/2
    var n = 0
    var m = 0
    for (i in 1..div) {
        var div2 = a/i
        if ((a % i == 0) && smallestDifference > abs(i - div2)) {
            smallestDifference = abs(i - div2)
            n = i
            m = div2
        }
    }
    if(3 > n || 3 > m) return findSquareDimensions(a+1)
    _Dim1 = n
    _Dim2 = m
}  