package main

import (
	"os"
	"fmt"
	"bufio"
	"strconv"
	"strings"
	"sort"
	"io"
)

func main() {
	in, err := os.Open("input.txt")
	check(err)
	defer in.Close()

	fo, err := os.Create("output.txt")
	check(err)
	defer fo.Close()

	var t int
	fmt.Fscanf(in, "%d\n", &t)
	if t >= 1 && t <= 100 {
		scanner := bufio.NewScanner(in)
		for i := 1; i <= t; i++ {
			scanner.Scan()
			d := scanner.Text()
			scanner.Scan()
			pan := scanner.Text()
			sol := solve(d, pan)
			pal := "Case #"
			pal += strconv.Itoa(i)
			pal += ": "
			pal += strconv.Itoa(sol)
			pal += "\n"
			fmt.Println(pal)

			_, err = io.Copy(fo, strings.NewReader(pal))
			check(err)
		}//End of for cycle for
	}//End of If
}//End main



func check(e error) {
	if e != nil{
		panic(e)
	}
}

/*
*Starts working on the problem
*converting the values to int
*then calls the function to get the result
 */

func solve(d, pan string) (mins int) {
	dinners, pancakes := AizuArray(pan, d)//Calls the func to get converted first
	mins = resu(dinners, pancakes)//Runs the func with the converted values
	return
}


/*This function manages to convert 2 entries (strings) to int types
*int defalut, not int32 nor int64. int
*/

func AizuArray(pan string, d string) (int, []int) {
	a := strings.Split(pan, " ")//Splits the array each space
	n, _ := strconv.Atoi(d)//Converts the number of dinners to int
	b := make([]int, n) //This creates b, an 'slice' of size n(dinners), its a kind of 'ArrayList' in Java
	for i, v := range a {
		b[i], _ = strconv.Atoi(v)//In this 'for' the position in the slice gets converted to int
	}
	return n, b//Returns the int converted values of the dinners and the slice of the pancakes served
}

/*
*Receives the information entered and converted to int
*@return value of the minimun time it takes for the pancakes to be eatens (minutes)
 */
func resu(dinners int, pancakes []int) (minutes int) {
	var min, max, aux, aux2, minTot int
	sort.Ints(pancakes)//Sorts the array as min-max
	max = pancakes[dinners-1]//Get the last value in the array (the max)
	times := make([]int, 1)//This is gonna save all the times registered
	times[0] = max
	for i := 0; i < len(pancakes); i++ {
		aux, aux2 = 0, 0
		if minTot <= max{
			for j := len(pancakes)-1; j > 0; j-- {
				if pancakes[j] != 1 && pancakes[j] == max{
					aux = pancakes[j]/2//Split of the value
					aux2 = (pancakes[j]/2)+(pancakes[j]%2)//Split the value and add in case it has a division waste
					pancakes[j] = aux
					pancakes = append(pancakes, aux2)
					min++
					sort.Ints(pancakes)
				}//End of If
			}//End of For
		}
		sort.Ints(pancakes)
		times = append(times, min+max)//Adds maximun time to the slice
	}
	sort.Ints(times)
	minutes = times[0]
	return
}