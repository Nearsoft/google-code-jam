/*
*   @author  Manuel Valle
*   @date    Febrero 2015
*/
import scala.io.Source
import java.io._

object codeJamIO {
   def read(filename : String) : Array[(Array[Long], Array[Long])] = {
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

   def write(filename : String, solutions : Array[String]) = {
      val writer = new PrintWriter(new File(filename))
      for ((sol, i) <- solutions.view.zipWithIndex) writer.write("Case #"+ (i+1) + ": " + sol + "\n")
      writer.close()
   }
}
