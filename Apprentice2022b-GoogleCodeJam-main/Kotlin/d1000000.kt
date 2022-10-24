fun main(args: Array<String>) {
    // input of number of cases (T)
    var cases = readln().toInt();
    // Array of results
    var results = IntArray(cases);
    // Input of Cases + storing results of each
    for (i in 1..cases){
        results[i-1] = calculateStraight();
    }
    //Printing the results
    for (i in 1..cases){
        println("Case #$i: " + results[i-1]);
    }
}

// Function to calculate max straight number
fun calculateStraight(): Int {
    // Input of number of dices
    var dices = readln().toInt();
    // Input of Array of dices (Int)
    var diceSides = readln().split(" ").map { it.toInt() }
    //We sort the array
    diceSides = diceSides.sorted()
    //We only need two parameters to know if we can keep a straight (min)
    var minSides = diceSides[0];
    var maxStraight: Int = 0;

    // For loop to calculate maxStraight
    for (sides in diceSides){
        if (sides >= minSides && sides  > maxStraight){
            maxStraight += 1;
        }else{
            continue;
        }
    }

    //return result Int
    return maxStraight;
}