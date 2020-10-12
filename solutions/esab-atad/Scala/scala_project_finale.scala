import scala.io.StdIn.readLine
import scala.io.StdIn.readInt
object Solution {
  var count= 0
  val scanner = new java.util.Scanner(System.in)

  def main(args: Array[String]): Unit = {
    val input = readLine().split(" ")
    val T=input(0).toInt
    val B=input(1).toInt
    // println("The value of a is "+ T + " " + B)
    for( w <- 0 to T-1){ 
      esab_atad(B) 
    } 
  }

  def printArray(arr: Array[Int]): Unit = {
    val size= arr.size
    for( w <- 0 to size-1){ 
      print(arr(w))
    } 
    print("\n")
  }

  def reverse(arr: Array[Int]): Unit = {
    val size= arr.size
    var tmp = 0
    var division = (size/2).toFloat
    val size_floor= division.floor.toInt
    for( w <- 0 to size_floor-1){ 
      tmp = arr(w)
      arr(w) = arr(size-1-w)
      arr(size-1-w) = tmp
    }
  }
  
  def flipArray(arr: Array[Int]): Unit = {
    val size= arr.size.toInt
    for( w <- 0 to size-1){ 
      arr(w) ^= 1
    }
  }
 
    //Transcripción de Función Query***
  def query(i:Int):Int ={
    if(i != -1){
      println(i+1) //'\n'
    }else{
      println("1") // '\n'
    }
    count += 1
    val input = readLine()
    // println(input)
    return input.toInt
  }
  def check (arr: Array[Int]): Unit = {
    println(arr.mkString) 
    val input = readLine()   
    if(!input.equals("Y")){
      scala.sys.exit(0)
    }    
  }
        
  def esab_atad(B: Int): Unit = {
    var result = new Array[Int](B)
    var flip_i= -1
    var reverse_i= -1
    var flip_res = 0
    var reverse_res = 0
    count = 0
    var division = (B/2).toFloat
    val B_floor= division.floor.toInt
    //fillArray(result)
    for(i <- 0 to B_floor-1){
      if(count != 0 && count%10 == 0){
        flip_res = query(flip_i)
        reverse_res = query(reverse_i)
        if(flip_i != -1 && (result(flip_i)^flip_res) == 1)
          flipArray(result)
        if(reverse_i != -1 && (result(reverse_i)^reverse_res) == 1)
          reverse(result)
      }
      result(i) = query(i)
      result(B-i-1) = query(B-i-1)
      if(result(i) == result(B-i-1))
        flip_i = i
      else
        reverse_i = i
    }
    check(result)
  }
}