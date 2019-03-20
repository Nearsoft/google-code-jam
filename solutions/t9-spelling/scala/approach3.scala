object Problema3 {
   def main(args: Array[String]) {
     
        val source = scala.io.Source.fromFile("A-large-practice.in")
        val lines = source.getLines
        val n = lines.next.toInt

        var alphabet = Array(" ", "", "abc","def","ghi","jkl","mno","pqrs","tuv","wxyz")

        for (i <- 0 until n) {
            var anterior = -1
            var palabra = lines.next

            print("Case #"+(i+1)+": ")

        for (j <- 0 until palabra.size) {
        
            for (k <- 0 until alphabet.length) {
                
                for (l <- 0 until alphabet(k).size){

                    if(palabra.charAt(j).equals(alphabet(k).charAt(l))){

                        if (anterior == k){
                            print(" ")
                        }

                        for(m <- 0 to l) {
                            print(k)
                        }

                        anterior = k

                    }

                }

            }
        }
            println("")
        }
    }
}
