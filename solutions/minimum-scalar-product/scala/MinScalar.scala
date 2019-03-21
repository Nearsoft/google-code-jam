object Minimum {

   def main(args: Array[String]) {

	val input = io.Source.fromFile("A-small-practice-minimum.in").getLines.toList
	var numberOfVectors =  input(0).toInt

	for (i <- 0 to numberOfVectors - 1) {
		var elements = input((3 * i + 1)).toInt
		var vector1 = input((3 * i + 2)).split(" ").map(_.toLong).sorted
		var vector2 = input((3 * i + 3)).split(" ").map(_.toLong).sorted.reverse
		var result = (vector1, vector2).zipped.map(_ * _).sum.toLong
		println( "Case #" + (i+1) + ": " + result )
	}
 }
}
