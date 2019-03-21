object Key {

    class Functions{
        var keyboard = Array(
                Array("a", "b", "c"),
                Array("d", "e", "f"),
                Array("g", "h", "i"),
                Array("j", "k", "l"),
                Array("m", "n", "o"),
                Array("p", "q", "r", "s"),
                Array("t", "u", "v"),
                Array("w", "x", "y", "z"),
                Array(" ")
            );

        def processLetter(letter: String) : String = {
            var t = getKey(letter);
            var str = "";
            for (i <- 0 to t._2 - 1) {
                str += t._1.toString;
            }
            return str;
        }

        def getKey(letter: String) : (Int, Int) = {
            for( i <- 0 to keyboard.length - 1){
                for (j <- 0 to keyboard(i).length - 1) {
                    if(keyboard(i)(j) == letter){
                        if(i == keyboard.length - 1){
                            return new Tuple2(0,j+1);
                        }
                        return new Tuple2(i+2,j+1);
                    }
                }
            }
            return new Tuple2(-1,-1);
        }

        def charAt(str: String, idx: Int) : String = {
            return str.charAt(idx) + "";
        }
    }

   def main(args: Array[String]) {

	val input = io.Source.fromFile("C-large-practice.in").getLines.toList

        var numberOfWords =  input(0).toInt;

        var lastKey = "";

        val functions = new Functions();

        for (i <- 1 to numberOfWords) {
            var word = input(i);
	    print("Case #" + i + ": ")
            for (j <- 0 to word.length - 1) {
                var fromKeys = functions.processLetter(functions.charAt(word, j));

                if(functions.charAt(fromKeys, 0) == lastKey){
                    print(" ");
                }

                lastKey = functions.charAt(fromKeys, 0);
                print(fromKeys);
            }
            println("");
        }
   }
}
