  import 'dart:io';

void main() {
  
  final filename ='solution-large.txt';
  var sink = new File(filename).openWrite();

  List<String> lines = new File('A-large-practice.in').readAsLinesSync();
  int i = 2;
  for(int k=1; k<=int.parse(lines[0]); k++){

    var v1 = lines[i].split(" ");
    var v2 = lines[i+1].split(" ");

    List<int> vector1 =[];
    List<int> vector2 =[];

    int j =0;
    while(j< v1.length){
        vector1.add(int.parse(v1[j]));
        vector2.add(int.parse(v2[j]));
        j++;
      }

    vector1.sort();
    vector1.join(",");
    vector2.sort();
    vector2.join(",");
    vector2 = vector2.reversed.toList();
    List<int> result = zipWith(vector1, vector2, multiply);
    int suma = sum(result);
    print(suma);

    sink.write("Case #$k: $suma\n");

    i += 3;

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
    result.add(funkyFunction(aux1[counter],aux2[counter]));
    counter++;
  }
  return result;
}
