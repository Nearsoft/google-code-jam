package main

import (
	"os"
	"fmt"
	"bufio"
	"strconv"
	"strings"
	//"io"
	"sort"
	//"io/ioutil"
	//"io/ioutil"
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
	if t >= 1 {
		if t <= 100 {
			scanner := bufio.NewScanner(in)
			for i := 1; i <= t; i++ {
				scanner.Scan()
				d := scanner.Text()
				scanner.Scan()
				pan := scanner.Text()
				//fmt.Println(d," vale ", pan)
				sol := solve(d, pan)
				fmt.Println("Case #", i , ": ", sol)
				pal := "Case #"
				pal += strconv.Itoa(i)
				pal += ": "
				pal += strconv.Itoa(sol)
				pal += "\n"
				fmt.Println(pal)

				_, err = io.Copy(fo, strings.NewReader(pal))
				check(err)
				}//End of for cycle

			}
		}
	}



func check(e error) {
	if e != nil{
		panic(e)
	}
}

func solve(d, pan string) (mins int) {
	/*This was the first version for the conversion of values
	*Then I found it is possible to return easily values here in go
	dinners, err := strconv.ParseInt(d, 10, 32)
	check(err)
	*/
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

func resu(dinners int, pancakes []int) (minutes int) {
	//fmt.Println("Probando que funciona ",dinners, " ", pancakes)
	var min, max, aux, aux2, minTot int
	sort.Ints(pancakes)
	//fmt.Println("Probando orden ",dinners, " ", pancakes)
	max = pancakes[dinners-1]
	times := make([]int, 1)
	times[0] = max
	for i := 0; i < len(pancakes); i++ {
		aux, aux2 = 0, 0
		if minTot <= max{
			for j := len(pancakes)-1; j > 0; j-- {
				if pancakes[j] != 1{
					if pancakes[j] == max{
						aux = pancakes[j]/2
						aux2 = (pancakes[j]/2)+(pancakes[j]%2)
						pancakes[j] = aux
						pancakes = append(pancakes, aux2)
						min++
						sort.Ints(pancakes)
					}
				}
			}

		}
		sort.Ints(pancakes)
		times = append(times, min+max)
	}
	sort.Ints(times)
	minutes = times[0]
	return
}