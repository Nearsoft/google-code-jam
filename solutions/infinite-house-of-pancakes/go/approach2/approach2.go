func main() {
	var T int
	fmt.Scan(&T)
	for t := 0; t < T; t++ {
	  var N int
	  fmt.Scan(&N)
	
	  ps := make([]int, N, N)
	  
	  for d := 0; d < N; d++ {
		  fmt.Scan(&dishes[d])
	  }
	  sort.Sort(sort.Reverse(sort.IntSlice(dishes)))
	
	  min := 10000
	  for lim := 1; lim <= dishes[0]; lim++ {
		  moves := 0
		  for _, dish := range dishes {
			  moves += (dish - 1) / lim
		  }
		  if moves < ret {
			  min = moves
		  }
	  }
	
		 fmt.Printf("Case #%v: %v\n", t+1, min)
	}
  }