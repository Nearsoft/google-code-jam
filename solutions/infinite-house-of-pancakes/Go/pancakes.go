package main

import ("fmt"
"os")

func main() {
  f, err:=os.Open("input.in")
  if err != nil{
    fmt.Println(err)
    return
  }

  cases:=readCases(f)

  for i:=1; i<= cases;i++{

    pancakes:=readPancakes(f, cases)
    max := getMax(pancakes)
    counter := make([]int, max+1)

    for j:=0; j < len(pancakes); j++{
      counter[pancakes[j]]++
    }

    moves:=max;
    for k:=1; k <= max;k++{
      splits:=0
      for l:=1;l <= max;l++{
        splits += ((l - 1) / k) * counter[l]
      }
      if (splits+k) <= moves{
        moves = splits + k
      }
    }
    fmt.Printf("Case #%v: %v\n", i,moves)
  }
}

func readCases(f *os.File) int{
  var cases int
  _, err := fmt.Fscanln(f, &cases)
  if err != nil{
    fmt.Println(err)
  }
  return cases
}

func readPancakes(f *os.File, cases int) []int{
  var b int
  _, err := fmt.Fscanln(f, &b)
  if err != nil{
    fmt.Println(err)
  }
  pancakes := make([]int, b)
  for a := 0; a < b; a++{
    var c int
    _, err = fmt.Fscan(f, &c)
    pancakes[a] = c
  }
  return pancakes
}

func getMax(pancakes []int) int {
  max := 0
  for a := 0; a < len(pancakes); a++ {
    if pancakes[a] > max{
      max=pancakes[a]
    }
  }
  return max
}
