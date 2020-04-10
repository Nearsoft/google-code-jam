import scala.collection.mutable.MutableList;
import java.io._;
object HelloWorld {
  def main(args: Array[String]) {
  	var linesTemp = io.Source.fromFile("A-large-practice.in").getLines.toList;
  	var lines:MutableList[String] = MutableList(linesTemp:_*);
    val writer = new PrintWriter(new File("file2.txt" ));

    var testCases = lines(0).toInt;
  	lines = lines.tail;


  	for (i <- 0 until testCases) {
		var vectorNumber: Int = lines(0).toInt;
		lines = lines.tail;

		var vector1: Array[String] = lines(0).split(" +");
		lines = lines.tail;
		
		var vector2: Array[String] = lines(0).split(" +");
		lines = lines.tail;

		var suma: Int = testCase(vector1,vector2);


		writer.write("Case #"+ (i+1) +": "+ suma +"\n");

	}
	writer.close();

  }
  def testCase(vector1: Array[String], vector2: Array[String]): Int = {
  		var vectorTemp: Array[Int] =vector1.map(_.toInt);
		var vector3: Array[Int] = vectorTemp.sorted;
		vectorTemp =vector2.map(_.toInt);
		var vector4: Array[Int] = vectorTemp.sorted.reverse;
		println(vector4.deep.mkString(" "))
		var suma: Int = 0;
		for (i <- 0 until vector4.length){
			suma = suma + vector3(i)*vector4(i).toInt;
		}
	return suma;
  }
}
