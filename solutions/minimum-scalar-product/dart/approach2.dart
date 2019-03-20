import 'dart:math';
import 'dart:io';
import 'dart:collection';
void main() async{
	print("Please input the file name on the same folder");
	var fileName = stdin.readLineSync();
	var config = new File(fileName);
	config.readAsLines().then(resolveMinimum);
}

resolveMinimum(List<String> lines){
	var T;
	var start = 2, vector1 = 3, vector2 = 4;
	var n = 0;
	var tempCase = 1;
	var v1 = [];
	var v2 = [];
	var counter = 0;
	for (var line in lines) {
		counter++;
		if(counter == start){
			n = int.parse(line);
		} else if(counter == vector1){
			v1 = line;
		} else if(counter == vector2){
			v2 = line;
			if(tempCase<=T){
				calculateMinimum(v1,v2,n,tempCase);
				tempCase++;
				start += 3;
				vector1 += 3;
				vector2 += 3;
			}
		} else{
			T = int.parse(line);
		}
		
	}
}

calculateMinimum(var v1, var v2, var n, var currentCase){
	var temp1=[];
	var temp2=[];
	var min;
	var max;
	var result = 0;
	for(var value in v1.split(' ')){
		temp1.add(int.parse(value));
	}
	temp1.sort();
	for(var value in v2.split(' ')){
		temp2.add(int.parse(value));
	}
	temp2.sort();
	for(int i = 0; i<n; i++){
		result += temp1[i]*temp2[n-1-i];
	}
	print("Case #$currentCase: $result");
}