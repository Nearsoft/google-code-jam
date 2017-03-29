package main
import ("fmt"
	"strconv"
)

func main() {
	var n int
	fmt.Scanf("%d",&n)
	for i := 0; i<n; i++{
		var nplatos , max , min int
		max = 0
		fmt.Scanf("%d",&nplatos)
		var platos = make([]int,nplatos)
		for k:=0; k<nplatos; k++{
			var tmp int
			fmt.Scanf("%d", &tmp)
			if max<tmp{
				max = tmp
			}
			platos[k] = tmp
		}

		min = max
		for t := 1; t<max; t++{

			var count = t
			for r:=0; r < nplatos; r++{
				count = count + ((platos[r]-1)/t)
			}
			if count < min{
				min = count
			}
		}
		fmt.Println("Case #"+strconv.Itoa(i+1)+": "+strconv.Itoa(min))
	}
}
