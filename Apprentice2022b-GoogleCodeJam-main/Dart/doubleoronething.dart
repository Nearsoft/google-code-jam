import 'dart:io'; // Input import

void main() {
  int cases = int.parse(stdin.readLineSync()); // No. of Cases variable
  
  // For loop to execute the function and printing the result
  for (int i = 1; i < cases + 1; i++){
    String curr = doubleOrOneThing(stdin.readLineSync());
    print("Case #$i: $curr");
  }
}

doubleOrOneThing(str) {
  String res = ''; // Variable where the result will be stored
    
    for (int i = 0; i < str.length; i++){
        
        String letter = str[(str.length - 1) - i]; /* We start iterating from the end
        otherwise we would need an algorithm to go through all possible solutions*/
        
        String comp1 = letter + letter + res; 
        String comp2 = letter + res;
        /* comp1, comp2 variables will let us compare which word would have
        a higher lexicographical order using a string comparison method built
        in most programming languages */

        // Example: word= PEEL, then, "LL" > "L" ? Make res "LL" : Make res "L"
        // Returns "L", then, "EEL" > "EL" ? Make res "EEL" : Make res "EL"
        if (comp1.compareTo(comp2) == 1) {
            res = letter + res;
        }
        else{
            res = letter + letter + res;
        }
    }
     // Return final word as a String   
    return res;
}