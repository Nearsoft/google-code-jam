// CREATED BY PEDRO DE LA REE 13/02/2015


package main

import (
	"io/ioutil"
	"fmt"
	"strings"
	"strconv"
)

func atoi(str string) int{
	l,err := strconv.Atoi(str)
	if(err != nil){
		panic("MEH")
	}
	return l
}


//reads a file from the system
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
//http://stackoverflow.com/questions/15323767/how-to-if-x-in-array-in-golang
func stringInArray(a string, list []string) bool {
    for _, b := range list {
        if b == a {
            return true
        }
    }
    return false
}

func coincidences(testWords []string, dictionary []string) int {
	c := 0
	for _, b := range testWords {
        if stringInArray(b, dictionary){
        	c++
        }
    }
    return c
}

//creates an array of all the posible words using the rules R
//recursive function: 
/*
n: next index
L: lenght of the words in the dictionary
actualStr: actual string being concatenated
testWords: array containing the current test words
*/	
func concatenate(R []string, n int, L int, actualStr string, testWords []string) []string {
	//add the actual string to the test words
	if len(actualStr) >= L {
		testWords = append(testWords, actualStr)
	} else{ //keep concatenating

		if string(R[n][0]) == "(" {//if it contains a "("
			for i:=1; i < len(R[n])-1; i++ {
				actualStr += string(R[n][i])
				testWords = concatenate(R, n+1, L, actualStr, testWords)
				actualStr = actualStr[:len(actualStr)-1]
			}
		} else {
			actualStr += string(R[n])
			testWords = concatenate(R, n+1, L, actualStr, testWords)

		}

	}
	return testWords
}

func createRulesArray(R string) []string{
	//create an array of words living by the rule R
		//create the array 

	rules:= [] string{}

		//fill the array
	//P := false
	currentStr := ""
	for j:=0; j < len(R); j++{
		//fmt.Println(string(R[j]))

		if string(R[j]) == "(" {
			if j != 0 && len(currentStr) > 0 {
				rules = append(rules, currentStr)
			}
			currentStr = string(R[j])

		} else if string(R[j]) == ")" {
			currentStr += string(R[j])

			if len(currentStr) > 0 {
				rules = append(rules, currentStr)
			}
			currentStr = "" 
		} else {
				currentStr += string(R[j])
		}
	}
	if string(R[len(R)-1]) != ")" {
		rules = append(rules, currentStr)
	}

	return rules
}

func main() {
	//read the file, and import vars L,D and N
	str := readFile("practice.in")
	auxstr := strings.Split(str[0]," ")
	L := atoi(auxstr[0])
	D := atoi(auxstr[1])
	N := atoi(auxstr[2])

	//create an array containing the D words 
	dictionary := [] string {}
	for i:= 0; i < D+1; i++{
		//dictionary[i] = str[i+1]
		dictionary = append(dictionary, str[i+1])
	}
	//test all the input
	for i:= D+2; i < D+N+1; i++{
		R := str[i]
		rules := createRulesArray(R)

		fmt.Println(len(rules))

		testWords :=[] string{}
		testWords = concatenate(rules, 0, L, "", testWords)

		//fmt.Println(testWords)

		c := coincidences(testWords, dictionary) //test all the coincidences 
		fmt.Printf("Case # %d: %d\n", i - D -1, c)
	}

}