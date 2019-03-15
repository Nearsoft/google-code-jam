object alien{

	case class wordDictionary(Dictionary: Array[String]){
		var sDictionary: Array[String] = Dictionary
		def addWord(word:String){
			sDictionary = sDictionary ++ Array(word)
			println (word)
		}

		def compareWord(word:String):Int = {
			var tempWord = word.replaceAll("\\(","[")
			tempWord = tempWord.replaceAll("\\)","]")
			var amount = 0
			for(element <- sDictionary){
				if(element.matches(tempWord)){
					amount = amount + 1
				}
			}
			return amount
		}
	}

	def main(args: Array[String]) {
		val content = io.Source.fromFile("myfile.txt").getLines.toList
		var num = 0
		var L = 0
		var D = 0
		var N = 0
		var dic = wordDictionary(Array())
		for(line <- content){
			if(num ==0){
				var header = line.split(" ")
				var count = 0
				for (element <- header){
					count match{
						case 0=> L = element.toInt 
						case 1=> D = element.toInt 
						case 2=> N = element.toInt 
					}
					count = count + 1
				}
			}
			else if(num< D+1){
				dic.addWord(line)
			}
			else if(num< D+N+1){
				println("Case #" + (num-D) + ": " + dic.compareWord(line))
			}
			num = num + 1
		}
	}
}