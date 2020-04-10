package main


import ("fmt"

    "sort"

    "strconv")


func main() {

    var t = []string{"1", "2", "3","4","5"}

    var t2 = []int{}

    var t3 =[]int{1,0,1,0,1}



    for _, i := range t {

        j, err := strconv.Atoi(i)

        if err != nil {

            panic(err)

        }

        t2 = append(t2, j)

    }







    sort.Sort(sort.Reverse(sort.IntSlice(t2)))

    sort.Sort(sort.IntSlice(t3))

    fmt.Println(t3)

    fmt.Println(t2)

    var min int=0;

    for i := 0; i < len(t); i++ {

        min+=t2[i]*t3[i]

    }

    fmt.Println(min)


}
