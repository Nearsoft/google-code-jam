package main

import (
    "fmt"
    "strings"
    "strconv"
    "log"
 	"bufio"
 	"os"
    "sort"
)

func main(){

	file, err := os.Open("A-large-practice.in")
    if err != nil {
        log.Fatal(err)
    }   
    defer file.Close()
    scanner := bufio.NewScanner(file)
    scanner.Scan();
    
    T, _ := strconv.Atoi(scanner.Text())

    for i := 0; i < T; i++ {
      scanner.Scan()
      n, _ := strconv.Atoi(scanner.Text())
      scanner.Scan()
      vector1str := strings.Split(scanner.Text(), " ")
      scanner.Scan()
      vector2str := strings.Split(scanner.Text(), " ")
      var vector1 = []int{}
      var vector2 = []int{}
      vector1 = make([]int, n)
      vector2 = make([]int, n)

      mult := 0

      for j := 0; j < n; j++ {
        vector1[j], _ = strconv.Atoi(vector1str[j])
        vector2[j], _ = strconv.Atoi(vector2str[j])
      }
        sort.Sort(sort.IntSlice(vector1))
        sort.Sort(sort.Reverse(sort.IntSlice(vector2)))
        
        for k := 0; k < n; k++{
            mult += vector1[k] * vector2[k]
        }

        fmt.Println("Case #"+strconv.Itoa(i+1)+": "+strconv.Itoa(mult))

    }
}


 

    
