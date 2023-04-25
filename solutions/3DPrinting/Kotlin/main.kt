// kotlinc main.kt -include-runtime -d main.jar && java -jar main.jar 3 300000 200000 300000 500000 300000 200000 500000 300000 300000 500000 300000 200000 1000000 1000000 0 0 0 1000000 1000000 1000000 999999 999999 999999 999999 768763 148041 178147 984173 699508 515362 534729 714381 949704 625054 946212 951187

fun main(args: Array<String>) {
    val T: Int = args[0].toInt()
    var index = 1
    for (test in 0..T - 1) {
        var printers: MutableList<MutableList<Int>> = mutableListOf()
        for (printer in 0..2) {
            var colors: MutableList<Int> = mutableListOf()
            for (inkValue in 0..3) {
                colors.add(args[index].toInt())
                index++
            }
            printers.add(colors)
        }
        print("Case #" + (test + 1) + ":")
        solution(printers)
    }
}

fun solution(printers: MutableList<MutableList<Int>>) {
    var necessaryInk = 1000000
    var totalInk = 0
    var mins: MutableList<Int> = mutableListOf()
    for (color in 0..3) {
        val arr = arrayOf(
            printers[0][color],
            printers[1][color],
            printers[2][color],
        )
        val ints = arr.toList()
        totalInk += ints.min()
        mins.add(ints.min())
    }

    if (totalInk < necessaryInk) {
        println("IMPOSSIBLE")
    } else {
        var remaining = totalInk - necessaryInk
        for (index in 3 downTo 0) {
            if (remaining > 0) {
                if (mins[index] <= remaining) {
                    remaining -= mins[index]
                    mins[index] = 0
                } else {
                    mins[index] = mins[index] - remaining
                    remaining = 0
                }
            }
        }
        println(mins)
    }
}