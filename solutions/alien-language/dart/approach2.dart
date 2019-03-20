import 'dart:math';
import 'dart:io';
void main() async{
	print("Please input the file name on the same folder");
	var fileName = stdin.readLineSync();
	var config = new File(fileName);
	config.readAsLines().then(resolveLanguage);
}

resolveLanguage(List<String> lines) {
	var header = 1;
	var L = 0, D = 0, N = 0;
	var counter = 0;
	var position = 0;
	var results = 0;
  	var sDictionary = [];
	var sCharacters = [];
	var word = [];
	for (var line in lines) {
		counter++;
		if (counter==header){
			for (var char in line.split(' ')){
				switch (position){
					case 0:
						L = int.parse(char);
						break;
					case 1:
						D = int.parse(char);
						break;
					case 2:
						N = int.parse(char);
						break;
					default:
						break;
				}
				position++;
			}
		}

		else if (counter <= header+D)
			sDictionary.add(line.substring(0,L));

		else if (counter <= header+D+N){
			line = line.replaceAll(new RegExp("\\("), '[');
			line = line.replaceAll(new RegExp("\\)"),"]");
			var regex = new RegExp(line);
			var count = 0;
			for (var word in sDictionary){
				if(regex.hasMatch(word))
					count++;
			}
			var tempCase = counter-header-D;
			print("Case #$tempCase: $count");
		}
	}
	
}