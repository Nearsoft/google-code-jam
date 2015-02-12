import scala.util.matching.Regex
import scala.io.Source._

object Main {
	def main(args: Array[String]) {
		print("Please enter L,D,N vars: ")
		val numbers = Console.readLine
		val tokens = numbers.split(" ")
		val L: Int = tokens(0).toInt
		val D: Int = tokens(1).toInt
		val N: Int = tokens(2).toInt

		println("Please enter all known words: ")
		val input = new Word(D,N)
		input.getD

		println("Please enter all unknown words: ")
		input.getN
		
		println("These are the matches: ")
		input.compare
	}
}

class Word(dict: Int, newWord: Int) {
	var i: Int = 0
	var j: Int = 0
	var dictionary = new Array[String](dict)
	var unknown = new Array[String](newWord)
	def getD{
		for (i <- 0 until dict) {
			dictionary(i) = Console.readLine 
		}
	}
	def getN{
		for (i <- 0 until newWord){
			unknown(i) = Console.readLine 
			unknown(i) = unknown(i).replaceAll("\\(","[")
			unknown(i) = unknown(i).replaceAll("\\)","]")
		}
	}
	def compare{
		for (i <- 0 until newWord) {
			var counter: Int = 0
			for (j <- 0 until dict){
				val p=  unknown(i).r
				val d= dictionary(j)
				if(p.pattern.matcher(d).matches){
					counter=counter+1
				}
			}
			println("Case #"+i+": "+counter)
		}
	}
}
