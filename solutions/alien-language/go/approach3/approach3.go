package main

import (
    "fmt"
    "regexp"
    "strings"
    "strconv"
    "log"
 	"bufio"
 	"os"
)

func main(){

	file, err := os.Open("A-large-practice.in")
    if err != nil {
        log.Fatal(err)
    }   
    defer file.Close()
    scanner := bufio.NewScanner(file)
    scanner.Scan();
    
    ldn := strings.Split(scanner.Text(), " ")
    fmt.Println(ldn)

    D, _ := strconv.Atoi(ldn[1])
    N, _ := strconv.Atoi(ldn[2])

    var palabras []string
	var patterns []string

    palabras = make([]string, D) 
	patterns = make([]string, N)

    for i := 0; i < D; i++ {	
		scanner.Scan()
		palabras[i] = scanner.Text()
	}

	for i := 0; i < N; i++ {	
		scanner.Scan()
		patterns[i] = scanner.Text()
	}

    for i := 0; i < len(patterns); i++ {
		
		cont := 0
    	pattern := strings.Replace((strings.Replace(patterns[i], "(", "[", -1)), ")", "]", -1)
	    
	    for j := 0; j < len(palabras); j++ {	
		
			match, _ := regexp.MatchString(pattern, palabras[j])
    	
    		if match {
    	
    			cont++
   		
   		 	}
		
		}

		fmt.Println("Case #"+strconv.Itoa(i+1)+": "+strconv.Itoa(cont))
	}
}


 

    

