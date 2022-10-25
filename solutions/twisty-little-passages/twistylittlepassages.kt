fun read(): Pair<Int, Int> { // Reading inputs
    val (r, p) = readLine()!!.split(" ").map { it.toInt() }
    return r to p
}

fun move(Move: String) {     //Printing action to do (walk, teleport or estimate)
    println(Move)
    System.out.flush()       //Flushing output stream
}

fun main() {
    var cases = readLine()!!.toInt()
    repeat(cases) {          // Iterate tru all cases
        val (N, K) = read()  // N: number of rooms, K: operations to solve case
        val (R, P) = read()  // R: current room and P: passages in room
        
        // Empty set
        var roomsLeft = hashSetOf<Int>() 
        // sSt {1, 2, ..., N}
        for (i in 1 .. N) roomsLeft.add(i)
        // Removes room R from the set
        if (R in roomsLeft) roomsLeft.remove(R)
        
        // Rooms visited degree sum
        var degree = 0L
        
        // Degree and count of every teleported room
        var degreeRL = 0L
        var countT = 1
        
        degree = P.toLong()
        degreeRL = degree

        repeat(K / 2) { // Iterate tru all the operations given for the case
            // Odd cases
            move("T ${roomsLeft.iterator().next()}")
            val (RT, PT) = read()
            degreeRL += PT
            countT++
            if (RT in roomsLeft) {
                roomsLeft.remove(RT)
                degree += PT
            }
            // Even cases
            move("W")
            val (RW, PW) = read()
            if (RW in roomsLeft) {
                roomsLeft.remove(RW)
                degree += PW
            }
        }
        // Degree Avg of every visited room using tp
        val degreeAvg = degreeRL / countT
        
        // Graph formula. Sum of all degrees / 2
        // Sum of degree of rooms visited + Avg degree times number of rooms left
        val result = (((degree + (degreeAvg * roomsLeft.size)) / 2)).toLong()
        move("E $result")
    }
}
