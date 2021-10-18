const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split('\n');

/* Function: Current Line

   This function is helpful to read the input from
   the console.

   Parameters:

      None

   Returns:

      Line of input from the console.

*/
let currentLine = 0;
function readLine() {                           //READ INPUT FUNCTION
    return input[currentLine++];
}


/* Function: Get Reversort

   This function generates a list of 'n' elements that 
   will have a cost 'c' to reversort.

   Parameters:

      n - Number of elements in the output list.
      c- The desired cost of reversorting the 
      generated list.

   Returns:

      The reversed list of reversorted elements.

*/
function getReversort (n, c) {                  //REVERSORT FUNCTION
    c -= n-1;
    if (c<0 || (Math.floor(n*(n-1)/2)) < c) {   //Boundaries for function
        return [];
    }

    var numList = Array.from({length: n}, (_,i) => i+1);        //Generate list from 1 to n

    for (let i = n-2; i>-1; i--) {                              //Iterate from n-2 to 0
        if ( c >= n - i - 1) {
            listToReverse = numList.slice(i, numList.length);   //Take a slice from the array
            listToReverse.reverse();                            //Reverse it
            Array.prototype.splice.apply(numList, [i,
            listToReverse.length].concat(listToReverse));       //Insert it back
            c -= n - i - 1;
        } else {
            j = c + i + 1;                                      //Modify cost value
            listToReverse = numList.slice(i, j);
            listToReverse.reverse();
            Array.prototype.splice.apply(numList, [i,
            listToReverse.length].concat(listToReverse));
            break;
        }
    }

    return numList;

}

//Receive first input: number of times the main loop with iterate
let num_cases = parseInt(readLine(), 10);

/*
MAIN LOOP
*/
for (let i = 1; i<num_cases+1; i++) {
    
    let n, c, result, numList; 
    
    [n, c] = readLine().split(/\s+/);               //Receive input for n and c
    
    n = parseInt(n, 10);                            //Change from str to int
    c = parseInt(c, 10);

    numList = getReversort(n, c);                   //Run main function

    if (numList.length === 0) {                     //Select the value of result
        result = "IMPOSSIBLE";
    } else {
        result = numList.join(" ");
    }

    console.log('Case #%d: %s', i, result);         //Log result
}