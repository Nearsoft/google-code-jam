package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main() {
	reader := bufio.NewReader(os.Stdin)
	testCases, _ := reader.ReadString('\n')
	testCases = strings.TrimSpace(testCases)
	testCasesNumber, _ := strconv.Atoi(testCases)
	for caseNumber := 0; caseNumber < testCasesNumber; caseNumber++ {
		number, _ := reader.ReadString('\n')
		number = strings.TrimSpace(number)
		var r1 string = ""
		var r2 string = ""
		for i := 0; i < len(number); i++ {
			digit := string(number[i])
			if digit == "4" {
				r1 = r1 + "2"
				r2 = r2 + "2"
			} else {
				r1 = r1 + digit
				if r2 != "" {
					r2 = r2 + "0"
				}
			}
		}
		fmt.Printf("Case #%d: %s %s\n", caseNumber+1, r1, r2)
	}

}
