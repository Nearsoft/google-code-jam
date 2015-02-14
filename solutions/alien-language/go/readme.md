package main 

import (
	"bufio"
	"fmt"
	"os"
	"log"
	"strconv"
	//"strings"
	"regexp"	
	
)

func main (){
	var flag bool =false
	var current, control string
	var l , d , n int64

//Open File Stream 
	file, err := os.Open("A-small-practice.in")
	if err != nil {
	    log.Fatal(err)
	}
	defer file.Close()
	scanner := bufio.NewScanner(file)
	
//Control Keys
	scanner.Scan()
	control = scanner.Text()
	for i:=0;i<len(control); i++{ 
			if control[i]== 32{
				if flag==false{
					l, _ = strconv.ParseInt(current,0,64)
					flag=true
					current=""
				}else{
					d, _ = strconv.ParseInt(current,0,64)
					current=""
					}
			}else{
			current += string(control[i])
			}
	}
	n, _= strconv.ParseInt(current,0,64)
	//Save known patterns
	var patterns = make([]string,d)
	for j:=int64(0); j<d ; j++{		
		scanner.Scan()
		patterns [j]=scanner.Text()
		

	}
//Compare Known patterns
	var counter, instance in
t	for scanner.Scan(){
		counter=0
		var expression = regularexpr(scanner.Text())
		for k:=0; k<len(patterns);k++{
				var reg = regexp.MustCompile(expression)
				
				if (reg.MatchString(patterns[k])){
                	counter++	
            	}
			}
		instance++	
		fmt.Printf("Case #%v:  %v \n",instance, counter)
		}

		
	}




func regularexpr(exprline string) string{
	var regexpr string =""
	var exprflag bool = false 
	for x:=0;x<len(exprline);x++{	
		if exprline[x]==40 {
			exprflag=true
			regexpr += string(exprline[x])
			x++
			regexpr += string(exprline[x])
									
		}


		if exprline[x]==41{
			exprflag=false
					
		}
		if exprflag==true {
		regexpr += "|"

		}

		
		regexpr += string(exprline[x])
	}
	return regexpr 
}
