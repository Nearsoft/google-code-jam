package main

import "fmt"
import "strconv"
import "strings"
import "bufio"
import "log"
import "os"
import "regexp"

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

func main() {

    input, err := readLines("A-large-practice.in")
    if err != nil {
      log.Fatalf("readLines: %s", err)
      return
    }

    var L = getIntInput(input[0], 0)
    var D = getIntInput(input[0], 1)
    var N = getIntInput(input[0], 2)

    var words = createArray(input, 1, D+1)
    var tokens = createArray(input, D+1, D+1+N)

    for i := 0; i < len(tokens); i++ {
        fmt.Println("Case #" + strconv.Itoa(i+1) + ": " +  strconv.Itoa(match(tokens[i], words)))
    }
}

func match(token string, words []string) int {
    var counter = 0
    token = strings.Replace(token, "(", "[", -1)
    token = strings.Replace(token, ")", "]", -1)
        for j := 0; j < len(words); j++ {
	    b,_ := regexp.MatchString(token, words[j])
	    if(b){
                counter++
            }
        }
    return counter
}

func createArray(input []string, min, max int) []string {
    var array []string
    for i := min; i < max; i++ {
        array = append(array, input[i])
    }
    return array
}

func getIntInput(str string, idx int) int {
    i, _ := strconv.Atoi(strings.Split(str, " ")[idx])
    return i
}
