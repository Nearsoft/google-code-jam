package main

import (
	"fmt"
	"os"
)

func main() {
	input, err := os.Open("C-small-practice.in")
	if err != nil {
		panic(err)
	}
	defer input.Close()

	out, err := os.Create("outputDijkstra.out")
	if err != nil {
		panic(err)
	}
	defer out.Close()

	var n int
	_, err = fmt.Fscanln(input, &n)

	for i := 0; i < n; i++ {
		var l, x int

		_, err = fmt.Fscan(input, &l, &x)

		s := make([]byte, l)
		_, err = fmt.Fscan(input, &s)

		var small int = x % 4
		if small > 0 {
			small += 4
			if small > x {
				small = x
			}

			longs := s
			for j := 0; j < small-1; j++ {
				longs = append(longs, s...)
			}

			// its much easir to use ints instead of strings good way to go in the future.
			lookup := [][]int{{1, 2, 3, 4}, {2, -1, 4, -3}, {3, -4, -1, 2}, {4, 3, -2, -1}}
			var result int = 1
			var ipos, jpos int = -1, -1
			for j := 0; j < len(longs); j++ {
				var rep int
				if longs[j] == '1' {
					rep = 0
				} else {
					rep = int(longs[j]) - int('i') + 1
				}

				var sign int = 1
				if result < 0 {
					result = -result
					sign = -1
				}
				result = lookup[result-1][rep] * sign
				if result == 2 {
					ipos = j
					break
				}
			}

			if ipos > -1 {
				result = 1
				for j := ipos + 1; j < len(longs); j++ {
					var rep int
					if longs[j] == '1' {
						rep = 0
					} else {
						rep = int(longs[j]) - int('i') + 1
					}

					var sign int = 1
					if result < 0 {
						result = -result
						sign = -1
					}
					result = lookup[result-1][rep] * sign
					if result == 3 {
						jpos = j
						break
					}
				}

				if jpos > -1 {
					result = 1
					for j := jpos + 1; j < len(longs); j++ {
						var rep int
						if longs[j] == '1' {
							rep = 0
						} else {
							rep = int(longs[j]) - int('i') + 1
						}

						var sign int = 1
						if result < 0 {
							result = -result
							sign = -1
						}
						result = lookup[result-1][rep] * sign
					}
					if result == 4 {
						fmt.Printf("Case #%d: YES!\n", i+1)
						fmt.Fprintf(out, "Case #%d: YES\n", i+1)
					} else {
						fmt.Printf("Case #%d: No\n", i+1)
						fmt.Fprintf(out, "Case #%d: NO\n", i+1)
					}

				} else {
					fmt.Fprintf(out, "Case #%d: NO\n", i+1)

				}

			} else {
				fmt.Fprintf(out, "Case #%d: NO\n", i+1)

			}
		} else {
			fmt.Printf("Case #%d: NO\n", i+1)
			fmt.Fprintf(out, "Case #%d: NO\n", i+1)
		}
	}
}
