package main

import "fmt"
import "strings"
import "regexp"

func main() {
   dictionary :=[]string{"abc","bca","dac","dbc","cba"}
   cases :=[]string{"(ab)(bc)(ca)","abc","(abc)(abc)(abc)","(zyx)bc"}
   
   //pattern := "(ab)(bc)(ca)"
   counter :=0
   
   for i := 0; i <=len(cases)-1; i++ {
       pattern := strings.Replace(strings.Replace(cases[i], ")", "]", -1), "(", "[", -1) 
       
       
       
       for j := 0; j<=len(dictionary)-1; j++ {
           match, _ := regexp.MatchString(pattern, dictionary[j])
          if  match {
           counter++  
          }//if
         
       }//for2
        fmt.Println(counter)
          counter = 0 
   }//for1
   
  
}
