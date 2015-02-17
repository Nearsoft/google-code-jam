package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	// "regexp"
	"sort"
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
	filename := "A-small-practice.in"
	lines, err := readLines(filename)
	if err != nil {
		log.Fatalf("readLines: %s", err)
	}

	testCases, _ := strconv.Atoi(lines[0])
	var s []string

	for i := 1; i < testCases*3; i += 3 {
		var v1 []string = strings.Split(lines[i+1], " ")
		var v2 []string = strings.Split(lines[i+2], " ")
		var v1i, v2i []int
		ans := 0
		tmpLen, _ := strconv.Atoi(lines[i])
		for j := 0; j < tmpLen; j++ {
			tmp, _ := strconv.Atoi(v1[j])
			v1i = append(v1i, tmp)
			tmp, _ = strconv.Atoi(v2[j])
			v2i = append(v2i, tmp)
		}
		sort.Sort(sort.IntSlice(v1i))
		sort.Sort(sort.Reverse(sort.IntSlice(v2i)))

		for j := 0; j < tmpLen; j++ {
			ans += v1i[j] * v2i[j]
		}
		s = append(s, "Case #"+strconv.Itoa((i/3)+1)+": "+strconv.Itoa(ans))
	}
	if err := writeLines(s, "solution.txt"); err != nil {
		log.Fatalf("Error!", err)
	}
}
