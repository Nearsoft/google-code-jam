


var odds = arrayOf<Int>()
var evens = arrayOf<Int>()

fun separateArrays(arr:IntArray) {
 // Sort odd and even
 separateArray(arr)
    odds.sort()
    evens.sort()
}

fun separateArray(arr:IntArray) {
    for(i in 0 until arr.size){
        if(i%2 == 0){
            evens+=(arr[i])
        }
        else {
            odds+=(arr[i])
        }
    }
}

fun checkTroubleSort(): Int {
    if (odds.size == evens.size){
        for (i in 0 until odds.size) {
            if (evens[i] > odds[i]) {
                // return esta mal regresa index
                return i
            }
        }
        return -1
    }
    else if(odds.size > evens.size){
        for (i in 0 until evens.size) {
            if (evens[i] > odds[i]) {
                // return esta mal regresa index
                return i
            }
        }
        return -1
    }
    else{
        for (i in 0 until odds.size) {
            if (evens[i] > odds[i]) {
                // return esta mal regresa index
                return i
            }
        }
        return -1
    }
//    for (i in 0 until len - 1) {
//        if (evens[i] > odds[i]) {
//            // return esta mal regresa index
//            return i
//        }
//    }
//    return -1
}

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
fun main(args: Array<String>) {

    var t = readLine()!!.toInt()
    var testCase = 1
    var resultados = listOf<String>()
    while (t > 0) {
        // Pedir tamaño de arreglo
        var n = readLine()!!.toInt()
        var flag = false
        // Llenar el arreglo donde el input es un string y cada numero esta separado por espacios
        var values = readLine()!!.toString()
        var mappedValues = values.split(' ').map { it.toInt() }
        var primeArray = IntArray(n)
        for ((cont, i) in mappedValues.withIndex()){
            primeArray[cont] = i
        }

        // Separate odd even
        separateArrays(primeArray)
        // Compare odd[i] with even[i]
        var result = checkTroubleSort()
        // if error its found return i
        // else iterate through original list and compare:
        if (result == -1) {
            // arr[i] > arr[i+1]
            // if true return i
            // else all good
            troubleSort2(primeArray)
            for (x in 0 until primeArray.size - 1){
                if (primeArray[x] > primeArray[x+1]){
                    flag = true
                    resultados += "CASE #$testCase: $x"
                    break
                }
            }
        }
        if (!flag){
            resultados += "CASE #$testCase: OK"
        }



        //End Rest----------

        t -= 1
        testCase += 1
    }
    for (i in resultados){
        println(i)
    }

}

