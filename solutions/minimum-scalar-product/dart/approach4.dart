import 'dart:io';
main(){
	List<String> lines = new File('A-large-practice.in').readAsLinesSync();
	var file = new File('file.txt');
  	var sink = file.openWrite();

	var testCases = int.parse(lines[0]);
	lines.removeAt(0);

	for (int i = 0;i<testCases; i++) {
		int vectorNumber = int.parse(lines[0]);
		lines.removeAt(0);

		List<int> vector1 = lines[0].split(' ');
		lines.removeAt(0);
		List<int> vector2 = lines[0].split(' ');
		lines.removeAt(0);

		vector1.sort((x, y) => int.parse(x).compareTo(int.parse(y)));
		vector2.sort((x, y) => int.parse(y).compareTo(int.parse(x)));

		var suma = testCase(vector1,vector2);

		sink.write("Case #${i+1}: $suma\n");

	}
	sink.close();
}

String testCase(List vector1, List vector2){
		var suma = 0;
		for(int i = 0;i<vector1.length;i++){
			suma = suma + int.parse(vector1[i])*int.parse(vector2[i]);
		}
	return suma;
}