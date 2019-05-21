package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

// M holds the values of the multiplications
var M = [5][5]int{
	{0, 0, 0, 0, 0},
	{0, 1, 2, 3, 4},
	{0, 2, -1, 4, -3},
	{0, 3, -4, -1, 2},
	{0, 4, 3, -2, -1}}

func readln(r *bufio.Reader) (string, error) {
	var (
		isPrefix = true
		err      error
		line, ln []byte
	)
	for isPrefix && err == nil {
		line, isPrefix, err = r.ReadLine()
		ln = append(ln, line...)
	}
	return string(ln), err
}

func absolute(n int) int {
	if n < 0 {
		n = -n
	}
	return n
}

func conversion(c string) int {
	switch c {
	case "i":
		return 2
	case "j":
		return 3
	case "k":
		return 4
	}
	return 0
}

func min(a, b int) int {
	if b < a {
		return b
	}
	return a
}

func mul(a, b int) int {
	var sign = 0
	if a*b > 0 {
		sign = 1
	} else {
		sign = -1
	}
	return sign * M[absolute(a)][absolute(b)]
}

func power(a, n int) int {
	var value = 1
	for i := 0; i < n%4; i++ {
		value = mul(value, a)
	}
	return value
}

func multiplayAll(S string, L int, X int) int {
	var value = 1
	for i := 0; i < L; i++ {
		var charValue = conversion(string(S[i]))
		value = mul(value, charValue)
	}
	return power(value, X)
}

func constructFirstTwo(S string, L, X int) bool {
	var iValue = 1
	var jValue = 1
	for i := 0; i < X; i++ {
		for j := 0; j < L; j++ {
			var charValue = conversion(string(S[j]))
			if iValue != 2 {
				iValue = mul(iValue, charValue)
			} else if jValue != 3 {
				jValue = mul(jValue, charValue)
			}
		}
	}
	return (iValue == 2) && (jValue == 3)
}

func main() {

	//reader used to read line by line
	reader := bufio.NewReader(os.Stdin)
	text, _, _ := reader.ReadLine()

	numberOfCases, _ := strconv.Atoi(string(text))

	for i := 0; i < numberOfCases; i++ {

		text, _, _ := reader.ReadLine()

		//split a line into the desired inputs
		line := strings.Split(string(text), " ")
		// L holds the length of the string
		L, _ := strconv.Atoi(line[0])
		// X holds how many time we need to repeat L to get S
		X, _ := strconv.Atoi(line[1])

		var S string
		var e error
		S, e = readln(reader)

		if e != nil {
			fmt.Println("An error has ocurred!")
		}

		var reduction = multiplayAll(S, L, X)
		var ok1 = reduction == -1
		var ok2 = constructFirstTwo(S, L, min(8, X))
		var isValid string
		if ok1 && ok2 {
			isValid = "YES"
		} else {
			isValid = "NO"
		}

		//print case solution with the specified format
		fmt.Println("Case #" + strconv.Itoa(i+1) + ": " + isValid)
	}

}
