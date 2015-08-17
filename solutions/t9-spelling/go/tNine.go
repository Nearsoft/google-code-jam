package main

import (
    "fmt"
    "log"
    "bufio"
    "strings"
    "strconv"
    "os")

func main() {
    file, err := os.Open(os.Args[1])
    if err != nil {
        log.Fatal(err)
    }
    defer file.Close()

    scanner := bufio.NewScanner(file)
    var lines[]string
    for scanner.Scan() {
        lines = append(lines, scanner.Text())
    }

    numpad := []string {" ","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"}

    for i := 1;
    i < len(lines);
    i += 1 {

        var res string = ""
        var prev_char int = 0
        var word string = lines[i]

        for j := 0;
        j < len(word);
        j++{
            var char string = word[j: j + 1]
            for k := 0;
            k < len(numpad);
            k++{
                if (strings.Index(numpad[k], char) > -1) {
                    if (prev_char == k) {
                        res += " "
                    }
                    var num_index = strings.Index(numpad[k], char)
                    for l := 0;
                    l < num_index + 1;
                    l++{
                        res += strconv.Itoa(k)
                    }
                    prev_char = k;
                }
            }
        }
        fmt.Println("Case # "+ strconv.Itoa(i)+": "+ res)
    }
}
