package main

import "fmt"

func main() {
	keyPad := map[string]string{
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
	

	var word string = "foo  bar"
	var finalMessage string = ""
	var last string = "1"

	for i := 0; i <= len(word)-1; i++ {
		char := word[i : i+1]
		num := keyPad[char]
		if(last == num[0:1]){
		finalMessage = finalMessage+" "
		}
		finalMessage = finalMessage + num
		last = num[0:1]
	}
	fmt.Println(finalMessage)
}