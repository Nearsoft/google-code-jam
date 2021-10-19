fun main() {
    //println(“Hello world!“)
    var cases = readLine()!!.toInt()
    for (i in 1..cases) {
        var len = readLine()!!.toInt()
        var str_input = readLine()!!
        // str to number array
        var array = str_input.split(" ").map { it.toInt() }.toTypedArray()
        //reverse(array, 1, 3)
        //print_array(array)
        //println(min_index(array, 2))
        var num = i
        var cost = reversort(array)
        println("Case #$num: $cost")
        //array.forEach { println(it) }
    }
}
fun swap(arr: Array<Int>, i: Int, j: Int) {
    val temp = arr[i]
    arr[i] = arr[j]
    arr[j] = temp
}
fun reverse(arr: Array<Int>, l: Int, h: Int) {
    var low = l
    var high = h
    while (low < high)
    {
        swap(arr, low, high)
        low++
        high--
    }
}
fun min_index(array: Array<Int>, i: Int): Int {
    var min = Int.MAX_VALUE
    //var I2: Int = Int.MAX_VALUE
    var min_index = -1
    var j = i
    while (j < array.size) {
        if (array[j] < min) {
            min = array[j]
            min_index = j
        }
        j++
    }
    return min_index
}
fun reversort(array: Array<Int>): Int {
    var cost: Int = 0
    for (i in (0..array.size - 2)) {
        var j = min_index(array, i)
        reverse(array, i, j)
        cost += j - i + 1
        //println(“Costo de iteration “.plus(j - i + 1))
    }
    return cost
}