/*
*   @author  Manuel Valle
*   @date    Febrero 2015
*/
import CodeJamIO._

object MinScalar {
   def dot (v1 : Array[Long], v2 : Array[Long]) : Long ={
      require(v1.size == v2.size)
      ((v1 zip v2).map{ Function.tupled(_ * _)}).sum
   }

  
   def main(args: Array[String]) {
      /*****************
      *  Input
      ******************/
      val vector_pairs = CodeJamIO.read("A-large-practice.in")
      
      /*****************
      *  Magic
      ******************/
      var solutions = vector_pairs.map((pair) => dot(pair._1.sortWith(_ < _),pair._2.sortWith(_ > _)).toString)
      
      /*****************
      *  Output
      ******************/ 
      CodeJamIO.write("A-large-practice.out", solutions.map(_.toString))
   }
}
