package main

import (
    "fmt"
    "bufio"
    "os"
    "io/ioutil"
    "strconv"
    "strings"
    "bytes"
)

// Reading files requires checking most calls for errors.
// This helper will streamline our error checks below.
func check(e error) {
    if e != nil {
        panic(e)
    }
}

func saveFile(data string){
    d1 := []byte(data)
    err := ioutil.WriteFile("resutl.txt", d1, 0644)
    check(err)
}

func readFile() ([]string){
    i:=0
    scanner := bufio.NewScanner(os.Stdin)
    var content []string
    if(scanner.Scan()){
        t := scanner.Text()
        x,err := strconv.Atoi(t) 
        check(err)
        content = make([]string,x+1)
        content[i] = t
        i = 1
        for scanner.Scan() {
            ucl := scanner.Text()
            content[i] = ucl
            i++
        }
    }
    return content
}


func evaluateInput(input []string)(string){

    var buffer bytes.Buffer
    fmt.Println(buffer.String())
    result := ""
    i:= 1
    t,err := strconv.Atoi(input[0]);  // t = total cases
    check(err)

    if(t>=1 && t<=100){ 
        for(i<=t){
            testCase := strings.Split(input[i], " ")
            buffer.WriteString("Case #")
            buffer.WriteString(strconv.Itoa(i))
            buffer.WriteString(": ")
            buffer.WriteString(evaluateCase(testCase))
            buffer.WriteString("\n")
            i++
        }
    }
    result = buffer.String()
    return result

}

/**
*
* @param testCase string line that has the values to evaluate
* @return name of winner
*/
func evaluateCase(testCase []string)(string){
    winner := "GABRIEL"
    var area int = 0

    xomino,err := strconv.Atoi(testCase[0])
    check(err)
    rows,err := strconv.Atoi(testCase[1])
    check(err)
    columns,err := strconv.Atoi(testCase[2])
    check(err)
    area = rows*columns
    if ( xomino > 6 || xomino > area || area%xomino >0 || perfectPieceExists(xomino,rows,columns) ){
        winner = "RICHARD"
    }
    return winner
}

/**
 * find if there is a piece that guarantees is impossible to fill the area
 * @param xomino omino number
 * @param rows rows in area
 * @param columns columns in area
 * @return
 */
func perfectPieceExists(xomino int,rows int,columns int)(bool){
    length := xomino
    height := 1

    //special cases for omino 4 that always block the area to be filled
    if(xomino==4) {
        if(rows == 2 || columns == 2){
            return true
        }
    }
    //special cases for omino 5 that always block the area to be filled
    if(xomino==5) {
        if(rows == 2 || columns == 2 || rows == 3 && columns ==5 || rows == 5 && columns ==3 ){
            return true
        }
    }
    //special cases for omino 6 that always block the area to be filled
    if(xomino==6) {
        if(rows == 2 || columns == 2 || rows == 3 || columns == 3){
            return true
        }
    }
    for length >= height {
        if(length<=rows && height<=columns || height<=rows && length<=columns) {
            length--
            height++
        }else{
            return true
        }
    }
    return false
}


func main() {
	listofContent := readFile()
    result := evaluateInput(listofContent)
    fmt.Println(result)
    fmt.Println("Done :) The result is in the file: result.txt")
    saveFile(result)
}
