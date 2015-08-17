object T9Spelling extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)
  lines.next //forget about the test cases number

  var numpad = Array(
    " ",
    "",
    "abc",
    "def",
    "ghi",
    "jkl",
    "mno",
    "pqrs",
    "tuv",
    "wxyz"
  )

  var num_case = 0

  //Reading the test cases
  while(lines.hasNext){
    var res = "";
    var prev_char = 0
    var test_case = lines.next

    //For every character on the test case find its number on the keypad
    for(i <- 0 to test_case.length - 1){
      var char = test_case.charAt(i)

      for(j <- 0 to numpad.length -1) {

        if(numpad(j).indexOf(char) > -1) {
          if (prev_char == j) res += " ";
          var num_index = numpad(j).indexOf(char)

          for(k <- 0 to num_index){
             res += j
          }

          prev_char = j

        }
      }
    }
    num_case += 1
    println("Case #" + num_case + ": " +res)

  }



}
