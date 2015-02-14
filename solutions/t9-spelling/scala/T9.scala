// CREATED BY PEDRO DE LA REE 13/02/2015

import scala.io.Source._


object T9 {

	def readFile(filename:String) : Array[String] ={
		val source = scala.io.Source.fromFile(filename)
		//val lines = source.getLines mkString "\n"
		val lines = source.getLines.toArray
		source.close()
		return lines
	}
	def T9(test:Int, dict:Map[Char,String], word:String) : Unit ={
	
		var result = "";
		var i = 0;
		for (i <- 0 until word.length()) {
			var X = dict(word(i));
			if ( result.length() != 0 && X(X.length()-1) == result(result.length()-1) ) {
				result += " ";
			}
			result += X;
		}
		//return result
	    printf("Case #%d: %s\n", test, result);
	    return 
	}

	def main(args: Array[String]) {

		val dict = Map(

			'a' -> "2",
			'b' -> "22",
			'c' -> "222",
			'd' -> "3",
			'e' -> "33",
			'f' -> "333",
			'g' -> "4",
			'h' -> "44",
			'i' -> "444",
			'j' -> "5",
			'k' -> "55",
			'l' -> "555",
			'm' -> "6",
			'n' -> "66",
			'o' -> "666",
			'p' -> "7",
			'q' -> "77",
			'r' -> "777",
			's' -> "7777",
			't' -> "8",
			'u' -> "88",
			'v' -> "888",
			'w' -> "9",
			'x' -> "99",
			'y' -> "999",
			'z' -> "9999",
			' ' -> "0");

		var str = readFile("practice.in");
		var i = 0;
		for (i <- 2 until str.length) {
			T9(i-1, dict, str(i));
		}

	}
}