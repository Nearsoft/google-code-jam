fun maxCost(n: Int): Int{
    return (n+2)*(n-1)/2;
}

fun minCost(n: Int): Int{
    return n - 1;
}

fun reverseReversort(l: Int, c: Int){
    //Check if is imposible
    if(c < minCost(l) || c > maxCost(l)){
        print("IMPOSSIBLE");
        print("\n");
        return;
    }     
    //Create cost list
    var remainCost: Int = c;
    var listOfCost: MutableList<Int> = mutableListOf();
    
    for(i: Int in l downTo 2){
        val minVal: Int = i - 3;
        var tryVal: Int = i;
        while(remainCost - tryVal <= minVal){
            tryVal--;
        }
        remainCost -= tryVal;
        listOfCost.add(tryVal);
    }
    
    //Reversort index
    var listOfIndexes: IntArray = IntArray(l);
    var listOfValues: MutableList<Int> = mutableListOf();
    for (i: Int in 1..l){
        listOfIndexes[i-1] = i-1;
        listOfValues.add(i);
    }
    
    for (i : Int in 0..l-2){
        var toPosition: Int = listOfCost[i] + i;
        listOfIndexes.reverse(i, toPosition);
    }
    
    //Create list of result
    var results: IntArray = IntArray(l);
    for (i: Int in 1..l){
        results[listOfIndexes[i-1]] = i;
    }
    
    //Print result
    print(results.joinToString(" "));
    print("\n");
}

fun main() {
    val times: Int = readLine()!!.toInt();
    
    for(i: Int in 1..times){
        print("Case #" + i.toString() + ": ");
        val input: String? = readLine();
        val listInput:List<String> = input!!.split(" ");
        val length: Int = listInput[0].toInt();
        val targetCost: Int = listInput[1].toInt();
        reverseReversort(length, targetCost);
    }
}