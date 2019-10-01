var readline = require('readline');

var rl = readline.createInterface({
    input: process.stdin,output: process.stdout,terminal: false});

var finalArray = [];

function troubleSort(array){
    let odd = [], even = [];
    let io = 0, ie = 0, len = array.length;

    for(let i = 0; i < array.length; i++){
        if (i%2 == 0){
            even.push(array[i]);
        }
        else{
            odd.push(array[i]);
        }
    }

    numberSort = function (a,b) {
        return a - b;
    };
    
    even.sort(numberSort);
    odd.sort(numberSort);
    
    for(let i = 0; i < len; i++){
        if( i%2 == 0 && ie < even.length ){
            array[i] = even[ie];
            ie++;
        }
        else if( io < odd.length ){
            array[i] = odd[io];
            io++;
        }
    }

    return array;

}

function checkTroubleSort(testCase){
    for(let i = 0; i < finalArray.length-1; i++){
        if (finalArray[i+1] < finalArray[i]){
            console.log("Case #" + testCase + ": " + i);
            return 0;
        }
    }

    console.log("Case #" + testCase + ": OK");
    return 0;
}

function getTestCases(t){
    let testCase = 0
    if (t >= 1 && t <= 100){
        rl.on("line",(input) => {

            input = input.split('\n');

            for(let i = 0; i < input.length; i++){
                if (input[i].length > 1){
                    testCase++;
                    let array = input[i].split(' ').map(Number);

                    if (array.length >= 0 && array.length <= Math.pow(10,9)){
                        finalArray = troubleSort(array);
                        checkTroubleSort(testCase);
                    }

                }
            }
        });
    }
}


function main (){
    rl.question("",(t) => {
        getTestCases(t);
    });
}

main();