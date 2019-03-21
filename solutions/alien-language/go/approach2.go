package main

import (
	"fmt"
	"bufio"
	"os"
	"strings"
	"strconv"
	"regexp"
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
    alien(content)
}

func alien(content []string) {
	count := 0;
	var sDictionary []string;
	var L, D, N int;
	var word string;
	var header = content[0];
	headValues := strings.Split(header," ");
	for i:=0; i<3; i++{
		switch i{
			case 0:
				L,_ = strconv.Atoi(headValues[i])
			case 1:
				D,_ = strconv.Atoi(headValues[i])
			case 2:
				N,_ = strconv.Atoi(headValues[i])
		}
	}
	for j:=0; j<D; j++{
		sDictionary = append(sDictionary,content[j+1]);
	}
	for k:=0; k<N; k++{
		word = strings.Replace(content[D+1+k], ")", "]", -1);
		word = strings.Replace(word, "(", "[", -1);;
		for j:=0; j<D; j++{
			match,_ := regexp.MatchString(word,sDictionary[j]);
			if(match){
				count++;
			}
		}
		fmt.Println("Case #",k+1, ": ", count);
		count = 0
	}
	L=L;
	N=N;
	count = count;
	sDictionary = sDictionary;
}