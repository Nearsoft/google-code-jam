/**
Name: punchedCard.dart
Last update: 18/04/2023
*/

fun main(args: Array<String>) {
	val testCases = readln().toInt()

	for (i in 0..testCases) {
		
		var minC = Int.MAX_VALUE 
		var minM = Int.MAX_VALUE 
		var minY = Int.MAX_VALUE
		var minK = Int.MAX_VALUE
		
		for (j in 1..3) {
			// Getting the ink amount of each color in the current printer.
			val (c, m, y, k) = readln().split(' ').map(String::toInt)
			
			// Choosing the smallest option.
			minC = if (c < minC) c else minC
			minM = if (m < minM) m else minM
			minY = if (y < minY) y else minY
			minK = if (k < minK) k else minK
		}

		var totalInk = arrayOf(minC, minM, minY, minK)

		if (totalInk.sum() < 1e6) println("Case #$i: IMPOSSIBLE")
		else {
			// Using a greedy algorithm to get a combination that fulfills the needed ink.
			var neededInk = 1_000_000
			var inkCombination = "Case #$i: "
			
			for (currentInkAmount in totalInk) {
				if(currentInkAmount <= neededInk) inkCombination += "$currentInkAmount "
				else if (currentInkAmount > neededInk && neededInk > 0) inkCombination += "$neededInk "
				else inkCombination += "0 "
				neededInk -= currentInkAmount
			}
			println(inkCombination)
		}
 	}
}
