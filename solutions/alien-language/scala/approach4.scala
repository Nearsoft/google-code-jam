import scala.util.matching.Regex

object Problema1 {
   def main(args: Array[String]) {

        val source = scala.io.Source.fromFile("A-large-practice.in")
        val lines = source.getLines.filter(_.length > 0)
        val ldn = lines.next.split(" ")

        val D = ldn(1).toInt
        val N = ldn(2).toInt

        var palabras = new Array[String](D)
        var patterns = new Array[String](N)

        for(i <- 0 until D){
            palabras(i) = lines.next
        }

        for(i <- 0 until N){
            patterns(i) = lines.next
        }

        patterns = patterns.map(_.replace("(", "[")).map(_.replace(")", "]"))


     for(i <- 0 until N){
        val cont = Stream.iterate(0)(_ + 1).iterator
         for(j <- 0 until D){
             if(palabras(j).matches(patterns(i))){
                cont.next 
             }
         }
         println("Case #"+(i+1)+": "+(cont.next))
     }
   }
} 
