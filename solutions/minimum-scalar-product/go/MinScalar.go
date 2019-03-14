package main

import "fmt"
import "strconv"
import "strings"
import "sort"
import "bufio"
import "log"
import "os"

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

    input, err := readLines("A-small-practice-minimum.in")
    if err != nil {
      log.Fatalf("readLines: %s", err)
      return
    }

    var numberOfVectors,_ =  strconv.Atoi(input[0])
    var output []string

    for i := 0; i < numberOfVectors; i++ {
        var elements,_ = strconv.Atoi(input[3 * i + 1])

        var vector1 = order(createArrayFromString( input[3 * i + 2] ))
        var vector2 = order(createArrayFromString( input[3 * i + 3] ))

        var result = 0

        for j := 0; j < elements; j++ {
            result +=  vector1[j] * vector2[elements - j - 1]
        }

	var line = "Case #" + strconv.Itoa(i+1) + ": " + strconv.Itoa(result)
	output = append(output, line)
        fmt.Println(line)
    }

    writeLines(output, "output.txt")

}

func order(vector []int) []int {
    sort.Ints(vector)
    return vector
}

func createArrayFromString(str string) []int {
	var arr = strings.Split(str, " ")
	var arrInt []int
	for i := 0; i < len(arr); i++ {
		i,_ := strconv.Atoi(arr[i])
		arrInt = append(arrInt, i)
	}
	return arrInt
}
