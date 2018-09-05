const fs = require('fs');

const rr = fs.createReadStream('input.txt');
var i = 0;
var numberOfInputs = 0;
var elements = 0;
var originalNumberString = "";
var results = "";
rr.on('readable', () => {
    if(i == 0) {
        elements = rr.read().toString().split("\n");
        numberOfInputs = parseInt(elements[0]);

        for (var j = 1; j <= numberOfInputs; j++) {
            originalNumberString = elements[j];
            let originalNumberChar = [];
            let originalNumber = "";

            for (let i = 0; i < originalNumberString.length; i++) {
                originalNumberChar.push(originalNumberString.charAt(i));
            }

            for (let i = originalNumberString.length - 1; i > 0; i--) {
                if (parseInt(originalNumberChar[i]) < parseInt(originalNumberChar[i - 1])) {
                    originalNumberChar[i - 1] = (parseInt(originalNumberChar[i - 1]) - 1).toString();
                    for (let j = i; j < originalNumberString.length; j++) {
                        originalNumberChar[j] = '9';
                    }
                }
            }

            for (let i = 0; i < originalNumberString.length; i++) {
                if (originalNumberChar[i] != '0')
                    originalNumber += originalNumberChar[i];
            }

            results += "Case #" + j + ": " + originalNumber+"\n";
        }
        fs.writeFile("results.txt",results,(err)=>{
           if(err){
               return console.log(err);
           }
           console.log("The file was saved!");
        });
        i++;
    }
});




// Se obtiene el numero
// let numberOfInputs = parseInt(window.prompt("", ""));
// let originalNumbersString = [];
// for (let i = 0; i < numberOfInputs; i++) {
//     originalNumbersString.push(window.prompt("", "").toString());
// }

// originalNumbersString.forEach((originalNumberString, index) => {
//
//
//
//     let originalNumberChar = [];
//     let originalNumber = "";
//
//     for (let i = 0; i < originalNumberString.length; i++) {
//         originalNumberChar.push(originalNumberString.charAt(i));
//     }
//
//     for (let i = originalNumberString.length - 1; i > 0; i--) {
//         if (parseInt(originalNumberChar[i]) < parseInt(originalNumberChar[i - 1])) {
//             originalNumberChar[i - 1] = (parseInt(originalNumberChar[i - 1]) - 1).toString();
//             for (let j = i; j < originalNumberString.length; j++) {
//                 originalNumberChar[j] = '9';
//             }
//         }
//     }
//
//     for (let i = 0; i < originalNumberString.length; i++) {
//         if (originalNumberChar[i] != '0')
//             originalNumber += originalNumberChar[i];
//     }
//
//     console.log("Case #" + (index + 1) + ": " + originalNumber);
// });

