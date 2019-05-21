package main

import (
	"fmt"
	"strconv"
	"math"
)

func get_winner(X, R, C int) string {
	S := int(math.Min(float64(R), float64(C)))
	L := int(math.Max(float64(R), float64(C)))
	if (S * L) % X != 0 {
		return "RICHARD"
	}
	if X == 3 && S == 1 {
		return "RICHARD"
	}
	if X == 4 && S <= 2 {
		return "RICHARD"
	}
	if X == 5 && (S <= 2 || (S == 3 && L == 5)) {
		return "RICHARD"
	}
	if X == 6 && S <= 3 {
		return "RICHARD"
	}
	if X >= 7 {
		return "RICHARD"
	}
	return "GABRIEL"
}

func main() {
	var test_cases_str string
	fmt.Scan(&test_cases_str)
	test_cases, _ := strconv.Atoi(test_cases_str)

	var test_X_str, test_R_str, test_C_str string

	for test := 1; test <= test_cases; test++ {
		fmt.Scan(&test_X_str, &test_R_str, &test_C_str)

		test_X, _ := strconv.Atoi(test_X_str)
		test_R, _ := strconv.Atoi(test_R_str)
		test_C, _ := strconv.Atoi(test_C_str)

		winner := get_winner(test_X, test_R, test_C)
		fmt.Printf("Case #%d: %s\n", test, winner)
	}
}