package main

import (
	"fmt"
	"bufio"
    "os"
	"strings"
	"bytes"
    "strconv"
    "log"
	"io"
)

var tests, checkAt int
var testNum = ""
var runeAsString bytes.Buffer
var X bytes.Buffer
var R bytes.Buffer
var C bytes.Buffer

func main(){
	OminoTest()
}

func OminoTest(){
	lines, err := readLines("D-large-practice.in")
	if err != nil {
		log.Fatalf("readLines: %s", err)
	}

	for i, line := range lines {
		if(i == 0){
			testNum = line
			tests = getInt(testNum)
			break
		}
	}

	values := make([]int, 3)
	var n int
	m := 0
	out := make([]string, tests)

	for i, line := range lines{
		if(i != 0){
			runes := []rune(line)
			n = 0
			for x := 0 ; x < len(line) ; x++{
				if(x == len(line) - 1){
					runeAsString.WriteString(string(runes[x]))
					values[n] = getInt(runeAsString.String())
					runeAsString.Reset()
				}else if(line[x] != ' '){
					runeAsString.WriteString(string(runes[x]))
				} else {
					values[n] = getInt(runeAsString.String())
					n++
					runeAsString.Reset()
				}
			}
			out[m] = OminoGame(values[0], values[1], values[2], m + 1) 
			m++
		}
	}


	fo, _ := os.Create("large-output.txt")

	var s, nAsText, outAsText string

	for n := 0; n < tests; n++{
		nAsText = strconv.Itoa(n+1)
		outAsText = out[n]
		s = "Case #" + nAsText + ": " + outAsText + "\r\n"
		_, _ = io.Copy(fo, strings.NewReader(s))
	}

	
}

func getInt(x string) (y int){
	y, err := strconv.Atoi(x)
		if err != nil {
			fmt.Println(err)
			return
		}
	return
}
  
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

func getXRC (text string){
	for i := 0; i < len((text)) ; i++ {
 
		if( text[i] != ' '){
			runes := []rune(text)
			X.WriteString(string(runes[i]))
			if( i > 0 && text[i - 1] == ' '){
				runes := []rune(text)
				R.WriteString(string(runes[i]))
				if( i > 0 && text[i - 1] == ' '){
					runes := []rune(text)
					C.WriteString(string(runes[i]))
				}
			}
		}
	 }
	 return
 }

func OminoGame(X int, R int, C int, numberOfCases int) (winner string){
	firstIndicator := 7
	secondIndicator := ((R * C) % X)
	thirdIndicator := X
	var fourthIndicator int

	if X%2 == 0{
		fourthIndicator = X / 2
	}else{
		fourthIndicator = (X / 2) + 1
	}

	fifthIndicator := thirdIndicator * fourthIndicator;

	winner = "RICHARD"

	if X < firstIndicator {
		if secondIndicator == 0 {
			if R >= thirdIndicator || C >= thirdIndicator {
				if R >= thirdIndicator {
					if C >= fourthIndicator {
						if( X > 3 ) {
							if(((R * C) - fifthIndicator) != 0 && ((R * C) - fifthIndicator) % X == 0 && C > (X / 2) ) {
								winner = "GABRIEL"
							}
						} else {
							winner = "GABRIEL"
						}
					}
				}
				if C >= thirdIndicator {
					if R >= fourthIndicator {
						if( X > 3 ) {
							if(((R * C) - fifthIndicator) != 0 && ((R * C) - fifthIndicator) % X == 0 && R > (X / 2) ) {
								winner = "GABRIEL"
							}
						} else {
							winner = "GABRIEL"
						}
					}
				}
			}
		}
	}
	//fmt.Println("Case #", numberOfCases, ": ", winner)
	return
}
