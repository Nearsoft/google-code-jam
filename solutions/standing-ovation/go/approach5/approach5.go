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
)

    var tests, checkAt int
  //  var input string
	var smax, t bytes.Buffer
  var text = ""
  var testNum = ""

    var friendsInvited = 0
    var standing = 0
    var caseNumber = 0
    var sLevel = 0



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

      inText := make([]string, tests)
      inSMax := make([]int, tests)
      out := make([]int, tests)

      for i, line := range lines {
        if(i == 0){
          testNum = line
        } else if(i <= tests){
          inText[i-1] = line
          inSMax[i-1] = getSMax(inText[i-1])
        } else{
          break
        }
        }


        for n := 0; n < tests; n++{
          out[n] = peopleStanding(inText[n], inSMax[n]) //obtains value of result
        }


        fo, _ := os.Create("results.txt")

        var s, nAsText, outAsText string

        for n := 0; n < tests; n++{
          nAsText = strconv.Itoa(n+1)
          outAsText = strconv.Itoa(out[n])
          s = "Case #" + nAsText + ": " + outAsText + "\r\n"
      	  _, _ = io.Copy(fo, strings.NewReader(s))
        }
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

func peopleStanding(inputText string, inputCheckAt int)(friendsInvited int){
    friendsInvited = 0
    standing = 0
    sLevel = 0
    runes := []rune(inputText)

    caseNumber++

    for i := inputCheckAt; i < (len((inputText))); i++ {
        if(i == inputCheckAt || getInt(string(runes[i])) == 0){
        standing += getInt(string(runes[i]))
        sLevel++
        } else if(standing >= sLevel){
        standing += getInt(string(runes[i]))
        sLevel++
        } else{
        friendsInvited += sLevel - standing
        standing += (friendsInvited + getInt(string(runes[i])))
        sLevel++
        }
    }

    return
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