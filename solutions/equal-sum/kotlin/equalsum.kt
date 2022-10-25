fun powers_of_two(N:Int):MutableList<Long> {
  /*
    This function generates a tactical N set of numbers made of powers of 2
    and the remaining N-30 numbers will be the last N numbers before 10**9 inclusive.

    As N is always 100, this fuction is always performed without problems
   */

  var A = mutableListOf<Long>() // Open empty list

  // Append first 30 powers of two
  for (t in 0..29) {
        val base = 2
        var exponent = t
        var result =  Math.pow(base.toDouble(), exponent.toDouble())
        A.add(result.toLong())
  }

  // Append the other 70 garbage numbers (in this case the last 70 allowed)
  for (t in 0..N-31) {
        val base = 10
        val exponent = 9
        val max_value = Math.pow(base.toDouble(), exponent.toDouble())
        A.add(max_value.toLong()-t)
  }
  
  return A
}

fun solve_sum(A:MutableList<Long>, B:MutableList<Long>):MutableList<Long> {
  /*
    This function implements a greedy approach to minimize the sum difference
    among two sets A and B
   */
  var C = A + B
  C = C.sortedDescending().toMutableList()

  var a_sum:Long = 0L
  var b_sum:Long = 0L
  var equal_sum_set = mutableListOf<Long>() // generate empty list to store resulting set

  for (i in C.indices) {
        if (a_sum > b_sum) {
          b_sum += C[i]
          equal_sum_set.add(C[i])
        } else {
          a_sum += C[i]
        }
    }
  return equal_sum_set
  
}


fun main() {
  
  val T:Int  = readln().toInt(); // Read number of cases


  for (t in 0..T-1) {
        var N:Int  =  readln().toInt(); // Read N int
        if (N == -1) {
          break
        }

        // Generate our A set of powers of two
        var A = powers_of_two(N)

        // Print A set in terminal
        println(A.joinToString(" "))

        // Read B set from terminal
        var B = readln().split(' ').map{ it.toLong() }
        B = B.toMutableList()

        // Solve case
        var C = solve_sum(A,B)
        println(C.joinToString(" "))

        
        
  }
  
}
