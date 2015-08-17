
package main

import ("fmt"
  "log"
  "bufio"
  "strings"
  "strconv"
  "sort"
  "os")

func main() {
   file, err := os.Open(os.Args[1])
  if err != nil {
    log.Fatal(err)
  }
  defer file.Close()

  scanner := bufio.NewScanner(file)
  var lines []string
  for scanner.Scan() {
    lines = append(lines, scanner.Text())
  }

  for i := 1; i < len(lines); i+=3 {
    first_array_s := strings.Split(lines[i + 1], " ")
    second_aray_s := strings.Split(lines[i + 2], " ")
    var first_array_i, second_array_i []int

    for i := 0; i < len(first_array_s); i++ {
      k, _ := strconv.Atoi(first_array_s[i])
      first_array_i = append(first_array_i, k)

      k, _ = strconv.Atoi(second_aray_s[i])
      second_array_i = append(second_array_i, k)
    }

    sort.Sort(sort.Reverse(sort.IntSlice(first_array_i)))
    sort.Sort(sort.IntSlice(second_array_i))

    res := 0
    for i := 0; i < len(first_array_i); i++ {
      res += first_array_i[i] * second_array_i[i];
    }
    fmt.Println("Case # "+ strconv.Itoa((i/3) + 1)+": "+ strconv.Itoa(res))
  }
}
