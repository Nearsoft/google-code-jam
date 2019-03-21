import 'dart:io';
import 'dart:convert';
import 'dart:async';

Map<String> matriz = new Map();
main(){

	List<String> lines = new File('C-large-practice.in').readAsLinesSync();
	matriz['a'] = '2';
	matriz['b'] = '22';
	matriz['c'] = '222';
	matriz['d'] = '3';
	matriz['e'] = '33';
	matriz['f'] = '333';
	matriz['g'] = '4';
	matriz['h'] = '44';
	matriz['i'] = '444';
	matriz['j'] = '5';
	matriz['k'] = '55';
	matriz['l'] = '555';
	matriz['m'] = '6';
	matriz['n'] = '66';
	matriz['o'] = '666';
	matriz['p'] = '7';
	matriz['q'] = '77';
	matriz['r'] = '777';
	matriz['s'] = '7777';
	matriz['t'] = '8';
	matriz['u'] = '88';
	matriz['v'] = '888';
	matriz['w'] = '9';
	matriz['x'] = '99';
	matriz['y'] = '999';
	matriz['z'] = '9999';
	matriz[' '] = '0';

	var lineNumbers = int.parse(lines[0]);
	lines.removeAt(0);

	var createdFile = new File('file.txt').create(recursive: true);
	var file = new File('file.txt');
  	var sink = file.openWrite();

	int lineNumber = 0;
	for (var line in lines) {
		lineNumber ++;
		String concatMessage;
		var previousGroup;
		for(int i = 0; i<line.length; i++){
			var currentGroup = matriz[line[i]][0];
			if(concatMessage == null){
				concatMessage = matriz[line[i]];
			}else if(previousGroup == currentGroup){
				concatMessage = concatMessage + ' ' + matriz[line[i]];
			}else{
				concatMessage = concatMessage + matriz[line[i]];
			}
			previousGroup = currentGroup;
		}

		sink.write("Case #$lineNumber: $concatMessage\n");
	}
	sink.close();
	
}