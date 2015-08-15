package main

import (
	"fmt"
	"bufio"
	"os"
	"strings"
	"strconv"
	"sort"
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
    mpsCalc(content)
}

func mpsCalc(content []string) {
	T,_:=strconv.Atoi(content[0])
	
	var n int
	var sum int
	var sV1, sV2 string
	var v1, v2 []string
	for i:= 0; i<T; i++{
		sum = 0
		n,_=strconv.Atoi(content[(i*3)+1])
		sV1 = content[(i*3)+2]
		sV2 = content[(i*3)+3]
		v1= strings.Split(sV1," ")
		v2= strings.Split(sV2," ")
		var Xn, Yn []int
		for j:= 0; j<n; j++{
			tempx,_:=strconv.Atoi(v1[j])
			tempy,_:=strconv.Atoi(v2[j])
			Xn = append(Xn,tempx)
			Yn = append(Yn,tempy)
		}
		sort.Ints(Xn)
		sort.Ints(Yn)
		for k:= 0; k<n; k++{
			sum += Xn[k]*Yn[n-k-1]
		}
		fmt.Println("Case #",i+1, ": ", sum)
	}
}