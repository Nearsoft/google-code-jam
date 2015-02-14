import scala.io.Source
import scala.util.matching.Regex


object T9 {
	def main(args: Array[String]){

  	var line, current =""
  	var frstline,lisset=false
  	var n, counter = 0  
    var file = Source.fromFile("C-small-practice.in").getLines()

//Get control keys
	line= (file next).toString()
	for(x <- 0 until line.length){
		current=current.trim()+line.charAt(x)	
	}
	try{ n=current.toInt}catch{ case e: Exception => 0}
//Save known patterns
	for(y <- 0 until n){
		print("Case #"+(y+1)+": ")
		convert_line((file next).toString())
	}

			
	}
	def convert_line(input:String){
		var line=""
		val patterns = Map(
			   "a" -> "2",
			   "b" -> "22",
			   "c" -> "222",
			   "d" -> "3",
			   "e" -> "33",
			   "f" -> "333",
			   "g" -> "4",
			   "h" -> "44",
			   "i" -> "444",
			   "j" -> "5",
			   "k" -> "55",
			   "l" -> "555",
			   "m" -> "6",
			   "n" -> "66",
			   "o" -> "666",
			   "p" -> "7",
			   "q" -> "77",
			   "r" -> "777",
			   "s" -> "7777",
			   "t" -> "8",
			   "u" -> "88",
			   "v" -> "888",
			   "w" -> "9",
			   "x" -> "99",
			   "y" -> "999",
			   "z" -> "9999",
			   " " -> "0")
		for(z <- 0 until input.length){
			if(z>0){
				if((patterns((input.charAt(z)).toString())).charAt(0) ==(patterns((input.charAt(z-1)).toString())).charAt(0)){			
					line+=" "
				}
			}

			line+=patterns((input.charAt(z)).toString())
		}
		println(line)

	}
	

}
