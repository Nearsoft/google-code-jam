/* Simple Hello, World! program */
void main(){
    var x="1 2 3 4 5";
    var myList=x.split(' ');
    var myList2="1 0 1 0 1";
    myList2=myList2.split(' ');
    var minimo=0;
    myList.sort();
    myList2.sort((b, a) => a.compareTo(b));
    
    for (var i = 0; i < myList.length; i++) {
        
       minimo+=int.parse(myList[i])*int.parse(myList2[i]);
    }
    print(minimo);
    
}

