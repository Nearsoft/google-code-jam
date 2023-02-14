package main

import (
	"bufio"
	. "fmt" // Like C++ with the namespace, you use the functions without the 'fmt.' part
	. "os"
)

var B, T int //where B is the size of the test, and T the number of Test Sets

var rd *bufio.Reader = bufio.NewReader(Stdin)
var wr *bufio.Writer = bufio.NewWriter(Stdout)

func main() {
	Scanf("%d%d", &T, &B)
	//Iterate trought all the cases
	for i := 1; i <= T; i++ {
		var DB = make([]int, B+1)
		var L int = 1
		var R int = B
		for Q := 1; true; Q += 2 { //To keep an eye to the query number
			if Q%10 == 1 && Q != 1 {
				p := -1
				asim := -1
				for i := 1; i < L; i++ {
					if DB[i] == DB[B+1-i] {
						p = i
					} else {
						asim = i
					}
				}
				if p == -1 {
					status := query(1)
					query(1)
					if status != DB[1] {
						for i := 1; i <= L; i++ {
							DB[i] ^= 1
						}
						for i := R; i <= B; i++ {
							DB[i] ^= 1
						}
					}
				} else {
					status := query(p)
					if status != DB[p] {
						for i := 1; i <= L; i++ {
							DB[i] ^= 1
						}
						for i := R; i <= B; i++ {
							DB[i] ^= 1
						}
					}
					if asim == -1 {
						query(p)
					} else {
						if query(asim) != DB[asim] {
							for i, j := 1, len(DB)-1; i < j; i, j = i+1, j-1 {
								DB[i], DB[j] = DB[j], DB[i]
							}
						}
					}
				}
				Q += 2
			}
			DB[L] = query(L)
			DB[R] = query(R)
			L += 1
			R -= 1
			if L > R { //Means that we already have all the database
				for i := 1; i <= B; i++ {
					Printf("%d", DB[i])
				}
				Println()
				var res string
				Scanf("%s", &res)
				if res == "N" { //Exit in case that our response is incorrect
					Exit(0)
				} else {
					break
				}

			}
		}
	}
}

// To ask for a bit inside the database
func query(pos int) int {
	Printf("%d\n", pos)
	defer wr.Flush()
	var bit int
	Scanf("%d", &bit)
	return bit
}

//Let me out of go jaj
//Note: Go is a good language, the thing was that
//I just used to much python.
