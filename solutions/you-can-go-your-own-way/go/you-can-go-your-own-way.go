package main

import (
	"bufio"
	"fmt"
	. "fmt"
	. "os"
)

var rd *bufio.Reader = bufio.NewReader(Stdin)
var wr *bufio.Writer = bufio.NewWriter(Stdout)

func main() {
	var T, N int
	var S string

	Scanf("%d", &T)

	for i := 1; i <= T; i++ {
		var sol string
		sol = ""
		Scanf("%d", &N)
		Scanf("%s", &S)

		for _, ch := range S {
			if ch == 83 {
				sol += "E"
			} else {
				sol += "S"
			}
		}
		fmt.Printf("Case #%d: %s\n", i, sol)
		defer wr.Flush()
	}

}
