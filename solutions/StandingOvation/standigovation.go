package main

import (
	"fmt"
	"os"
	"strconv"
)

func main() {
	filein, _ := os.Open("/Users/Noe/Library/Preferences/RubyMine2017.1/scratches/firstRun.txt")
	var arrayAudience string
	var testCase, maxShyness, t, min int
	fmt.Fscanln(filein, &testCase)
	for i := 0; i < testCase; i++ {
		t = 0
		min = 0
		fmt.Fscanln(filein, &maxShyness, &arrayAudience)
		for j := 0; j <= maxShyness; j++ {

			if min <  j-t {
				min = j - t
			}
			var s, err = strconv.Atoi(string(arrayAudience[j]))
			if err != nil {
				panic(err)
			}
			t+=s

		}
		fmt.Println( "Case #"+strconv.Itoa(i+1)+": "+strconv.Itoa(min))
	}
}