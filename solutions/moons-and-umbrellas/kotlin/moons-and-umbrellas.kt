fun main(args: Array<String>) {
    // Initialize the number of test cases
    var T : Int

	// Read the amount of test cases, iterate for each one
	T = Integer.valueOf(readLine())
	for (t in 1..T)
	{
		// Get the prices and string
		var (X,Y,S) = readLine()!!.split(" ")
		var x = X.toInt()
		var y = Y.toInt()

		// Initialize the solve answers and dp array
		var dp = Array(1000, {IntArray(2)})
		var ans : Int = 0
		for (i in 0..S.length - 1)
		{
		    dp[i][0] = 1000000000
		    dp[i][1] = 1000000000
		}
        when(S[0]){
            'C' -> {
                dp[0][0] = 0
            }
            'J' -> {
                dp[0][1] = 0
            }
            else -> {
                dp[0][1] = 0
                dp[0][0] = 0
            }
        }

        //Solve the problem		
        for (i in 1..S.length - 1)
        {
            when(S.get(i)){
                'C' -> {
                    dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1] + y)
                }
                'J' -> {
                    dp[i][1] = Math.min(dp[i - 1][1], dp[i - 1][0] + x)
                }
                else -> {
                    dp[i][1] = Math.min(dp[i - 1][1], dp[i - 1][0] + x)
                    dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1] + y)
                }
            }
        }
        ans = Math.min(dp[S.length - 1][0], dp[S.length - 1][1])
        
		// Write this case's answer
		println("Case #$t: $ans")
	}
}