import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Scanner

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

//fun checkTroubleSort(): Int {
//    if (odds.size == evens.size){
//        for (i in 0 until odds.size) {
//            if (evens[i] > odds[i]) {
//                // return esta mal regresa index
//                return i
//            }
//        }
//        return -1
//    }
//    else if(odds.size > evens.size){
//        for (i in 0 until evens.size) {
//            if (evens[i] > odds[i]) {
//                // return esta mal regresa index
//                return i
//            }
//        }
//        return -1
//    }
//    else{
//        for (i in 0 until odds.size) {
//            if (evens[i] > odds[i]) {
//                // return esta mal regresa index
//                return i
//            }
//        }
//        return -1
//    }
//
//    // New
//
//
//}

fun troubleSort2(arr:IntArray){
    var cambio = false
    for ((cont, i) in arr.withIndex()){
        if(cont+2 < arr.size){
            if (arr[cont] > arr[cont+2]){
                var aux = arr[cont]
                arr[cont] = arr[cont+2]
                arr[cont+2] = aux
                cambio = true
            }
        }
    }
    if (cambio){
        troubleSort2(arr)
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
    val reader = (BufferedReader(InputStreamReader(System.`in`)))
//    var t = reader.readLine()!!.toInt()
//    val sc:Scanner = Scanner(System.`in`)

    var t = reader.readLine().toInt()
//    var t = sc.nextInt()
    var testCase = 1
    var resultados = listOf<String>()
    while (t > 0) {
        // Pedir tamaño de arreglo
        var n = reader.readLine()!!.toInt()
//        var n = sc.nextInt()
        //reset odds and evens
        odds = arrayOf<Int>()
        evens = arrayOf<Int>()
        // Llenar el arreglo donde el input es un string y cada numero esta separado por espacios
//        val `in` = Scanner(BufferedReader(InputStreamReader(System.`in`)))
        var values = reader.readLine()!!.toString()
//        var values = sc.nextLine()
//        val values = `in`.nextLine().toString()

//        val values = reader.readLine()
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