object HelloWorld {
   def main(args: Array[String]) {
      
    var t =Array("2", "22", "222", "3", "33", "333", "4", "44", "444", "5", "55", "555", "6", "66", "666", "7", "77", "777", "7777", "8", "88", "888", "9", "99", "999", "9999")
    var mensaje  = "hello world"
	var aux  = ""
	var aux2  =""
	var codigo  = ""
	var mensajeFinal  = ""
	
	for ( i <- 0 to (mensaje.length - 1) ) {
         mensaje(i) match{
             case'a' => codigo = t(0)
		    	aux = "uno"
             case'b' => codigo = t(1)
		    	aux = "uno"
		     case'c' => codigo = t(2)
		    	aux = "uno"
		    	
		    case'd' => codigo = t(3)
		    	aux = "dos"
		    case'e' => codigo = t(4)
		    	aux = "dos"
		    case'f' => codigo = t(5)
		    	aux = "dos"
		    	
		    case'g' => codigo = t(6)
		    	aux = "tres"
		    case'h' => codigo = t(7)
		    	aux = "tres"
		    case'i' => codigo = t(8)
		    	aux = "tres"
		    	
		    case'j' => codigo = t(9)
		    	aux = "cuatro"
		    case'k' => codigo = t(10)
		    	aux = "cuatro"
		    case'l' => codigo = t(11)
		    	aux = "cuatro"
		    	
		    case'm' => codigo = t(12)
		    	aux = "cinco"
		    case'n' => codigo = t(13)
		    	aux = "cinco"
		    case'o' => codigo = t(14)
		    	aux = "cinco"
		    	
		    case'p' => codigo = t(15)
		    	aux = "seis"
		    case'q' => codigo = t(16)
		    	aux = "seis"
		    case'r' => codigo = t(17)
		    	aux = "seis"
		    case's' => codigo = t(18)
		    	aux = "seis"
		    	
		    case't' => codigo = t(19)
		    	aux = "siete"
		    case'u' => codigo = t(20)
		    	aux = "siete"
		    case'v' => codigo = t(21)
		    	aux = "siete"
		    
		    case'w' => codigo = t(22)
		    	aux = "ocho"
		    case'x' => codigo = t(23)
		    	aux = "ocho"
		    case'y' => codigo = t(24)
		    	aux = "ocho"
		    case'z' => codigo = t(25)
		    	aux = "ocho"
		    case' ' => codigo = "0"
		    	aux = "cero"
             
         }
         if (aux==aux2){
             mensajeFinal+=" "
         }
         mensajeFinal+=codigo
         aux2=aux
         codigo=""
      }
      println(mensajeFinal)

   
   }
}


