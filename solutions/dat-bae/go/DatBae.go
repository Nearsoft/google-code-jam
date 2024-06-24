package main

import (
  "fmt"
  "strings"
  "math"
  "strconv"
)

func getPC(arreglo []string, n int) string {
  id := ""
  for i := len(arreglo)-1; i >= 0; i-- {
      id += string(arreglo[i][n])
  }
  return id
}

func main() {
  var n int // worker computers
  var b int // workers broken
  var f int // calls to TEST_STORE
  var t int // test cases
  var ids, responses []string
  var veredict int
  finish := false
  
  fmt.Scanf("%d", &t)
  for i := 0; i < t && !finish; i++ {
    fmt.Scanf("%d %d %d", &n, &b, &f)
    
    ids = make([]string, f) 
    responses = make([]string, f)
    for oof := 0; oof < f; oof++ {
      ids[oof] = ""
      responses[oof] = ""
    }                
    
    guess := ""     // This will send to guess, ex. 010101
    answer := ""    // When we know the answer, ex. 0 2
    response := ""  // The response of the judge, ex. 1101
    
    for j := 0; j < f && !finish; j++ {
      guess = ""
      var digit float64 = 0
      limit := math.Pow(2,float64(j))
      d := "0"
      for k := 0; k < n; k++ {
        if digit >= limit {
          if d == "0" {
            d = "1"
          }else{
            d = "0"
          }
          digit = 0
        }
        guess += d // 00110011
        digit++
      }
      ids[j] = guess
      fmt.Println(guess)
      fmt.Scanf("%s", &response) // Response of the judge, ex. 1101
      if strings.EqualFold(response,"-1") {
        finish = true
        break
      }
      responses[j] = response
    }
    if finish {break}
    // Vamos a comparar
    o := 0
    contador := 0
    for r := 0; r < n-b; r++ { 
      if !strings.EqualFold(getPC(ids,r+o), getPC(responses, r)) {
        o++
        r--
        num := r+o
        cad := strconv.Itoa(num)
        answer += cad+" "
      }
      contador = r+1
    }
    for o < b {
      answer += strconv.Itoa(contador+o)+" "
      o++
    }
    fmt.Println(answer)
    fmt.Scanf("%d", &veredict)
    if veredict == -1 {
      finish = true
      break
    }

  }
}