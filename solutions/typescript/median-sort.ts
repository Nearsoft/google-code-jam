declare var require: any;
declare var process: any;

var readline = require('readline');
var rl = readline.createInterface(process.stdin, process.stdout);



const get_index = (arr: number[], num: number) => {
    let i: number = Math.ceil(((arr.length)*1/3) - 1);    
    let j: number = Math.ceil(((arr.length)*2/3)-1);    
    
    while (true) {
        console.log(arr[i], num, arr[j]);
        let m: number = parseInt(data.join(''));
        if (m == num) {
            if (j-i == 1){
                return j;
            }
            else {
                i = i+(j-i)*1/3;
                j = i+(j-i)*2/3;
            }
        }                            
        else if (m == arr[i]) {
            if (i == 0){
                return 0
            }
            else if (j-i == 1) {
                i = i -1;
                j = i;
            }
            else {
                i = i-(j-i)*2/3
                j = i-(j-i)*1/3
            }                           
        }
        else {  
            if (j == arr.length-1){
                return arr.length;
            }
            else if (j-i == 1) {
                i = j;
                j = j+1;
            }
            else {
                i = j+(j-i)*1/3
                j = j+(j-i)*2/3
            }
        }
        if (i == j){
            j += 1
        }
    };
};


const medianSort = (t: number,n: number) => {
    
    while (t>0) {
        console.log(1,2,3);
        let k = parseInt(data.join(''));
        let arr: number[] = [];
        if (k == 1) {
            arr = [2, 1, 3]
        }
        else if (k == 2) {
            arr = [1, 2, 3]
        }
        else { 
            arr = [1, 3, 2]
        }
        while (arr.length < n) {
            let nextIndex = arr.length + 1
            let placeIndex = get_index(arr, nextIndex)
            arr.splice(placeIndex,0, nextIndex)
        }
        
        
        console.log(arr.join(' '));
        let answer = parseInt(data.join(''));
        if (answer == 1) {
            console.log('correct');
        }
        if (answer == -1) {
            throw new Error('ERROR');
        }
        
        t--; 
    };
};

let data: string[] = [];
let t: number = 0;
let n: number = 0;

rl.on('line', (input: string) => {
    data = input.split(' ');
    t = parseInt(data[0]);
    n = parseInt(data[1]);
    medianSort(t,n)

}).on('close',function(){
    process.exit(0);
});