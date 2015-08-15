package main

import "fmt"
import "sort"
	

func main() {
	 vector1 := []int{1, 2, 3, 4, 5}
	 vector2 := []int{1, 0, 1, 0, 1}
	
	sort.Sort(sort.IntSlice(vector1))
	sort.Sort(sort.Reverse(sort.IntSlice(vector2)))
	
	fmt.Println(vector1)
	fmt.Println(vector2)
	
	
	var total int = 0
	for i := 0; i <= len(vector1)-1; i++ {
         var suma int = vector1[i] * vector2[i]
	total += suma
    }

	fmt.Println(total)
	
	
}