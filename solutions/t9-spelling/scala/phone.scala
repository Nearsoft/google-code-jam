import scala.collection.mutable.MutableList;
import java.io._;
object HelloWorld {
  def main(args: Array[String]) {
  	var linesTemp = io.Source.fromFile("C-large-practice.in").getLines.toList;
  	var lines:MutableList[String] = MutableList(linesTemp:_*);
    val writer = new PrintWriter(new File("file2.txt" ));  	

  	var map:Map[Char,String] = Map();

  	map += ('a'->"2");
  	map += ('b'->"22");
  	map += ('c'->"222");
  	map += ('d'->"3");
  	map += ('e'->"33");
  	map += ('f'->"333");
  	map += ('g'->"4");
  	map += ('h'->"44");
  	map += ('i'->"444");
  	map += ('j'->"5");
  	map += ('k'->"55");
  	map += ('l'->"555");
  	map += ('m'->"6");
  	map += ('n'->"66");
  	map += ('o'->"666");
  	map += ('p'->"7");
  	map += ('q'->"77");
  	map += ('r'->"777");
  	map += ('s'->"7777");
  	map += ('t'->"8");
  	map += ('u'->"88");
  	map += ('v'->"888");
  	map += ('w'->"9");
  	map += ('x'->"99");
  	map += ('y'->"999");
  	map += ('z'->"9999");
  	map += (' '->"0");

  	var numberOfLines = lines(0);
  	lines = lines.tail;
    var lineNumber: Int = 0;
  	for (line <- lines) {
		lineNumber = lineNumber + 1;
		var concatMessage: String = null;
		var previousGroup: String = "";
		var i: Int = 0;
		for(i <- 0 until line.length){
			//print("char at: " + line.charAt(i) + ", ");
			var currentGroup = map(line.charAt(i)).charAt(0).toString();
			if(concatMessage == null){
				concatMessage = map(line.charAt(i));
			}else if(previousGroup == currentGroup){
				concatMessage = concatMessage + ' ' + map(line.charAt(i));
			}else{
				concatMessage = concatMessage + map(line.charAt(i));
			}
			previousGroup = currentGroup;
		}

		writer.write("Case #" + lineNumber + ": "+ concatMessage +"\n");
	}
	writer.close();


  }
}