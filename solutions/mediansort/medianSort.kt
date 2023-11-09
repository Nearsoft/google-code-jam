fun main(){
    
    var (a, b, c) = readLine()!!.split(' ')
    var testCases = a.toInt()
    var numOfElemnts = b.toInt()
    var numOfQueries = c.toInt()
    while(testCases > 0){
        if(!medianSort(numOfElemnts, numOfQueries))
        return
    }
    testCases = testCases - 1
}
    
    // Median Sort
    fun medianSort( nE: Int, nQ: Int): Boolean{
        var nQL = nQ
        var numbers = intArrayOf(1,2)
        for (i in 3..nE+1){
            var left = 0
            var right = numbers.size-1
            while (right - left >= 1 && nQL > 0){
                var leftP = left + ((right-left)/3)
                var rightP = right - ((right-left)/3)
                println(" ${numbers[leftP]} ${numbers[rightP]} ${i}")
                System.out.flush()
                var med = readLine()!!.toInt()
                nQL = nQL-1
                if(med == numbers[leftP]){
                    right = leftP - 1
                    if(left == right){
                        right += 1
                    }
                }else if(med == numbers[rightP]){
                    left = rightP + 1
                    if(left == right){
                        left -= 1
                    }
                }else{
                    left = leftP + 1
                    right = rightP - 1
                    if(left == right){
                        left -= 1
                    }
                }
            }
            numbers = insertAt(numbers, i, left)
        }
        return output(numbers)
    }

fun output(numbers: IntArray): Boolean{
    println(numbers.contentToString())
    System.out.flush()
    var response = readLine()!!.toInt()
    return response == 1
}

fun insertAt(arr: IntArray, key: Int, index: Int): IntArray{
    val array = arr.copyOf(arr.size + 1)
    for(i in 0 until index){
        array[i] = arr[i]
    }
    array[index] = key
    for(i in index+1..arr.size){
        array[i] = arr[i-1]
    }
    return array
}
