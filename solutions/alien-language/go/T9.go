package main 

import (
	"bufio"
	"fmt"
	"os"
	"log"
	"strconv"

	
)

func main (){
	var controlnum, caseline int64
//Open File Stream 
	file, err := os.Open("C-small-practice.in")
	if err != nil {
	    log.Fatal(err)
	}
	defer file.Close()
	scanner := bufio.NewScanner(file)
	
//Control Keys
	scanner.Scan()
	controlnum, _= strconv.ParseInt(scanner.Text(),0,64)

//Compare Known patterns
	for scanner.Scan(){
		caseline++
		fmt.Printf("Case #%v: ",caseline)
		convertsymbol(scanner.Text())
	}

		
}


func convertsymbol(input string) {
	var line=""
	var patterns = map[string]string{
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
	for i:=0;i<len(input); i++{
		if(i>0){
			   if(string(patterns[string(input[i])][0]) == string(patterns[string(input[i-1])][0])) {
                line +=" "
            }
		}
		line+=patterns[string(input[i])] 
	}
	fmt.Printf(" "+line+"\n")
}




