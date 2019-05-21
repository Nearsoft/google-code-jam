package main

import "fmt"
import "strconv"
import "strings"
import "bufio"
import "log"
import "os"

var keyboard [][]string

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

func writeLines(lines []string, path string) error {
  file, err := os.Create(path)
  if err != nil {
    return err
  }
  defer file.Close()

  w := bufio.NewWriter(file)
  for _, line := range lines {
    fmt.Fprintln(w, line)
  }
  return w.Flush()
}

func main() {

    keyboard = append(keyboard, []string{"a", "b", "c"})
    keyboard = append(keyboard, []string{"d", "e", "f"})
    keyboard = append(keyboard, []string{"g", "h", "i"})
    keyboard = append(keyboard, []string{"j", "k", "l"})
    keyboard = append(keyboard, []string{"m", "n", "o"})
    keyboard = append(keyboard, []string{"p", "q", "r", "s"})
    keyboard = append(keyboard, []string{"t", "u", "v"})
    keyboard = append(keyboard, []string{"w", "x", "y", "z"})
    keyboard = append(keyboard, []string{" "})

    input, err := readLines("C-large-practice.in")
    if err != nil {
      log.Fatalf("readLines: %s", err)
      return
    }

    var numberOfWords,_ =  strconv.Atoi(input[0])

    var lastKey = ""

    var str []string
    for i := 1; i <= numberOfWords; i++ {
        var word = input[i]
	var s = "Case #" + strconv.Itoa(i) + ": "
        for j := 0; j < len(word); j++ {
            var fromKeys = processLetter(charAt(word, j))
            if(charAt(fromKeys, 0) == lastKey){
		s += " "
            }
            lastKey = charAt(fromKeys, 0)
            s += fromKeys
        }
	str = append(str, s)
    }

    writeLines(str, "output.txt")

}

func processLetter(letter string) string{
    var key,times = key(letter)
    var str = ""
    for i := 0; i < times; i++ {
        str += strconv.Itoa(key)
    }
    return str
}

func key(letter string) (int, int){
    for i := 0; i < len(keyboard); i++ {
        for j := 0; j < len(keyboard[i]); j++ {
            if(keyboard[i][j] == letter){
                if(i == len(keyboard) - 1){
                    return 0,j+1
                }
                return i+2,j+1
            }
        }
    }
    return -1,-1
}

func charAt(str string, idx int) string {
     return strings.Split(str, "")[idx]
}
