package main

import (
	"fmt"
	"math"
	"strconv"
)

// Just for the testcases
func main() {
	var T int
	fmt.Scanf("%d", &T)
	for caseNumber := 1; caseNumber <= T; caseNumber++ {
		S := nestingDepth()
		fmt.Printf("Case #%d: %s\n", caseNumber, S)
	}
}

// The solution
func nestingDepth() (nestingS string) {
	var S string
	var parentesisAbiertos int = 0
	fmt.Scanf("%s", &S)

	nestingS = ""

	for _, val := range S {
		char := string(val)

		if digit, _ := strconv.Atoi(char); parentesisAbiertos == digit {
			nestingS += char
		} else if remain := digit - parentesisAbiertos; remain > 0 {
			for i := 0; i < remain; i++ {
				nestingS += "("
				parentesisAbiertos++
			}
			nestingS += char
		} else {
			for i := 0; i < int(math.Abs(float64(remain))); i++ {
				nestingS += ")"
				parentesisAbiertos--
			}
			nestingS += char
		}
	}

	for i := 0; i < parentesisAbiertos; i++ {
		nestingS += ")"
	}

	return
}
