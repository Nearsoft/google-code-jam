package main

import (
   "fmt"
    "log"
    "strconv"
    "bufio"
    "os")

func main() {

    alphabet := []string {" ", "", "abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"}

    file, err := os.Open("A-large-practice.in")
    if err != nil {
        log.Fatal(err)
    }   
    defer file.Close()
    scanner := bufio.NewScanner(file)
    scanner.Scan();

    n, _ := strconv.Atoi(scanner.Text())

    for i := 0; i < n; i++{ 
    
    var anterior = -1
    scanner.Scan()
    palabra := scanner.Text()

    fmt.Print("Case #"+strconv.Itoa(i+1)+": ")

        for j := 0; j < len([]rune(palabra)); j++ {
            for k := 0; k < len(alphabet); k++ {
                
                for l := 0; l < len([]rune(alphabet[k])); l++ {
                    
                    if palabra[j] == alphabet[k][l] {
                        
                        if anterior == k {
                            fmt.Print(" ")
                        }
                        
                        for m := 0; m <= l; m++ {
                        fmt.Print(k)
                        }

                        anterior = k
                        
                    } 
                }
            }
        }
    fmt.Println("")
}
}