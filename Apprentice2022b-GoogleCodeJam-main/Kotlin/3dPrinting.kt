fun main(args: Array<String>) {
    run()
}

fun run(){
    val tc = readLine()!!.toInt() // number of cases
    val results = mutableListOf<String>() // array where results will be added

    for(i in 1..tc){
        val case = ArrayList<List<String>>() // array where printers data will be stored for each case
        for(x in 1..3){
            val input = readLine().toString() // reads printers information
            val stringArray: List<String> = input.split(" ") // splits input
            case.add(stringArray) // add info about xth printer
        }
        results.add(calculateInk(case)) //solves each case and adds the answer to results
    }

    // for that gives format to the output
    for(i in 0 until tc){
        val case = i+1
        if(results[i] == "IMPOSSIBLE"){
            println("Case #$case: IMPOSSIBLE")
        }
        else{
            println("Case #$case: " + results[i])
        }
    }

}

/**
 * Method that calculates the amount of ink that will be used
 */
fun calculateInk(case:ArrayList<List<String>>): String{
    val min = ArrayList<Int>() // minimum values
    for(i in 0..3){
        val list = listOf(case[0][i].toInt(),case[1][i].toInt(),case[2][i].toInt())
        val minVal = list.minOrNull()!! // gets the min value of each ink color per case
        min.add(minVal)
    }
    val sum = min.sum() //result of the sum of all the minimum values
    if(sum < 1000000){
        return "IMPOSSIBLE"
    }
    else if(sum == 1000000){
        return min.joinToString(" ")
    }
    else{
        var dif:Int = sum - 1000000
        while(true){
            val max:Int = min.maxOrNull()!! // find the max num of the min nums
            if(dif == 0){ // if there is nothing else to subtract
                break
            }
            val max_pos = min.indexOf(max) // max value position
            if((max - dif) < 0){
                dif -= max
                min[max_pos] = 0
            }
            else{
                min[max_pos] = max - dif
                dif = 0
            }

        }
        return min.joinToString(" ")
    }
}
/**
 * TEST CASE
3
300000 200000 300000 500000
300000 200000 500000 300000
300000 500000 300000 200000
1000000 1000000 0 0
0 1000000 1000000 1000000
999999 999999 999999 999999
768763 148041 178147 984173
699508 515362 534729 714381
949704 625054 946212 951187

 */
