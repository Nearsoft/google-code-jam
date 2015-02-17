/*
*   @author  Manuel Valle
*   @date    Febrero 2015
*/
import codeJamIO._

object minScalarProduct {
   def dot (v1 : Array[Long], v2 : Array[Long]) : Long ={
      require(v1.size == v2.size)
      ((v1 zip v2).map{ Function.tupled(_ * _)}).sum
   }

  
   def main(args: Array[String]) {
      /*****************
      *  Input
      ******************/
      val vector_pairs = codeJamIO.read_scalar("A-large-practice.in")
      
      /*****************
      *  Magic
      ******************/
      var solutions = vector_pairs.map((pair) => dot(pair._1.sortWith(_ < _),pair._2.sortWith(_ > _)).toString)
      
      /*****************
      *  Output
      ******************/ 
      codeJamIO.write("A-large-practice.out", solutions.map(_.toString))
   }
}
