// CREATED BY PEDRO DE LA REE 13/02/2015


package main

import (
	"io/ioutil"
	"fmt"
	"strings"
	"strconv"
)

func readFile(filename string) []string{
	//read the file
	file, err := ioutil.ReadFile(filename)
	if err != nil {
		panic("Error opening the file")
	}
	str := string(file)
	//fmt.Println(str)
	return strings.Split(str,"\n")
}


func T9(test int, dict map[string]string, word string) {
	
	var result string = ""
	for i := 0; i < len(word); i++ {
		var X string = string(dict[string(word[i])])
		if len(result) != 0 && string(X[len(X)-1]) == string(result[len(result)-1]) {
			result += " "
		}
		result += X
		// fmt.Printf(X+"\n")

		// fmt.Printf(string(result[len(result)-1])+"\n")

	}
	//return result
    fmt.Printf("Case #%d: %s\n", test, result)
}


func main() {
	dict := make(map[string]string)
	
	//create dictionary
	dict["a"] = "2"
	dict["b"] = "22"
	dict["c"] = "222"
	dict["d"] = "3"
	dict["e"] = "33"
	dict["f"] = "333"
	dict["g"] = "4"
	dict["h"] = "44"
	dict["i"] = "444"
	dict["j"] = "5"
	dict["k"] = "55"
	dict["l"] = "555"
	dict["m"] = "6"
	dict["n"] = "66"
	dict["o"] = "666"
	dict["p"] = "7"
	dict["q"] = "77"
	dict["r"] = "777"
	dict["s"] = "7777"
	dict["t"] = "8"
	dict["u"] = "88"
	dict["v"] = "888"
	dict["w"] = "9"
	dict["x"] = "99"
	dict["y"] = "999"
	dict["z"] = "9999"
	dict[" "] = "0"

	str := readFile("practice.in")
	l,err := strconv.Atoi(str[0])
	if(err != nil){
		panic("MEH")
	}
	//call the function
	for i:=2; i <= l; i++ {
		//call the function T9
		T9(i-1, dict, string(str[i]))
	}	

}