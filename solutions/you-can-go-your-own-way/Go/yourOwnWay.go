package main

import (
	"fmt"
	"strings"
)

func main() {
	var nCases int
	fmt.Scanln(&nCases)

	for i := 0; i < nCases; i++ {
		var unnecessary string
		fmt.Scanln(&unnecessary)
		var originalWay string
		fmt.Scanln(&originalWay)

		myWay := strings.Map(func(char rune) rune {
			switch char {
			case 'E':
				return 'S'
			default:
				return 'E'
			}
		}, originalWay)

		fmt.Printf("%s%d%s %s\n", "Case #", (i + 1), ":", myWay)
	}
}
