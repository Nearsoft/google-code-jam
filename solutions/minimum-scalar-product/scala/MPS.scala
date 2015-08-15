object MPS{

	case class Vector(Xn:Array[Int],Yn:Array[Int]){
		var x: Array[Int] = Xn
		var y: Array[Int] = Yn
		def assignX(sXn:Array[String]){
			x = Array()
			for(Xn <- sXn){
				x= x ++ Array(Xn.toInt)
			}
		}
		def assignY(sYn:Array[String]){
			y = Array()
			for(Yn <- sYn){
				y= y ++ Array(Yn.toInt)
			}	
		}
		def sort(){
			scala.util.Sorting.quickSort(x)
			scala.util.Sorting.quickSort(y)
		}
		def calc(n:Int):Int = {
			var sum = 0
			for (i <- 0 to n-1){
				sum += x(i) * y(n-1-i)
			}
			return sum
		}

	}
	
	def main(args: Array[String]) {
		val content = io.Source.fromFile("myfile.txt").getLines.toList
		var num = 0
		var T = 0
		var n = 0
		var v = Vector(Array(0),Array(0))
		for(line <- content){
			if(num ==0)
				T = line.toInt
			else if(num<=T*3){
				num%3 match{
					case 1=> n = line.toInt 

					case 2=> v.assignX(line.split(" ")) 

					case 0=> {
								v.assignY(line.split(" "))
								v.sort()
								println("Case #" + num/3 + ": " + v.calc(n))
							 }
				}
			}
			num=num+1
		}
	}
}