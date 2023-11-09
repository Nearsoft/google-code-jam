fun main(args: Array<String>) {
    //input cases
    val cases = readln().toInt();
    //inputing an Array of all the cases
    val casesArray: MutableList<String> = mutableListOf();
    for (i in 0 until cases) {
        val temp = readLine().toString();
        casesArray.add(temp);
    }
    //Print cases
    var case: Int;
    for (i in 0 until cases) {
        case = i + 1;
        print("Case #$case: ");
        //Calling method with the array of cases
        doubleOrOne(casesArray[i]); 
    }
}

fun doubleOrOne(word: String) {
    //Making 2 arrays, one for the letter and one for the number of times each letter appears to double it later
    val letter: MutableList<String> = mutableListOf();
    val count: MutableList<Int> = mutableListOf();
    //Counter to know how much times a letter is in the string (next to)
    var counter = 1;
    //Making a new variable for the String received as val cant be changed and var can. 
    //Adding a char that is bigger than all the letters so it can iterate tru all the String as I need to check for i+1
    var firstWord: String = word.plus("·");
    for (i in 0 until firstWord.length - 1) {
        if (firstWord[i] == firstWord[i+1]) {
            counter++;    //counter++ if 2 letters are the same next to each other
        }
        else {
            letter.add(firstWord[i].toString()); //adding the letter
            count.add(counter);                  //adding the counter
            counter = 1;                         //reset counter
        }
    }
    
    //new word that is gonna be created with all the double chars
    var newWord: String = "";
    //iterate tru all the letter variables saved
    for (i in 0 until letter.size - 1) {
        if (letter[i] < letter [i + 1]) {   //if the letter is lower repeat it by the times on count * 2
            repeat(count[i] * 2) { 
                newWord = newWord.plus(letter[i]);
            }
        } else {                            //else just add the count, w/o * 2
            repeat(count[i]) {
                newWord = newWord.plus(letter[i]);
            }
        }
    }
    //as the for is on size - 1 add last letter by the number of times on count
    newWord = newWord.plus(letter[letter.size - 1].repeat(count[count.size - 1]));
    //print final word
    print("$newWord\n");
}