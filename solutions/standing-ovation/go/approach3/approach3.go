package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

func main() {
	pwd, _ := os.Getwd()
	file, err := ioutil.ReadFile(pwd + "/" + os.Args[1])

	if err != nil {
		fmt.Println(err)
		return
	}

	input := string(file)
	input = input[:len(input)-1]
	results := getResults(input)
	printResults(results)
}

func printResults(results []int) {
	for i := 0; i < len(results); i++ {
		fmt.Printf("Case #%v: %v\n", i+1, results[i])
	}
}

func getResults(input string) []int {
	lines := strings.Split(input, "\n")
	var results []int

	for i := 1; i < len(lines); i++ {
		testCase := lines[i]
		results = append(results, getTestCaseResult(testCase))
	}

	return results
}

func getTestCaseResult(testCase string) int {
	maxShyness, _ := strconv.Atoi(strings.Split(testCase, " ")[0])
	audience := strings.Split(strings.Split(testCase, " ")[1], "")

	standingPeopleCount := 0
	friendsCount := 0

	for shynessLevel := 0; shynessLevel <= maxShyness; shynessLevel++ {
		peopleCount, _ := strconv.Atoi(audience[shynessLevel])

		if shynessLevel > standingPeopleCount {
			friendsNeeded := shynessLevel - standingPeopleCount
			friendsCount += friendsNeeded
			standingPeopleCount += friendsNeeded + peopleCount
		} else {
			standingPeopleCount += peopleCount
		}
	}

	return friendsCount
}
