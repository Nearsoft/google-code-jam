fun main(args: Array<String>) {
    //input cases
    val cases = readln().toInt();
    //Array of cases
    val casesArray: MutableList<MutableList<Int>> = mutableListOf();
    for (i in 0 until cases) { //O(n)
        val (x, y) = readLine()!!.split(' ').map(String::toInt);
        val temp: MutableList<Int> = mutableListOf(x, y);
        casesArray.add(temp);
    }
    //Print cases
    var case: Int = 0;
    for (i in 0 until cases) {   
        case = i + 1;
        println("Case #$case:");
        printPunchCard(casesArray[i][0], casesArray[i][1])
    }
}

fun printPunchCard(row: Int, col: Int) {
    //Top left print
    print("..");
    for (i in 1 until col) { 
        print("+-");
    }
    print("+\n");
    print("..");
    for (i in 1 until col) { 
        print("|.");
    }
    print("|\n");
    // End of top left print

    //Constructing the string to print multiple times
    var pluss: String = "";
    var bars: String = "";

    //Plus sign string
    for (i in 0 until col) { 
        pluss = pluss.plus("+-");
    }
    pluss = pluss.plus("+");

    //Bar sign string
    for (i in 0 until col ) { 
        bars = bars.plus("|.");
    }
    bars = bars.plus("|");

    //Printing rest of the punch card
    for (i in 0 until row) { 
        print("$pluss" + "\n");
        if (i == row - 1) {
            continue;
        } else {
            print("$bars" + "\n");
        }
    }
}
