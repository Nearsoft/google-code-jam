/*
*   @author  Manuel Valle
*   @date    Febrero 2015
*/
import codeJamIO._

object alienLang {
   def matchStr(str: String, pattern: String) : Int ={
      if (str.matches(pattern)) 1 else 0
   }
  
   def main(args: Array[String]) {

      /*****************
      *  Input
      ******************/
      val input = codeJamIO.read_alien("A-large-practice.in")
      val dictionary = input._1
      val tests = input._2

      /*****************
      *  Magic
      ******************/
      var solutions = tests.map(_.replace("(", "[")).map(_.replace(")", "]")).map((pattern) => dictionary.map((word) => matchStr(word, pattern)).sum.toString)
      
      /*****************
      *  Output
      ******************/ 
      codeJamIO.write("A-large-practice.out", solutions)
   }
}
