package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strconv"
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
	filename := "C-large-practice.in"
	lines, err := readLines(filename)
	if err != nil {
		log.Fatalf("readLines: %s", err)
	}
	keyOf := map[string]string{
		"a": "2",
		"b": "22",
		"c": "222",
		"d": "3",
		"e": "33",
		"f": "333",
		"g": "4",
		"h": "44",
		"i": "444",
		"j": "5",
		"k": "55",
		"l": "555",
		"m": "6",
		"n": "66",
		"o": "666",
		"p": "7",
		"q": "77",
		"r": "777",
		"s": "7777",
		"t": "8",
		"u": "88",
		"v": "888",
		"w": "9",
		"x": "99",
		"y": "999",
		"z": "9999",
		" ": "0",
	}

	testCases, err := strconv.Atoi(lines[0])
	var s []string
	ans, pre := "", ""

	for i := 1; i <= testCases; i++ {
		pre, ans = "", ""
		for _, rune := range lines[i] {
			indx, _ := strconv.Unquote(strconv.QuoteRune(rune))
			letterKey := keyOf[indx]
			if pre == strconv.Itoa(int(letterKey[0])) {
				ans += " "
			}
			ans += letterKey
			pre = strconv.Itoa(int(letterKey[0]))
		}
		s = append(s, "Case #"+strconv.Itoa(i)+": "+ans)
	}

	if err := writeLines(s, "solution.txt"); err != nil {
		log.Fatalf("Error!", err)
	}
}
