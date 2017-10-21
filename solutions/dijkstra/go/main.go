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
		content = make([]string, (x*2)+1)
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
	result := ""
	i := 1
	j := 1
	t, err := strconv.Atoi(input[0]) // t = total cases
	check(err)

	if t >= 1 && t <= 100 {
		for i <= t {
			lx := strings.Split(input[j], " ")
			characters := strings.Split(input[j+1], " ")
			buffer.WriteString("Case #")
			buffer.WriteString(strconv.Itoa(i))
			buffer.WriteString(": ")
			buffer.WriteString(dijkstra(getLX(lx, characters)))
			buffer.WriteString("\n")
			i++
			j += 2
		}
	}

	result = buffer.String()
	return result
}

/**
*
* @param testCase string line that has the values to evaluate
* @return YES or NO, depending on whether the string can be
* 				broken into three parts that reduce to "ijk"
 */
func dijkstra(testCase string) string {
	weExpect := "ijk"
	i := 0

	for testCase != "" {
		if i < 3 && string(testCase[0]) == string(weExpect[i]) {
			i++
			testCase = testCase[1:]
		} else {
			if len(testCase) >= 2 {
				if (len(testCase) == 2) && string(testCase[0]) == "-" {
					break
				}
				xy := getFirstElements(testCase)
				mult := getMultiplication(xy)

				testCase = strings.Replace(testCase, xy, mult, 1)
			} else {
				if testCase == "1" {
					testCase = ""
				}
				break
			}
		}
	}
	if i == 3 && testCase == "" {
		return "YES"
	}

	return "NO"
}

func getMultiplication(xy string) string {
	negative := false
	if len(xy) == 3 {
		xy = xy[1:]
		negative = true
	}

	m := make(map[string]string)
	m["11"] = "1"
	m["1i"] = "i"
	m["1j"] = "j"
	m["1k"] = "k"

	m["i1"] = "i"
	m["ii"] = "-1"
	m["ij"] = "k"
	m["ik"] = "-j"

	m["j1"] = "j"
	m["ji"] = "-k"
	m["jj"] = "-1"
	m["jk"] = "i"

	m["k1"] = "k"
	m["ki"] = "j"
	m["kj"] = "-i"
	m["kk"] = "-1"

	res := m[xy]

	if negative {
		return negate(res)
	}

	return res
}

func negate(value string) string {
	if string(value[0]) == "-" {
		return value[1:]
	} else {
		return "-" + value
	}
}

func getFirstElements(value string) string {
	if string(value[0]) == "-" {
		return value[0:3]
	} else {
		return value[0:2]
	}
}

func getLX(lx []string, characters []string) string {
	l := lx[0]
	x := lx[1]
	length, err := strconv.Atoi(l)
	check(err)
	times, err := strconv.Atoi(x)
	check(err)

	if length != len(characters[0]) {
		os.Exit(1)
	}

	text := ""

	for i := 0; i < times; i++ {
		text += characters[0]
	}

	return text
}

func main() {
	listofContent := readFile()
	result := evaluateInput(listofContent)
	// fmt.Println(result)
	fmt.Println("Results in output.txt")
	saveFile(result)
}
