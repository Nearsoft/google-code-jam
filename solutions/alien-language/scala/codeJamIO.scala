/*
*   @author  Manuel Valle
*   @date    Febrero 2015
*/
import scala.io.Source
import java.io._

object codeJamIO {
   def read(filename : String) : (Array[String], Array[String]) = {
      val f_lines = Source.fromFile(filename).getLines
      val input_data = f_lines.next.split(" ")
      //val len_word = input_data(0).toInt
      val len_dict = input_data(1).toInt 
      val len_tests = input_data(2).toInt

      val dictionary = f_lines.take(len_dict).toArray
      val tests = f_lines.take(len_tests).toArray

      return (dictionary, tests)
   }
   def write(filename : String, solutions : Array[String]) = {
      val writer = new PrintWriter(new File(filename))
      for ((sol, i) <- solutions.view.zipWithIndex) writer.write("Case #"+ (i+1) + ": " + sol + "\n")
      writer.close()
   }
}
