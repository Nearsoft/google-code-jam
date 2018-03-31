package main

import (
	"fmt"
)

func main(){
	OminoTest()
}

func OminoTest(){
	fmt.Println("Tests:")
	var number int
	_, err := fmt.Scanf("%d", &number)
	if err != nil {
		fmt.Println(err)
	}

	for i := 0; i < number; i++{
		test(i+1)
	}
}

func test(numberOfCases int){

	fmt.Println("Enter X value:")
	var X int
	_, err := fmt.Scanf("%d", &X)
	if err != nil {
		fmt.Println(err)
	}

	

	fmt.Println("Enter number of rows:")
	var R int
	_, err2 := fmt.Scanf("%d", &R)
	if err2 != nil {
		fmt.Println(err)
	}

	fmt.Println("Enter number of columns:")
	var C int
	_, err3 := fmt.Scanf("%d", &C)
	if err3 != nil {
		fmt.Println(err)
	}

	OminoGame(X, R, C, numberOfCases)
}

func OminoGame(X int, R int, C int, numberOfCases int){
	firstIndicator := 7
	secondIndicator := ((R * C) % X)
	thirdIndicator := X
	var fourthIndicator int

	if X%2 == 0{
		fourthIndicator = X / 2
	}else{
		fourthIndicator = (X / 2) + 1
	}

	winner := "RICHARD"

	if X < firstIndicator {
		if secondIndicator == 0 {
			if R >= thirdIndicator || C >= thirdIndicator {
				if R >= thirdIndicator {
					if C >= fourthIndicator {
						winner = "GABRIEL"
					}
				}
				if C >= thirdIndicator {
					if R >= fourthIndicator {
						winner = "GABRIEL"
					}
				}
			}
		}
	}
	fmt.Println("Case #", numberOfCases, ": ", winner)
}