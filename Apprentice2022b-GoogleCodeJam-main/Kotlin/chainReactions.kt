/*
* So I noticed that we never point backwards what do I mean by that, if we start from the back we will never have a
* pointer that requires us to go back through the array I checked it by reading all test cases and comparing
* for (i in n downTo 1) {
*   if ( i < ptrN[i]) {
*       print("false")
*   }
* }
*
* still it took me quite a while to figure out why my code wasn't working, but it finally did
* you need the biggest possible placeholder for temp, if you replace Int.MAX_VALUE with any number smaller than the ones
* we are going to compare out input to we are kind of screwed, as to why? see the algorithm below
* you can put any number instead of MAX_VALUE, but I used MAX_VALUE as a safeguard, in this case the program won't break unless
* we input a number higher than the biggest possible integer.
* nvm integers are small, we need to use long for this to work
* */

import kotlin.math.max
import kotlin.math.min

fun main() {
    val caseN = readLine()!!.toInt() // Read Number of test cases

    for (tc in 1..caseN) { // Iterate through them

        val n = readLine()!!.toInt() // Number of nodes

        // This line below was not working for all cases when it was a List of Int and for some reason I can't use UInt.MAX_VALUE
        val funN = listOf<Long>(0L) + readLine()!!.split(" ").map { it.toLong() } // Fun numbers mapped to Arr offset 1 so that pointers are correct
        val ptrN = listOf<Int>(0) + readLine()!!.split(" ").map { it.toInt() } // Pointer numbers mapped to Arr offset 1 so that pointers are correct
        val temp = LongArray(n + 1) { Long.MAX_VALUE } // Temporal array used to modify data

        var total = funN.sum() // So in this case total is going to represent the sum of all the numbers in the array, then we'll modify this number

        for (i in n downTo 1) { // we iterate the array backwards
            if (temp[i] == Long.MAX_VALUE) { // we convert the unknown number to 0 for us to work with it
                // btw this was the problem I tried to start the Array as an Array of 0, and it doesn't work, again idk why...
                temp[i] = 0
            }
            total -= min(funN[i], temp[i]) // on our first run we are not going to subtract anything since temp[i] = 0
            var funM = max(funN[i], temp[i])
            temp[ptrN[i]] = min(temp[ptrN[i]], funM)  // then we change our temp value
        }
        print("Case #$tc: $total\n")
    }
}

/*
* This is going to be the algorithm explanation since this is the juicy part
* let's draw our 3 arrays
* funN = [0, 60, 20, 40, 50]
* ptrN = [0, 0 , 1 , 1 , 2 ]                                    btw ? = MAX_VALUE or the biggest possible integer
* temp = [?, ? , ? , ? , 0 ] since we have run once             our total at the moment is 170, and we are going to subtract 0 from temp[i] or temp[4]
*                                                               on the second line we are calculation the max number between funN[i] and temp[i] or max(50, 0) so funM = 50
*                                                               then we change our temp at temp[ptrN[i]] or temp[2] to the min between(temp[ptrN[i]], funM) or (?, 50) so our new Arr looks like this
* temp = [?, ? , 50, ? , 0]                                     and we repeat the process
*
* funN = [0, 60, 20, 40, 50]
* ptrN = [0, 0 , 1 , 1 , 2 ]                                    now we are at i = 3, so we check if temp[3] is == to ?, if so we change it to 0
* temp = [?, ? , 50, 0 , 0 ]                                    our total at the moment is 170, and we are going to subtract 0 from  temp[3]
*                                                               we do our max(40 , 0) so funM = 40
*                                                               now we set temp[1] to the min between(?, 40)
*
* funN = [0, 60, 20, 40, 50]                                    our total at the moment is 170, and now our min function has a different value other than 0 so total is = 170 - min(20, 50) or 170 - 20 so, our new total is 150
* ptrN = [0, 0 , 1 , 1 , 2 ]                                    we do our max(20 , 50) so funM = 50
* temp = [?, 40, 50, 0 , 0 ]                                    now we set temp[1] to the min between(40, 50) so 40
*
* funN = [0, 60, 20, 40, 50]                                    our total at the moment is 150, so total is = 170 - min(60, 40) or 150 - 40 so, our new total is 110
* ptrN = [0, 0 , 1 , 1 , 2 ]                                    we do our max(60 , 40) so funM = 60
* temp = [?, 40, 50, 0 , 0 ]                                    now we set temp[0] to the min between(?, 60) so 60
*
* funN = [0, 60, 20, 40, 50]                                    we don't run the last step our for loop ends in 1
* ptrN = [0, 0 , 1 , 1 , 2 ]
* temp = [60, 40, 50, 0 , 0 ]
* */