import 'dart:io';
import 'dart:collection';
void main() async{
	print("Please input the file name on the same folder");
	var fileName = stdin.readLineSync();
	var config = new File(fileName);
	config.readAsLines().then(translate);
}

translate(List<String> lines){
	var N=0;
	var value = 0, index = 0, tempIndex = 0;
	var word="";
	var currentCase = 1;
	var repeated = false;
	var mapping = [" " , "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"];
	for (var line in lines){
		word = "";
		if(N==0)
			N = int.parse(line);
		else{
			for(var char in line.split('')){
				var element = mapping.firstWhere((o) => o.contains(char), orElse: () => '');
				tempIndex = mapping.indexOf(element);
				if(tempIndex == index)
					repeated = true;
				index = tempIndex;
				value = element.indexOf(char);
				for (var i = 0; i<=value; i++){
					if(repeated){
						word += " ";
						repeated = false;
					}
					word+="$index";
				}
			}
			print ("Case #$currentCase: $word");
			currentCase++;
		}
	}
}