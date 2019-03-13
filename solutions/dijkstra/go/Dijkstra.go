package main
import (
    "bufio"
 	"fmt"
    "os"
   	"strconv"
    "strings"
)

func main() {
	miScanner := bufio.NewReader(os.Stdin)
	tests, _ := miScanner.ReadString('\n')
	tests = tests[:len(tests) - 1]
    T, _ := strconv.Atoi(tests)
    
	for numberOfCase := 1; numberOfCase <= T; numberOfCase++ {

        input1, _ := miScanner.ReadString('\n')
        splitted := strings.Split(input1, " ")
        var l, _ = strconv.Atoi(splitted[0])
        var x, _ = strconv.Atoi(splitted[1][:len(splitted[1]) - 1])
        _ = l


        input2, _ := miScanner.ReadString('\n')
        input2 = input2[:len(input2) - 1]
        
        word := ""
        for i := 0; i < x; i++ {
            word += input2
        }


        if !(reduceWordTo1(word) == "-1") {
            fmt.Println("Case #" + strconv.Itoa(numberOfCase) + ": NO")
        } else if seekijk(word) {
            fmt.Println("Case #" + strconv.Itoa(numberOfCase) + ": YES")
        } else {
            fmt.Println("Case #" + strconv.Itoa(numberOfCase) + ": NO")
        }
    }

}

func reduceWordTo1(word string) string {

    // Split the input string char by char
    // for the for loop to multiply them
    // in succession.

    splitWord := strings.Split(word, "")
    reduced := splitWord[0]
        
    for i := 1; i < len(word); i++ {
        reduced = quaternionMult(reduced, splitWord[i])  
    }
    return reduced
}

func seekijk(word string) bool {

    tmpi := "1"
    tmpj := "1"
    tmpk := "1"
    
    splitted := strings.Split(word, "")

    i := 0
    j := 0
    k := 0
    
    lw := len(word)
    
    for i < lw {

        tmpi = quaternionMult(tmpi, splitted[i])

        if tmpi == "i" {

            j = i + 1;

            for j < lw {

                tmpj = quaternionMult(tmpj, splitted[j]);

                if tmpj == "j" {

                    k = j + 1;

                    for k < lw {

                        tmpk = quaternionMult(tmpk, splitted[k]);

                        if tmpk == "k" {
                            return true
                        }
                        k += 1
                    }
                }
                j += 1
            }
        }
        i += 1
    }

    return false;
}

func quaternionMult(a, b string) string {

    indices := map[string]int{"1": 0,
                              "i": 1,
                              "j": 2,
                              "k": 3,
                              "-1": 4,
                              "-i": 5,
                              "-j": 6,
                              "-k": 7}

    indx := indices[a]
    indy := indices[b]
    
    mult := [8][8]string{{"1", "i", "j", "k", "-1", "-i", "-j", "-k"},
                         {"i", "-1", "k", "-j", "-i", "1", "-k", "j"},
                         {"j", "-k", "-1", "i", "-j", "k", "1", "-i"},
                         {"k", "j", "-i", "-1", "-k", "-j", "i", "1"},
                         {"-1", "-i", "-j", "-k", "1", "i", "j", "k"},
                         {"-i", "1", "-k", "j", "i", "-1", "k", "-j"},
                         {"-j", "k", "1", "-i", "j", "-k", "-1", "i"},
                         {"-k", "-j", "i", "1", "k", "j", "-i", "-1"},
                        }
    return mult[indx][indy]
}