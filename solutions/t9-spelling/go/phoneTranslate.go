package main

import (
	"fmt"
	"bufio"
	"os"
	"strings"
	"strconv"
)

func check(e error) {
    if e != nil {
        panic(e)
    }
}

func main() {
	scanner := bufio.NewScanner(os.Stdin)
	var path string
    fmt.Println("Please insert the file name")
	for scanner.Scan() {
		path = scanner.Text();
		break;
	}

	file, err := os.Open(path)
    check(err)
  	defer file.Close()
	
	var content []string
    scanner = bufio.NewScanner(file)
	for scanner.Scan() {
	  	content = append(content, scanner.Text())
	}
    phoneTranslate(content)
}

func phoneTranslate(content []string){
	mapping := []string{" ", "" , "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"}
	N,_:=strconv.ParseInt(content[0],0,64)
	var lastChar int
	for i:= int64(0); i<N; i++{
		word:= content[i+1]
		chars:= strings.Split(word,"")

		fmt.Print("Case #", i+1, ": ")
		for j:= 0; j<len(chars); j++{
			for k:= 0; k<len(mapping); k++{
				if(strings.Contains(mapping[k],chars[j])){
					if lastChar==k{
						fmt.Print(" ")	
					}
					for l:= 0; l<=strings.Index(mapping[k],chars[j]); l++{
						fmt.Print(k)
					}
					lastChar=k
				}
			}
		}	
		fmt.Print("\n")
	}
}