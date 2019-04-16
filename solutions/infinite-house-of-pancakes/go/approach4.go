package main

import (
    "fmt"
    "bufio"
    "os"
	  "bytes"
    "strings"
    "strconv"
    "log"
    "io"
    "math"
)

  var tests int
	var smax, t bytes.Buffer
  var text = ""
  var testNum = ""

  var diners int
  var pancakes string
  var minutes = 0  //minutes gone by
  var outNum = 0
  var runeAsString bytes.Buffer

func main(){
      lines, err := readLines("large.in")
      if err != nil {
      log.Fatalf("readLines: %s", err)
      }

      for i, line := range lines {
        if(i == 0){
          testNum = line
          tests = getInt(testNum)
          break
        }
      }



      out := make([]int, 1000)
      for i, line := range lines {
          if(i == 0){
            //do nothing
          } else if(i <= tests*2 && i % 2 == 0){
            pancakes = line
          } else if(i <= tests*2){
            diners = getInt(line)
          }

            if(i % 2 == 0 && i > 0){
              inPancakes := make([]int, diners)
              pNum := 0 //variable used to fill out pancakes array

              runes := []rune(pancakes)
              for x := 0; x < len(pancakes) ; x++ {
                if(x == len(pancakes)-1){
                  runeAsString.WriteString(string(runes[x]))
                  inPancakes[pNum] = getInt(runeAsString.String())
                  pNum++
                  runeAsString.Reset()
                } else if( pancakes[x] != ' '){
                  runeAsString.WriteString(string(runes[x]))
                } else{
                  inPancakes[pNum] = getInt(runeAsString.String())
                  pNum++
                  runeAsString.Reset()
                  }
              }
              out[outNum] = waiter(diners, inPancakes)
              outNum++
          }

        }

        //creates output file

        fo, _ := os.Create("results.txt")

        var s, nAsText, outAsText string

        for n := 0; n < tests; n++{
          nAsText = strconv.Itoa(n+1)
          outAsText = strconv.Itoa(out[n])
          s = "Case #" + nAsText + ": " + outAsText + "\r\n"
      	  _, _ = io.Copy(fo, strings.NewReader(s))
        }

}


func waiter(diners int, pancakes []int)(minutes int){ //array
      minsArray := make([]int, 1001)

      max := pancakes[0] //the maximum number of pancakes
      for _, e := range pancakes {
          if e > max {
              max = e
          }
      }

      minsArray[0] = max // the minutes its gonna take if no pancakes
      // are moved

      for i := 1 ; i <= 1000 ; i++ { //from 1 minute to 1000 (max)
        split := 0.0 //pancakes on one plate being split

        var v float64
        for _, pancake := range pancakes{
            if(pancake > i){ //if the pancakes on the plate are more than the time{
              v = (float64(pancake) / float64(i))
              split += math.Ceil(v) -1
            }
        }

          minsArray[i] = int(split) + i
        }

        min := minsArray[0] //get the smallest minutes
        for _, e := range minsArray {
            if e < min {
                min = e
            }
        }

        return  min
}

func getInt(x string) (y int){
  y, err := strconv.Atoi(x)
     if err != nil {
             fmt.Println(err)
             return
           }
        return
}

func readLines(path string) ([]string, error) {
  file, err := os.Open(path)
  if err != nil {
    return nil, err
  }
  defer file.Close()

  var lines []string
  scanner := bufio.NewScanner(file)
  for scanner.Scan() {
    lines = append(lines, scanner.Text())
  }
  return lines, scanner.Err()
}