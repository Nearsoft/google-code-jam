object phoneTranslate{
	def main(args: Array[String]){
		val content = io.Source.fromFile("myfile.txt").getLines.toList
		val dictionary = Array (" ", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz")
		var num = 0
		var N = 0
		
		for(line <- content){
			var lastIndex = 10
			if(num ==0)
				N = line.toInt
			else if(num<=N){
				print("Case #" + num + ": ")
				for(char <- line){
					var index =dictionary.indexWhere{ case x => x.contains(char) }
					if(index == lastIndex)
						print(" ")
					else
						lastIndex=index
					for (i <- 0 to dictionary(index).indexOf(char)){
						print(index)
					}
				}	
			}
			num=num+1
			print("\n")
		}

	}
}