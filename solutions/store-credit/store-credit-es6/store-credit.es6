
"use strict"
var fs  = require("fs");

let case_number = 0;
let current_data = [];
let array_size = 0;
let item1, item2, credit, prices, output_items,aux;

let getCaseValor = (case_data) => {
    credit = parseInt(case_data[0]);
    prices = case_data[2].split(" ").map(Number);
    prices.some((priceFirstItem,i) =>{
        item1 = i;
        prices.some((priceSecondItem, k) => {
            if(i!==k){
              if(priceFirstItem+priceSecondItem === credit){
                  output_items = (i < k) ?  (i+1)+ ' ' + (k+1) : (k+1) + ' ' + (i+1)
                  return true;
              }
            };
        });
    });
    console.log('Case #' + case_number + ': ' + output_items)
    current_data = [];
    case_number++
};

fs.readFileSync('../../../../codejamcases/testcasesstorecredit.txt').toString().split('\n').forEach(line => {

      if(line !== "" && case_number === 0){
          case_number++;
      }else{
          current_data.push(line);
          (current_data.length === 3) ? getCaseValor(current_data) : null;
      };

  });
