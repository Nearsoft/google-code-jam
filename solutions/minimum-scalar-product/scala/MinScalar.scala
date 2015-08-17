object MinimumScalar extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)
  lines.next //Skipping first line

  var first_array =   Array.empty[Int]
  var second_array =   Array.empty[Int]

var test_case = 0
  //Iterating over the text to get the vector values
  while(lines.hasNext){
    lines.next //forget about the vectors length
    first_array = lines.next.toString.split(" ").map(_.toInt)
    second_array = lines.next.toString.split(" ").map(_.toInt)

    first_array = first_array.sortWith(_ < _)
    second_array = second_array.sortWith(_ > _)

    var res = 0
    for (i <- 0 to first_array.length - 1) {
      res += first_array(i) * second_array(i)
    }

    //Getting the testcase number
    test_case += 1

    println("Case #" + test_case  + ": " + res)
  }
}
