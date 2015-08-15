
import java.io._

object t9{


	def main(args: Array[String]){
	  var map:Map[Char,Int]= Map()
	 map += ('a' -> 2)
	 map += ('b' -> 22)
	 map += ('c' -> 222)
	 map += ('d' -> 3)
	 map += ('e' -> 33)
	 map += ('f' -> 333)
	 map += ('g' -> 4)
	 map += ('h' -> 44)
	 map += ('i' -> 444)
	 map += ('j' -> 5)
	 map += ('k' -> 55)
	 map += ('l' -> 555)
	 map += ('m' -> 6)
	 map += ('n' -> 66)
	 map += ('o' -> 666)
	 map += ('p' -> 7)
	 map += ('q' -> 77)
	 map += ('r' -> 777)
	 map += ('s' -> 7777)
	 map += ('t' -> 8)
	 map += ('u' -> 88)
	 map += ('v' -> 888)
	 map += ('w' -> 9)
	 map += ('x' -> 99)
	 map += ('y' -> 999)
	 map += ('z' -> 999)
	 map += (' ' -> 0)

	 
	 var fileLines = io.Source.fromFile("t9ScalaExample.text").getLines.toList
	 val writer = new PrintWriter(new File("outputT9Scala.txt" ))
	 

	 for(i<-1 to fileLines.length-1){

	 	var finalMessage :String = ""
	  	var last : Char = '1'


	    for( j <- 0 to fileLines(i).length()-1) {
	  	  var char = fileLines(i).charAt(j)
	  	  var firstNumber =map(char).toString.charAt(0)
	  		if(last == firstNumber){
	  			finalMessage = finalMessage+" "
	     	}
	  	  last = firstNumber
	  	  finalMessage= finalMessage+map(char)	
	    } 
	  	writer.write("case#"+i+": "+finalMessage+"\n")
	 
	 }
	 writer.close()
	 
	}
}