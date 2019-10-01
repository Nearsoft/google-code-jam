var odds = arrayOf<Int>()
var evens = arrayOf<Int>()
fun separateArrays(arr:IntArray): IntArray {
    // Sort odd and even
    separateArray(arr)
    odds.sort()
    evens.sort()
    var newArr = IntArray(arr.size)
    var ie = 0
    var io = 0
    for (i in 0 until arr.size){
        if((i%2 == 0 || i == 0) && ie < evens.size){
            newArr[i] = evens[ie]
            ie++
        }
        else if(io < odds.size){
            newArr[i] = odds[io]
            io++
        }
    }
    return newArr
}
fun separateArray(arr:IntArray) {
    for(i in 0 until arr.size){
        if(i%2 == 0 || i == 0){
            evens+=(arr[i])
        }
        else {
            odds+=(arr[i])
        }
    }
}
fun checkForErrors(arr:IntArray): Int{
    for (i in 0 until arr.size - 1){
        if (arr[i] > arr[i+1]){
            return i
        }
    }
    return -1
}
fun main(args: Array<String>) {
    val reader = System.`in`.bufferedReader()
    var t = reader.readLine()!!.toInt()
    var testCase = 1
    var resultados = listOf<String>()
    while (t > 0) {
        // Pedir tamaño de arreglo
        var n = reader.readLine()!!.toInt()
        //reset odds and evens
        odds = arrayOf<Int>()
        evens = arrayOf<Int>()
        // Llenar el arreglo donde el input es un string y cada numero esta separado por espacios
        var values = reader.readLine()!!.toString()
        var mappedValues = values.split(' ').map { it.toInt() }
        var primeArray = IntArray(n)
        for ((cont, i) in mappedValues.withIndex()){
            primeArray[cont] = i
        }
        // Separate odd even
        var resultArr = separateArrays(primeArray)
        // Compare if its sorted correctly
        var result = checkForErrors(resultArr)
        // if error its found return i
        // else iterate through original list and compare:
        if (result == -1) {
            resultados += "CASE #$testCase: OK"
        }
        else{
            resultados += "CASE #$testCase: $result"
        }
        t -= 1
        testCase += 1
    }
    for (i in resultados){
        println(i)
    }
}var odds = arrayOf<Int>()
var evens = arrayOf<Int>()
fun separateArrays(arr:IntArray): IntArray {
    // Sort odd and even
    separateArray(arr)
    odds.sort()
    evens.sort()
    var newArr = IntArray(arr.size)
    var ie = 0
    var io = 0
    for (i in 0 until arr.size){
        if((i%2 == 0 || i == 0) && ie < evens.size){
            newArr[i] = evens[ie]
            ie++
        }
        else if(io < odds.size){
            newArr[i] = odds[io]
            io++
        }
    }
    return newArr
}
fun separateArray(arr:IntArray) {
    for(i in 0 until arr.size){
        if(i%2 == 0 || i == 0){
            evens+=(arr[i])
        }
        else {
            odds+=(arr[i])
        }
    }
}
fun checkForErrors(arr:IntArray): Int{
    for (i in 0 until arr.size - 1){
        if (arr[i] > arr[i+1]){
            return i
        }
    }
    return -1
}
fun main(args: Array<String>) {
    val reader = System.`in`.bufferedReader()
    var t = reader.readLine()!!.toInt()
    var testCase = 1
    var resultados = listOf<String>()
    while (t > 0) {
        // Ask Array size
        var n = reader.readLine()!!.toInt()
        //reset odds and evens
        odds = arrayOf<Int>()
        evens = arrayOf<Int>()

        var values = reader.readLine()!!.toString()
        var mappedValues = values.split(' ').map { it.toInt() }
        var primeArray = IntArray(n)
        for ((cont, i) in mappedValues.withIndex()){
            primeArray[cont] = i
        }
        // Separate odd even
        var resultArr = separateArrays(primeArray)
        // Compare if its sorted correctly
        var result = checkForErrors(resultArr)
        // if error its found return i
        // else iterate through original list and compare:
        if (result == -1) {
            resultados += "CASE #$testCase: OK"
        }
        else{
            resultados += "CASE #$testCase: $result"
        }
        t -= 1
        testCase += 1
    }
    for (i in resultados){
        println(i)
    }
}