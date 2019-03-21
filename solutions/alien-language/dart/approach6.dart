/* Simple Hello, World! program */
void main(){
   var expresion  = "(zyx)bc";
	var counter  = 0;   
	expresion = expresion.replaceAll( '(', '[');
	expresion = expresion.replaceAll(')', ']');
	RegExp exp = new RegExp(expresion);
	var t = ["abc", "bca", "dac", "dbc", "cba"];
	Iterable<Match> matches ;
	for (var i = 0; i < t.length; i++) {
         matches=exp.allMatches(t[i]);
        counter+=matches.length;     
    }
    print(counter);


    
}

