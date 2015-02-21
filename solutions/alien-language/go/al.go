package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"regexp"
	"strconv"
	"strings"
)

// readLines reads a whole file into memory
// and returns a slice of its lines.
func readLines(path string) ([]string, error) {
	file, err := os.Open(path)
	if err != nil {
		return nil, err
	}
	defer file.Close()

	var lines []string
	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		lines = append(lines, scanner.Text())
	}
	return lines, scanner.Err()
}

// writeLines writes the lines to the given file.
func writeLines(lines []string, path string) error {
	file, err := os.Create(path)
	if err != nil {
		return err
	}
	defer file.Close()

	w := bufio.NewWriter(file)
	for _, line := range lines {
		fmt.Fprintln(w, line)
	}
	return w.Flush()
}

func main() {
	filename := "A-large-practice.in"
	lines, err := readLines(filename)
	if err != nil {
		log.Fatalf("readLines: %s", err)
	}
	var languageWords []string = strings.Split(lines[0], " ")
	var regExpr []string
	// wordLength, _ := strconv.Atoi(languageWords[0])
	languageLength, _ := strconv.Atoi(languageWords[1])
	testCases, _ := strconv.Atoi(languageWords[2])
	caseN := make([]int, testCases)
	languageWords = languageWords[:0]

	//Add the language that will be tested.
	for i := 0; i < languageLength; i++ {
		languageWords = append(languageWords, lines[i+1])
	}
	//Iterate through each String regex.
	for i := 0; i < testCases; i++ {
		regExpr = append(regExpr, lines[i+languageLength+1])
		regExpr[i] = strings.Replace(regExpr[i], "(", "[", -1)
		regExpr[i] = strings.Replace(regExpr[i], ")", "]", -1)
	}
	var s []string
	for r := 0; r < len(regExpr); r++ {
		caseN[r] = 0
		for t := 0; t < len(languageWords); t++ {
			if a, _ := regexp.MatchString(regExpr[r], languageWords[t]); a {
				caseN[r]++
			}
		}
		s = append(s, "Case #"+strconv.Itoa(r+1)+": "+strconv.Itoa(caseN[r]))
	}
	if err := writeLines(s, "solution.txt"); err != nil {
		log.Fatalf("Error!", err)
	}
}
