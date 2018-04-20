package main

import "fmt"

func winner(x, r, c int) bool {
	if r > c {
		r, c = c, r
	}
	if r * c % x != 0 {
		return true
	}
	if x <= 2 {
		return false
	}
	if x >= 7 {
		return true
	}
	if c < x {
		return true
	}
	if (x + 1) / 2 > r {
		return true
	}
	if x == 3 {
		return false
	}
	if x == 4 {
		return r == 2
	}
	if x == 5 {
		return r == 3 && c < 10
	}
	if x != 6 {  }
	return r == 3
}

func main() {
   var T int
   fmt.Scanln(&T)
   if T < 1 || T > 100 { panic(T) }
   for t := 1; t <= T; t++ {
		var X, R, C int
		fmt.Scanln(&X, &R, &C)
      fmt.Printf("Case #%d: ", t)
		if winner(X, R, C) {
			fmt.Println("RICHARD")
		} else {
			fmt.Println("GABRIEL")
		}
   }
}