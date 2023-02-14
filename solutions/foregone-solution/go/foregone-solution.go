package main

import (
	"fmt"
	"strconv"
)

// Just for the reading of the test cases
func main(){
    var T int
    fmt.Scanf("%d", &T)
    for caseNumber := 1; caseNumber <= T ; caseNumber++{
        A, B := foregone()
        fmt.Printf("Case #%d: %s %s\n", caseNumber, A, B)
    }
    
}

// The solution of the actual problem
func foregone() (A, B string)  {
    var N string
    fmt.Scanf("%s", &N)
	var four string = strconv.Itoa(4)
	A = ""
	B = ""

	for _, char := range N {
		if c := string(char); four == c {
			A += "2"
			B += "2"
		} else {
			A += c
			B += "0"
		}
	}
	
	return 
}