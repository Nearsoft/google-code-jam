object Solution {

  def main(args: Array[String]): Unit = {
        
  var cases = scala.io.StdIn.readInt()
  for (i <- 1 until cases+1) {
    var len = scala.io.StdIn.readInt()
    var input = scala.io.StdIn.readLine()

    var array = input.split(" ").map(x => x.toInt)
    var num = i
    var cost = reversort(array)
    println("Case #"+num+": "+cost)
        
                            }
 
    }
    //Inicio funciones
    def swap(arr: Array[Int], i: Int, j: Int) ={
    val temp = arr(i)
    arr(i) = arr(j)
    arr(j) = temp
}
def reverse(arr: Array[Int], l: Int, h: Int) = {
    var low = l
    var high = h
    while (low < high)
    {
        swap(arr, low, high)
        low = low + 1 
        high = high-1
    }
}
def min_index(array: Array[Int], i: Int): Int ={
    var min = 100000000
    //var I2: Int = Int.MAX_VALUE
    var min_index = -1
    var j = i
    while (j < array.length) {
        if (array(j) < min) {
            min = array(j)
            min_index = j
        }
        j = j+1
    }
    return min_index
}
def reversort(array: Array[Int]): Int = {
    var cost: Int = 0
    for (i <- 0 until array.length-1) {
        var j = min_index(array, i)
        reverse(array, i, j)
        cost += j - i + 1
    }
    return cost
}
    
    
    //Fin funciones
 }