import scala.util.control.Breaks._

object Solution {
   def main(args: Array[String]) {
        // Initialize the number of test cases
        var T : Int = 0
        
    	// Read the amount of test cases, iterate for each one
    	T = Integer.valueOf(readLine())
    	
    	
    	for (t <- 1 to T)
    	{
    		// Get the length of the array and the desired cost
    		var v = readLine().split(" ")
    		var n = v(0).toInt
    		var c = v(1).toInt
	
    		// Check maximum and minimum range
    		if (c < n - 1 || c >= (n * (n + 1) / 2) )
    		{
    		    println("Case #" + t + ": IMPOSSIBLE")
    		}
    		else
    		{
        		// Declare a list of size n, 2 pivots and a placeholder
        		var ans = (1 to n + 1).toArray
        		var j : Int = 0
        		var k : Int = 0
        		var temp : Int = 0
    
                //  Make the correct permutation 
                c -= n - 1
                breakable{for (i <- n - 2 to 0 by -1)
        		{
        		    if (c >= n - i - 1)
        		    {
        		        c -= n - i - 1
        		        j = n-1
        		        k = i
        		        while (k <= j)
        		        {
        		            temp = ans(k)
        		            ans(k) = ans(j)
        		            ans(j) = temp
        		            j-=1
        		            k+=1
        		        }
        		    }
        		    else
        		    {
        		        j = c + i
        		        k = i
        		        while (k <= j)
        		        {
        		            temp = ans(k)
        		            ans(k) = ans(j)
        		            ans(j) = temp
        		            j-=1
        		            k+=1
        		        }
        		        break
        		    }
        		}}
        		
          	    // Print answer array
              	print("Case #" + t + ": ")
              	for (i <- 0 to n - 1)
              	{
              	    print(ans(i).toString + " ")
              	}
              	println("")

          	}
        }
   }
}