package main

import (
    "fmt"
    "log"
    "bufio"
    "strings"
    "strconv"
    "regexp"
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

    params := strings.Split(lines[0], " ")
    dictionary_length, _ := strconv.Atoi(params[1])

    //Filling up the dictionary
    var dictionary[]string

    for i := 1;
    i < dictionary_length + 1;
    i += 1 {
        dictionary = append(dictionary, lines[i])
    }


    //Running the test cases
    for i := dictionary_length + 1;
    i < len(lines);
    i++{
        var test_case string = lines[i]
        var res int = 0
        test_case = strings.Replace(test_case, "(", "[", -1)
        test_case = strings.Replace(test_case, ")", "]", -1)


        regex, _ := regexp.Compile(test_case)

        for j:= 0;
        j < len(dictionary);
        j++{
            if regex.MatchString(dictionary[j]) == true {
                res += 1
            }



        }
        fmt.Println("Case #" + strconv.Itoa(i - len(dictionary)) + ": " + strconv.Itoa(res))
    }
}
