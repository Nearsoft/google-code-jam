import "dart:io";

void main() {
    
    final filename = 'solution-large.txt';
    var sink = new File(filename).openWrite();

    var letterTransform = {
  // Keys      Values
    'a' : '2',
    'b': '22',
    'c' : '222',
    'd' : '3',
    'e': '33',
    'f' : '333',
    'g' : '4',
    'h': '44',
    'i' : '444',
    'j' : '5',
    'k': '55',
    'l' : '555',
    'm' : '6',
    'n': '66',
    'o' : '666',
    'p' : '7',
    'q': '77',
    'r' : '777',
    's' : '7777',
    't': '8',
    'u' : '88',
    'v' : '888',
    'w': '9',
    'x' : '99',
    'y' : '999',
    'z': '9999',
    ' ' : '0'
  };

  List<String> lines = new File('C-large-practice.in').readAsLinesSync();
  for(int k=1; k<=int.parse(lines[0]); k++){
    String word = lines[k];
    word = word.toLowerCase();
    String result = "";
    String lastCharacter = null;
    for(int i= 0; i < word.length; i++){
      if(lastCharacter != null){
       if(lastCharacter[0] == letterTransform[word[i]].toString()[0]){
          result = result + " ";
       }
     }
      result= result + letterTransform[word[i]].toString();
      lastCharacter = letterTransform[word[i]].toString();
    }
    sink.write("Case #$k: $result\n");
  }
}
sum(List<int> a, [int result]){
  if(result==null){
    List<int> auxList = a;
    return sum(auxList, 0);
  }else{
    result += a.removeLast();
    if(a.length > 0) return sum(a, result);
    else return result;
  }
}

multiply(int a, int b){
  return a*b;
}

zipWith(List list1, List list2, Function funkyFunction){
  int counter = 0;
  List aux1 = list1;
  List aux2 = list2;
  List result = [];
  while(aux1.length > aux2.length){ 
    aux1.removeLast();
  }
  while(aux2.length > aux1.length){ 
    aux2.removeLast();
  }
  while(counter < aux1.length){
    print(result);
    result.add(funkyFunction(aux1[counter],aux2[counter]));
    counter++;
  }
  return result;
}
