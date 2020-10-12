package main

import (
	"fmt"
	"sort"
)

func main() {
	var time int
	fmt.Scanf("%d", &time)

	for caseNum := 1; caseNum <= time; caseNum++ {
		var N int
		fmt.Scanf("%d", &N)

		var tasks [][2]int
		for i := 0; i < N; i++ {
			var time2 [2]int
			fmt.Scanf("%d %d", &time2[0], &time2[1])
			tasks = append(tasks, time2)
		}

		IDnumber := make([]int, N)
		for i := range IDnumber {
			IDnumber[i] = i
		}

		sort.Sort(taskSlice{
			ts:   tasks,
			IDnumber: IDnumber,
		})

		valid := true
		parent := make([]bool, N)

		cEndTime, jEndTime := -1, -1
		for _, j := range IDnumber {
			time2 := tasks[j]
			if cEndTime <= time2[0] {
				cEndTime = -1
			}
			if jEndTime <= time2[0] {
				jEndTime = -1
			}
			if cEndTime == -1 {
				cEndTime = time2[1]
				parent[j] = false
			} else if jEndTime == -1 {
				jEndTime = time2[1]
				parent[j] = true
			} else {
				valid = false
			}
		}

		if valid {
			var res []byte
			for _, b := range parent {
				if b {
					res = append(res, 'J')
				} else {
					res = append(res, 'C')
				}
			}
			fmt.Printf("Case #%d: %s\n", caseNum, string(res))
		} else {
			fmt.Printf("Case #%d: IMPOSSIBLE\n", caseNum)
		}
	}
}

type taskSlice struct {
	ts   [][2]int
	IDnumber []int
}

func (time2 taskSlice) Len() int {
	return len(time2.ts)
}

func (time2 taskSlice) Less(i, j int) bool {
	return time2.ts[time2.IDnumber[i]][0] < time2.ts[time2.IDnumber[j]][0]
}

func (time2 taskSlice) Swap(i, j int) {
	time2.IDnumber[i], time2.IDnumber[j] = time2.IDnumber[j], time2.IDnumber[i]
}