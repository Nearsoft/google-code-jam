/*

How to run ?

  ./executableName < inputFile.* > outputFile.*

*/

package main

import (
  "fmt"
  "os"
  "bufio"
  "strconv"
  "strings"
)

func min(x, y int) int {
  if x < y {
    return x
  }
  return y
}

func max(x, y int) int {
  if x > y {
    return x
  }
  return y
}

func whoWins(x, r, c int) string {
  s := min(r,c)
  l := max(r,c)

  //Richards conditions for winning
  if (s*l)%x != 0 {
    return "RICHARD"
  }
  if x == 3 && s == 1 {
    return "RICHARD"
  }
  if x == 4 && s <= 2 {
    return "RICHARD"
  }
  if x == 5 && (s <= 2 || (s == 3 && l== 5)) {
    return "RICHARD"
  }
  if x == 6 && s <= 3 {
    return "RICHARD"
  }
  if x >= 7 {
    return "RICHARD"
  }

  return "GABRIEL"
}

func main()  {

  //reader used to read line by line
  reader := bufio.NewReader(os.Stdin)
  text,_,_ := reader.ReadLine()

  numberOfCases,_ := strconv.Atoi(string(text))

  for i := 0 ; i < numberOfCases; i++ {

    text,_,_ := reader.ReadLine()

    //split a line into the desired inputs (xOmino,rGrif,cGrid)
    line := strings.Split(string(text), " ")
    x,_ := strconv.Atoi(line[0])
    r,_ := strconv.Atoi(line[1])
    c,_ := strconv.Atoi(line[2])

    winner := whoWins(x,r,c)

    //print case solution with the specified format
    fmt.Println("Case #" + strconv.Itoa(i + 1) + ": " + winner)
  }

}
