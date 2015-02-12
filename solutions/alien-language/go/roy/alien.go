package main

import (
	"fmt"
	"os"
	"strings"
	"regexp"
)

func main() {
	
	var L, D, N int 
	fmt.Fscanf(os.Stdin,"%d %d %d", &L, &D, &N)
	dict := make([]string, D)
	for i:=0;i<D;i++{
		fmt.Scanf("%s", &dict[i])
	}
	cases := make([]string, N)
	for i:=0;i<N;i++{
		fmt.Scanf("%s", &cases[i])
		cases[i]=strings.Replace(cases[i], "(","[",-1)
		cases[i]=strings.Replace(cases[i], ")","]",-1)
	}
	fmt.Println()
	for i:=0;i<N;i++{
		flag:=0
		for j:=0;j<D;j++{
			matched, err:=regexp.MatchString(cases[i],dict[j])
			if matched == true && err == nil{
				flag++
			}
		}
		fmt.Println("Case #",i+1,": ",flag)
	}
}
