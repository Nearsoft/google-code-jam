package main

import (
    "fmt"
    "bufio"
    "os"
	  "bytes"
_    "strings"
    "strconv"
    "log"
  _  "io"
)

  var tests, checkAt int
	var smax, t bytes.Buffer
  var text = ""
  var testNum = ""

  var diners int
  var pancakes string
  var minutes=0  //minutes gone by

  var runeAsString bytes.Buffer

func main(){
      lines, err := readLines("small.in")
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

    //  inDiners   := make([]int, tests)

    //  out        := make([]int, te
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
            //  out[n] = waiter(diners, inPancakes[n])
          }

        }



        //creates output file
        /*
        fo, _ := os.Create("results.txt")
        var s, nAsText, outAsText string
        for n := 0; n < tests; n++{
          nAsText = strconv.Itoa(n+1)
          outAsText = strconv.Itoa(out[n])
          s = "Case #" + nAsText + ": " + outAsText + "\r\n"
      	  _, _ = io.Copy(fo, strings.NewReader(s))
        }*/
}

func waiter(diners int, pancakes string){



}





func getSMax(text string)(checkAt int){
   for i := 0; i < len((text)) ; i++ {

        if( text[i] != ' '){
          runes := []rune(text)
          smax.WriteString(string(runes[i]))
          checkAt = i+1
        } else{
          checkAt = i+1
          return
        }
    }
    return
  //  fmt.Println(smax.String())
}
/*
func peopleStanding(inputText string, inputCheckAt int)(friendsInvited int){
    friendsInvited = 0
    standing = 0
    sLevel = 0
    runes := []rune(inputText)
    caseNumber++
    for i := inputCheckAt; i < len((inputText)) ; i++ {
        if(i == inputCheckAt || getInt(string(runes[i])) == 0){
        standing += getInt(string(runes[i]))
        sLevel++
        } else if(standing >= sLevel){
        standing += getInt(string(runes[i]))
        sLevel++
        } else{
        friendsInvited += sLevel - standing
        standing += (sLevel + getInt(string(runes[i])) - standing)
        sLevel++
        }
    }
    return
}
*/
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