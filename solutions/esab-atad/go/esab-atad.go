package main

import( 
	// Adding a dot as the package name, means you don't have to 
	// type it everytime you use a package function
	"bufio"
	. "fmt" 
	. "os"
)

// Global Variable
var B int // This makes it easier to be able to use B in different scopes

var reader *bufio.Reader = bufio.NewReader(Stdin)
var writer *bufio.Writer = bufio.NewWriter(Stdout)

func main ()  {
	var T int // Number of test
	// Read both inputs then saves them respectively
	Scanf("%d%d", &T,&B) 
	for nr := 1; nr <= T; nr++ {
		easb_atad()
	}
}

func easb_atad(){
	// Create vector like slice
	var answer = make([]int,B+1)
	var L = 1
	var R = B
	for nr := 1; true; nr += 2 {
		if nr % 10 == 1 && nr != 1 {
			found := -1;
			found_diff := -1;
			for i := 1; i < L; i++ {
				if answer[i] == answer[B+1-i] {
					found = i;
				}else {
					found_diff = i;
				}
			}
			if(found == -1) {
				new_value := query(1);
				query(1);
				if(new_value != answer[1]) {
					for i := 1; i <= L; i++ {
						answer[i] ^= 1;
					}
					for i := R; i <= B; i++ {
						answer[i] ^= 1;
					}
				}
			}else {
				one := query(found);
				if(one != answer[found]) {
					for i := 1; i <= L; i++ {
						answer[i] ^= 1;
					}
					for i := R; i <= B; i++ {
						answer[i] ^= 1;
					}
				}
				if(found_diff == -1) {
					query(found);
				}else {
					if(query(found_diff) != answer[found_diff]) {
						for i, j := 1, len(answer)-1; i < j; i, j = i+1, j-1 {
							answer[i], answer[j] = answer[j], answer[i]
						}
					}
				}
			}
			nr += 2;
		}
		answer[L] = query(L);
		answer[R] = query(R);
		L++;
		R--;
		if(L > R) {
			for i := 1; i <= B; i++ {
				Printf("%d",answer[i])
			}
			Println()
			var response string
			Scanln(&response)
			if response != "Y"{
				return
			}
			return
		}
	}
}

func query(i int) (int){
	Println(i)
	defer writer.Flush()
	var r int
	Scanf("%d", &r)
	return r
}
