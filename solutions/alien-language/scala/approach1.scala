/*
*   @author  Manuel Valle
*   @date    Febrero 2015
*/
import CodeJamIO._

object AlienLang {
   def matchStr(str: String, pattern: String) : Int ={
      if (str.matches(pattern)) 1 else 0
   }
  
   def main(args: Array[String]) {

      /*****************
      *  Input
      ******************/
      val input = CodeJamIO.read("A-large l-practice.in")
      val dictionary = input._1
      val tests = input._2

      /*****************
      *  Magic
      ******************/
      var solutions = tests.map(_.replace("(", "[")).map(_.replace(")", "]")).map((pattern) => dictionary.map((word) => 
matchStr(word, pattern)).sum.toString)
      
      /*****************
      *  Output
      ******************/ 
      CodeJamIO.write("A-large-practice.out", solutions)
   }
}
