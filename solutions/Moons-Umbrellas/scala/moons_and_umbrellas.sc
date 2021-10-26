object Solution {
   def main(args: Array[String]) {
        // Initialize the number of test cases
        var T : Int = 0
    
    	// Read the amount of test cases, iterate for each one
    	T = scala.io.StdIn.readInt();
    	for (t <- 1 to T)
    	{
    		// Get the prices and string
    		var v = readLine().split(" ")
    		var x = v(0).toInt
    		var y = v(1).toInt
    		var S = v(2)
    
    		// Initialize the solve answers and dp array
    		var dp = Array.ofDim[Int](1000, 2) 
    		var ans : Int = 0
    		for (i <- 0 to S.length - 1)
    		{
    		    dp(i)(0) = 1000000000
    		    dp(i)(1) = 1000000000
    		}
            S(0) match{
                case 'C' => {
                    dp(0)(0) = 0
                }
                case 'J' => {
                    dp(0)(1) = 0
                }
                case _ => {
                    dp(0)(1) = 0
                    dp(0)(0) = 0
                }
            }
    
            //Solve the problem		
            for (i <- 1 to S.length() - 1)
            {
                S(i) match{
                    case 'C' => {
                        dp(i)(0) = Math.min(dp(i - 1)(0), dp(i - 1)(1) + y)
                    }
                    case 'J' => {
                        dp(i)(1) = Math.min(dp(i - 1)(1), dp(i - 1)(0) + x)
                    }
                    case _ => {
                        dp(i)(1) = Math.min(dp(i - 1)(1), dp(i - 1)(0) + x)
                        dp(i)(0) = Math.min(dp(i - 1)(0), dp(i - 1)(1) + y)
                    }
                }
            }
            ans = Math.min(dp(S.length() - 1)(0), dp(S.length() - 1)(1))
            
    		// Write this case's answer
    		println("Case #" + t + ": " + ans)
    	}
   }
}