object Problema2 {
   def main(args: Array[String]) {
     
        val source = scala.io.Source.fromFile("A-large-practice.in")
        val lines = source.getLines
        val T = lines.next.toInt
        
        for (i <- 0 until T) {
            var mult = 0
            var n = lines.next.toInt
            var vector1str = lines.next.split(" ")
            var vector2str = lines.next.split(" ")

            var vector1 = new Array[Int](n);
            var vector2 = new Array[Int](n);

            for (j <- 0 until n) {
                vector1(j) = vector1str(j).toInt
                vector2(j) = vector2str(j).toInt
            }

            vector1 = vector1.sortWith(_ < _)
            vector2 = vector2.sortWith(_ > _)
            
            for (k <- 0 until n) {
                mult += vector1(k) * vector2(k)
            }

            println("Case #"+(i+1)+": "+(mult))
        
        }
    }
}