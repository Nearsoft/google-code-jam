package main

import (
	"bufio"
	"bytes"
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

// Reading files requires checking most calls for errors.
// This helper will streamline our error checks below.
func check(e error) {
	if e != nil {
		panic(e)
	}
}

func saveFile(data string) {
	d1 := []byte(data)
	err := ioutil.WriteFile("output.txt", d1, 0644)
	check(err)
}

func readFile() []string {
	i := 0
	scanner := bufio.NewScanner(os.Stdin)
	var content []string
	if scanner.Scan() {
		t := scanner.Text()
		x, err := strconv.Atoi(t)
		check(err)
		content = make([]string, x+1)
		content[i] = t
		i = 1
		for scanner.Scan() {
			ucl := scanner.Text()
			content[i] = ucl
			i++
		}
	}
	return content
}

func evaluateInput(input []string) string {
	var buffer bytes.Buffer
	fmt.Println(buffer.String())
	result := ""
	i := 1
	t, err := strconv.Atoi(input[0]) // t = total cases
	check(err)

	if t >= 1 && t <= 100 {
		for i <= t {
			testCase := strings.Split(input[i], " ")
			buffer.WriteString("Case #")
			buffer.WriteString(strconv.Itoa(i))
			buffer.WriteString(": ")
			buffer.WriteString(evaluateCase(testCase))
			buffer.WriteString("\n")
			i++
		}
	}
	result = buffer.String()
	return result

}

/**
*
* @param testCase string line that has the values to evaluate
* @return number of friends that must be invited
 */
func evaluateCase(testCase []string) string {
	invited := 0
	aplauding := 0
	for i := 0; i < len(testCase[1]); i++ {
		num, err := strconv.Atoi(string(testCase[1][i]))
		check(err)
		if num > 0 {
			if aplauding < i {
				invited += i - aplauding
				aplauding = i
			}
			aplauding += num
		}
	}

	invitedString := strconv.Itoa(invited)

	return invitedString
}

func main() {
	listofContent := readFile()
	result := evaluateInput(listofContent)
	fmt.Println(result)
	fmt.Println("Results in output.txt")
	saveFile(result)
}
