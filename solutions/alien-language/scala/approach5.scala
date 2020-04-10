import scala.collection.mutable.MutableList;
import scala.util.matching.Regex;
import java.io._;
object HelloWorld {
  def main(args: Array[String]) {
    var linesTemp = io.Source.fromFile("A-large-practice.in").getLines.toList;
    var lines:MutableList[String] = MutableList(linesTemp:_*);
    val writer = new PrintWriter(new File("file2.txt" ));
    var parametersString = lines(0);
    var parametersList = parametersString.split(' ');
  	var wordsNumber = parametersList(1).toInt;
  	var testCasesNumber = parametersList(2).toInt;
  	var regexList: MutableList[String] = MutableList();
  	var dictionary: MutableList[String] = MutableList();
  	
  	println(parametersString);

  	var i: Int = 1;
  	var testCaseCounter: Int = 0;
  	lines = lines.tail;
  	println (wordsNumber);
  	for (line <- lines) {
      if(i <= wordsNumber){
        dictionary += line;
      }
      if(i>wordsNumber && i < testCasesNumber + wordsNumber + 1){
        var testSuccessCounter: Int = 0;
        testCaseCounter = testCaseCounter +1;
        var regularExp: String = changeToRegEx(line);
        regexList+=regularExp;
      }
      i = i+1;;
    }



  	var j: Int = 1;
  	for(regex <- regexList){
  		
  	  var count: Int = 0;
  	  for(word<-dictionary){
  	    val patternMatch = (regex.r findFirstIn word);
  		if(patternMatch != None){
          count = count + 1;
        }
  	  }
  	  writer.write("Case #" + j + ": " + count + "\n");
      j=j+1;
  	}
	
	writer.close();

  }

  def changeToRegEx(line: String): String = {
  	var newLine: String = "";
  	var newLine1: String = "";
    newLine = line.replace('(','[');
    newLine1 = newLine.replace(')',']');
    return newLine1;
  }
}