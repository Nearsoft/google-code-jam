import java.io._

object alienL {
    
   def main(args: Array[String]) {


    var fileLines = io.Source.fromFile("exampleAlien.text").getLines.toList
   
     	                   
	 //var dictionary = Array("abc","bca","dac","dbc","cba")
	 //var cases = Array("(ab)(bc)(ca)","abc","(abc)(abc)(abc)","(zyx)bc")
    
     var caseCounter = 0
     var k = 0
     val writer = new PrintWriter(new File("outputAlienScala.txt" ))

    while(k < fileLines.length-1){
        var indexArray = fileLines(k).toString.split(" ").map(_.toInt)
        var dictionarySize = indexArray(1)
        var casesSize = indexArray(2) 
        var dictionary = fileLines.take(dictionarySize+1).toArray
        var cases = fileLines.takeRight(casesSize).toArray
         
        var pattern = " "
        var counter = 0
      
     for( i <- 0 to cases.length-1) {
         
         pattern = cases(i).replace("(","[").replace(")","]")
         
            for( j <- 1 to dictionary.length-1) {
                if(dictionary(j) matches pattern){
                counter+=1
                } //end if
            }//end for 2
            caseCounter = caseCounter+1
            writer.write("case#"+(caseCounter)+": "+counter+"\n")    
         //println("case#"+(caseCounter)+": "+counter)
         counter=0
        }//end for1 
        k= (k+1)+dictionarySize+casesSize
      }
         writer.close()
    }

}

