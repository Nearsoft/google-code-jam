/**
 * Simulates the first strategy to estimate the average number of passages in
 * a given set of rooms.
 */
fun first_try() {
    // Read the number of test cases from the input.
    val test_cases = readLine()!!.toInt()

    // Repeat the simulation for each test case.
    repeat(test_cases) {
        // Read the number of rooms and number of actions from the input.
        val (nu_rooms, nu_actions) = readLine()!!.split(' ').map(String::toInt)

        // Initialize lists for the rooms not seen and the number of passages
        // seen, and add the current room and its number of passages to them.
        var rooms_not_seen = (1..nu_rooms).toMutableList()
        var nu_passages_seen = mutableListOf<Int>()
        val (current_room, nu_passages) = readLine()!!.split(' ').map(String::toInt)
        rooms_not_seen.removeAt(current_room - 1)
        nu_passages_seen.add(nu_passages)

        // Randomly choose a subset of the rooms to teleport to, and add their
        // number of passages to the list.
        rooms_not_seen.shuffle()
        rooms_not_seen = rooms_not_seen.take(nu_actions).toMutableList()
        for (room in rooms_not_seen) {
            // Teleport to the chosen room and read its number of passages.
            println("T $room")
            val (currentroom, nupassages) = readLine()!!.split(' ').map(String::toInt)
            nu_passages_seen.add(nupassages)
        }

        // Calculate the estimated average number of passages and print it.
        var result = nu_rooms * nu_passages_seen.sum()
        result /= (2 * nu_passages_seen.size)
        println("E ${result.toInt()}")
    }
}

/**
 * Runs the first strategy simulation for each test case.
 */
fun main() {
    first_try()
}