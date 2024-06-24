
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashSet
import scala.util.control._
object Solution {
  def main(args: Array[String]): Unit = {
    val inloop = new Breaks;

    var cases : Int = scala.io.StdIn.readLine().toInt;
    //Iterate over cases
    for( c <- 1 to cases){
      var trace : Int = 0;
      var size : Int = scala.io.StdIn.readLine().toInt;

      var matrix= Array.ofDim[String](size, size)
      //Fill matrix

      for (i <- 0 to size - 1){
        matrix(i) = scala.io.StdIn.readLine.split(" ");
      }

      //Calculate Trace
      for (i <- 0 to size - 1){
        trace += matrix(i)(i).toInt;
      }


      //Get row repetead elements
      var row_repetead = 0
      for (i <- 0 to size - 1){
        var hs_rows : HashSet[String] = HashSet.empty[String];
        inloop.breakable{
        for (j <- 0 to size - 1){
          if (hs_rows.contains(matrix(i)(j))){
            row_repetead += 1;
            inloop.break;
            } else{
            hs_rows.add(matrix(i)(j));
            }
          }}
        }
      var columns_repetead = 0
      //Get Column repetead elements
      for (i <- 0 to size-1){
        var hs_columns : HashSet[String] = HashSet.empty[String];
        inloop.breakable{
        for(j <- 0 to size-1){
          if(hs_columns.contains(matrix(j)(i))){
            columns_repetead += 1;
            inloop.break;
          } else{
            hs_columns.add(matrix(j)(i));
          }
        }
        }
      }
      println(s"Case #$c: $trace $row_repetead $columns_repetead")
    }
  }
}
