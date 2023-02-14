package main

import (
	"fmt"
	"math"
)

// Just for the reading of the test cases
func main() {
	var T int
	fmt.Scanf("%d", &T)

	for caseNumber := 1; caseNumber <= T; caseNumber++ {
		var N int
		fmt.Scanf("%d", &N)

		F := make([]int64, N+1)
		for i := 1; i < N+1; i++ {
			fmt.Scanf("%d", &F[i])
		}

		P := make([]int64, N+1)
		for i := 1; i < N+1; i++ {
			fmt.Scanf("%d", &P[i])
		}

		// Machines that point to each other
		machines := make(map[int64][]int64)
		for i := 1; i < N+1; i++ {
			machines[P[i]] = append(machines[P[i]], int64(i))
		}

		Fun, _ := chainReaction(0, machines, F)
		fmt.Printf("Case #%d: %d\n", caseNumber, Fun)
	}
}

// The solution of the actual problem
func chainReaction(root int64, machines map[int64][]int64, F []int64) (maxF int64, minV int64) {
	if len(machines[root]) == 0 {
		return F[root], F[root]
	} else {
		maxF = 0
		minV = math.MaxInt64
		for _, machine := range machines[root] {
			total, low := chainReaction(machine, machines, F)
			maxF += total
			minV = int64(math.Min(float64(minV), float64(low)))
		}
	}

	if minV < int64(F[root]) {
		maxF += F[root] - minV
		minV = F[root]
	}

	return
}
