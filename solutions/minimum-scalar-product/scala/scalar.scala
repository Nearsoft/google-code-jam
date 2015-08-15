
import scala.io.Source
import java.io._

object Vectors{

def SortArray(array: Array[Int]){
	scala.util.Sorting.quickSort(array)
}

def Scalar(a1: Array[Int], a2: Array[Int], counter: Int) : Int ={
	var total = 0
	for( i <- 0 to a1.length-1) {
		var sum= (a1(i)*a2(i))
		total += sum
	}
	return total
}

	 def main(args: Array[String]){
	
		var fileLines = io.Source.fromFile("example.text").getLines.toList
		var counter = 0


      var vector1 = Array.empty[Int]
      var vector2 = Array.empty[Int]
      val writer = new PrintWriter(new File("outputVectorsScala.txt" ))


		for(i <- 2 to fileLines.length-2 by 3){
			counter= counter+1
			vector1 = fileLines(i).toString.split(" ").map(_.toInt)
 			vector2 = fileLines(i+1).toString.split(" ").map(_.toInt)

			SortArray(vector1)
			SortArray(vector2)
			vector2 =vector2.reverse

			writer.write("case#"+counter+":"+Scalar(vector1, vector2, counter)+"\n")	
		}
		writer.close()
	}
	
}