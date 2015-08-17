object AlienLanguage extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  var params = lines.next.toString.split(" ").map(_.toInt)
  var dictionary_length = params(1).toInt

  //println(dictionary_length)
  var dictionary =   Array.empty[String]

  //Filling the dictrionary
  for (i <- 0 to dictionary_length - 1) {
    dictionary :+= lines.next.toString
  }

  var num_case = 0

  //Iterating over the test cases
  while(lines.hasNext){
    var test_case = lines.next //getting the regex
    test_case = test_case.toString.replace('(', '[')
    test_case = test_case.toString.replace(')', ']')

    var regex = test_case
    var res = 0

    //Matching de regex with each word of the dictionary
    for (i <- dictionary) {
      if (i.matches(regex)) { res += 1 }
    }

    num_case += 1   //Getting the testcase number
    println("Case #" + num_case  + ": " + res)
  }
}
