package main

import "fmt"
import (
	"math"
	"strconv"
)

var mul_table = [5][5]int{
	{0,  0,  0,  0,  0},
	{0,  1,  2,  3,  4},
	{0,  2, -1,  4, -3},
	{0,  3, -4, -1,  2},
	{0,  4,  3, -2, -1},
}

func mul(a, b int) int {
	var sign int
	if a*b > 0 {
		sign = 1
	} else {
		sign = -1
	}
	var row = int(math.Abs(float64(a)))
	var col = int(math.Abs(float64(b)))
	return sign * mul_table[row][col]
}

func reduce(codes []int, times int) int {
	var value = 1
	for _, code := range codes {
		value = mul(value, code)
	}
	return exponentiate(value, times)
}

func exponentiate(value int, times int) int {
	var limit = times % 4
	var power = 1
	for limit > 0 {
		power = mul(power, value)
		limit--
	}
	return power
}

func viable_split(codes []int, times int) bool {
	var maybe_2, maybe_3 int = 1, 1
	for times > 0 {
		for _, code := range codes {
			if maybe_2 != 2 {
				maybe_2 = mul(maybe_2, code)
			} else if maybe_3 != 3 {
				maybe_3 = mul(maybe_3, code)
			} else {
				return true
			}
		}
		times--
	}
	return maybe_2 == 2 && maybe_3 == 3
}

func main() {
	var test_cases_str string
	fmt.Scan(&test_cases_str)
	test_cases, _ := strconv.Atoi(test_cases_str)

	var test_L_str, test_X_str, test_I_str string
	var test_I []int

	for test := 1; test <= test_cases; test++ {
		fmt.Scan(&test_L_str, &test_X_str, &test_I_str)

		test_L, _ := strconv.Atoi(test_L_str)
		test_X, _ := strconv.Atoi(test_X_str)

		test_I = make([]int, test_L)
		for idx, ch := range test_I_str {
			test_I[idx] = int(ch) - int('i') + 2
		}

		test_1 := reduce(test_I, test_X) == -1
		test_2 := viable_split(test_I, int(math.Min(8, float64(test_X))))

		if test_1 && test_2 {
			fmt.Printf("Case #%d: YES\n", test)
		} else {
			fmt.Printf("Case #%d: NO\n", test)
		}
	}
}
