/*
*   @author  Manuel Valle
*   @date    Febrero 2015
*/
import scala.io.Source
import java.io._

object codeJamIO {
   def read_scalar(filename : String) : Array[(Array[Long], Array[Long])] = {
      val f_lines = Source.fromFile(filename).getLines
      f_lines.next // skip test cases quantity, we don't need it

      var v1 = Array.empty[Long]
      var v2 = Array.empty[Long]
      var vector_pairs = Array.empty[(Array[Long], Array[Long])]

      while (f_lines.hasNext){
         f_lines.next //skip vector size, we don't need it
         v1 = f_lines.next.toString.split(" ").map(_.toLong)
         v2 = f_lines.next.toString.split(" ").map(_.toLong)

         vector_pairs = vector_pairs:+(v1,v2)
      }
      return vector_pairs
   }

   def read_alien(filename : String) : (Array[String], Array[String]) = {
      val f_lines = Source.fromFile(filename).getLines
      val input_data = f_lines.next.split(" ")
      val len_dict = input_data(1).toInt //input_data(0) is word length, but we don't need it
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
