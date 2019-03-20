package main

import (
    "fmt"
    "io/ioutil"
	"strings"
)

func check(e error) {
    if e != nil {
        panic(e)
    }
}

func doCase(countstring string) int {
	needed := 0
	sum := 0
	for index, people := range countstring {
		if sum + needed < index {
			needed = index - sum
		}
		sum += int(people - '0')
	}
	return needed
}

func main() {
    data, err := ioutil.ReadFile("A-small-practice.in")
	check(err)
	file err :=

	sData := strings.Split(string(data), "\n")
	
	for index, element := range sData{
		if index > 0 && index < len(sData) - 1{
			needed := doCase(strings.Split(element, " ")[1])
			fmt.Println(needed)
		}	
	}
}

