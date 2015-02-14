import scala.io.Source
import scala.util.matching.Regex


object AlienLang {
  def main(args: Array[String]){

  	var line, current =""
  	var frstline,lisset=false
  	var l, d, n,counter = 0  
    var file = Source.fromFile("A-large-practice.in").getLines()

//Get control keys
	line= (file next).toString()
	for(x <- 0 until line.length){
		if(line.charAt(x)==32){
			if(lisset==false){
				try { l=current.toInt} catch { case e: Exception => 0 }
				current=""
				lisset=true
				
			}
			else{
				try { d=current.toInt } catch { case e: Exception => 0 }
				current=""
			}					
		}
		current=current.trim()+line.charAt(x)	
	}
	try{ n=current.toInt}catch{ case e: Exception => 0}
	
//Save known patterns
	var patterns :Array [String] =new Array (d)
	for(y <- 0 until d){
		patterns (y) = (file next).toString()
	}

//Prepare Expressions	
	for(z <- 0 until n){
		var regular_expression = prepexpression((file next).toString())
		var counter =0
		
		for(i <-0 until d){
			if(patterns(i).matches(regular_expression)){
				counter+=1
			}
		}
		println("Case #"+(z+1)+": "+counter)
	}


			
}




	def prepexpression(expression:String) : String ={
		var exprstart , pause = false
		var x=0
		var regularexpr=""
		while(x < expression.length()) {
			if(expression.charAt(x)==40){
				exprstart=true
				regularexpr+= expression.charAt(x)
				x+=1
				regularexpr+= expression.charAt(x)
			}
			else if (expression.charAt(x)==41){
				exprstart=false

			}

		
			if (exprstart==true){
				regularexpr+="|"
			}

		regularexpr+= expression.charAt(x)
		x+=1
			
		}
		return regularexpr
	}
}
