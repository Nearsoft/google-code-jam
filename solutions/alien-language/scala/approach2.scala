object Alien {

	def main(args: Array[String]) {

	    val input = io.Source.fromFile("A-large-practice.in").getLines.toList

	    var L = input(0).split(" ")(0).toInt
	    var D = input(0).split(" ")(1).toInt
	    var N = input(0).split(" ")(2).toInt

	    var words = createArray(input, 1, D+1)
	    var tokens = createArray(input, D+1, D+1+N)

	    for (i <- 0 to tokens.length - 1) {
		println("Case #" + (i+1) + ": " +  words.filter(_.matches(tokens(i).replace("(", "[").replace(")", "]") ) 
).count(_ => true) )
	    }
	}

	def createArray(input: List[String], min: Int, max: Int) : List[String] = {
	    var array = List[String]()
	    for (i <- min to max - 1) {
		array = array :+ input(i)
	    }
	    return array
	}
}
