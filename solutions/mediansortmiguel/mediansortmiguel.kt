/************************
Median Sort solution
Google Code Jam Qualification Round 2021
https://codingcompetitions.withgoogle.com/codejam/round/000000000043580a/00000000006d1284
Oct/21
*************************/

fun main(){
    var (a, b, c) = readLine()!!.split(' ')
    var testCases = a.toInt()
    var numOfElemnts = b.toInt()
    var numOfQueries = c.toInt()
    while(testCases > 0){
        if(!medianSort(numOfElemnts, numOfQueries)){
          return
        }
      testCases--
    }
}
    
// Median Sort
fun medianSort( nE: Int, nQ: Int): Boolean{
	var nQL = nQ
	var numbers = intArrayOf(1,2)
	for (i in 3..nE){
		var left = 0
		var right = numbers.size - 1
		while (right - left >= 1 && nQL > 0){
			var leftP = left + ((right-left) / 3)
			var rightP = right - ((right-left) / 3)
			println(" ${numbers[leftP]} ${numbers[rightP]} ${i}")
			val med = readLine()!!.toInt()
			nQL--
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
	var res = "${numbers[0]}"
	for (i in 1 until numbers.size) {
		res += " " + numbers[i]
	}
	println(res)
    var response = readLine()!!.toInt()
    return response == 1
}

fun insertAt(arr: IntArray, key: Int, index: Int): IntArray{
    var res: IntArray = IntArray(arr.size + 1)
    if (index >= 0) {
        res = arr.copyOf(arr.size + 1)
    }
    res[index] = key
    if (arr.size + 1 - (index + 1) >= 0) {
		arr.copyInto(res, index + 1, index, res.size - 1)
    }
    return res
}