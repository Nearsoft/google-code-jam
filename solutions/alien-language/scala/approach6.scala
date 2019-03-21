object HelloWorld {
    import scala.util.matching.Regex
   def main(args: Array[String]) {
      
    var diccionario= Array("abc","bca","dac","dbc","cba")
    var expresion= "(zyx)bc"
    var counter =0
    expresion=expresion.replace('(','[')
    expresion=expresion.replace(')',']')
    val pattern = new Regex(expresion)
    for ( i<- 0 to diccionario.length-1) {
        
        if((pattern findAllIn diccionario(i)).exists(_.trim.nonEmpty)){
            counter=counter+1
        }
    }
    println(counter)
    
    
}
}


