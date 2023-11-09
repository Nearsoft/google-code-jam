import scala.io.StdIn.readLine
import scala.collection.mutable.ListBuffer

object Solution extends App {
  /********************/
  def is_correct(list:ListBuffer[Int]) : Boolean = {
    for(x <- list){
      print(x.toString()+" ")
    }
    println()
    var input = readLine().toInt
    return input == 1
  }
  def askJudge(a:Int, b:Int, c:Int) : Int = {
    println(a.toString()+" "+b.toString()+" "+c.toString())
    return readLine().toInt
  }
  def medianSort(nn:Int, qq:Int) : Boolean = {
    var n = nn
    var q = qq
    var numbers = new ListBuffer[Int]()
    numbers += 1
    numbers += 2

    for(i <- 3 to n){
      var l : Int = 0
      var r : Int = numbers.size -1

      while(((r-l) >= 1) && (q>0)){
        var lp : Int = l + ((r-l) / 3)
        var rp : Int = r - ((r-l) / 3)

        var median : Int = askJudge(numbers(lp), numbers(rp), i)

        q-=1

        if (median == numbers(lp)){
          r = lp -1
          if(l == r){
            r+=1
          }
        }else if(median == numbers(rp)){
          l = rp +1
          if(l == r){
            l-=1
          }
        }else{
          l = lp +1
          r = rp -1
          if(l == r){
            l -=1
          }
        }
      }
      //Insert
      if(l == 0){
        var temp = new ListBuffer[Int]()
        temp += i
        numbers = ListBuffer.concat(temp, numbers)
      }else if(l == numbers.size){
        numbers += i
      }else{
        var temp = new ListBuffer[Int]()
        for(j <- 0 to l - 1){
          temp += numbers(j)
        }

        temp += i

        for(j <- l to numbers.size - 1){
          temp += numbers(j)
        }
        numbers = temp
      }
      //Insert
    }
    return is_correct(numbers)
  }
  /********************/
  var input = readLine()
  var temp = input.split(" ")

  var T : Integer = temp(0).toInt
  var N : Integer = temp(1).toInt
  var Q : Integer = temp(2).toInt

  while(T > 0){
    if(!medianSort(N, Q)){
      T = 0
    }else{
      T -= 1
    }

  }
}
