fun main(vararg args: String) {
  // t number of cases
  val t  = readLine()!!.toInt()

  for (i in 1..t){
    //r rows, c columns
    val (r, c) = readLine()!!.split(' ').map(String::toInt)
      println("Case #"+i.toString()+":")

      // print the first row in the punched card
      println(".."+"+-".repeat(c-1)+"+")
      println(".."+"|.".repeat(c-1)+"|")

    // complete the rest of the punched card according to parity
    for (h in 1..2*r-1){
      if (h%2==1){
        println("+-".repeat(c)+"+")
      }
      else{
        println("|.".repeat(c)+"|")
      }
    }
  }
}
