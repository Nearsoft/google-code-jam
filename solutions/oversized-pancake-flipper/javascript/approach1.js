let fs = require('fs');

let contents = fs.readFileSync('largeInput.txt', 'utf8').toString().split('\n');

let output = '';

for(let i = 1; i < contents.length-1; i++){
    console.log('Case#'+ i + ': ' + solver(contents[i]));
    output += 'Case#'+ i + ': ' + solver(contents[i]) + '\n'
}
fs.writeFileSync('output.txt',output);



function getK(xcase){
    let k = '';
    for(i in xcase){
        if(xcase[i]==' '){
            i++;
            while(i <= (xcase.length-1)){
                k+= xcase[i]
                i++;
            }
        }
    }
    return k;
}

function solver(xcase){
    let k = Number(getK(xcase));
    let s = Number(xcase.length) - Number(k.toString.length+1);

    let posicion = 0;
    let steps = 0;

    let values = [];

    for(let i = 0; i < s; i++){
        values.push(xcase[i]);
    }
    while(k + posicion <= s){
        if(values[posicion] == '+'){
            posicion++;
            continue;
        }
        if(values[posicion] == '-'){
            for(let i = 0;i < k; i++){
                if(values[posicion+i] == '-'){
                    values[posicion+i] = '+';
                }else if(values[posicion+i] == '+'){
                    values[posicion+i] = '-';
                }
            }
            //console.log(values);
            posicion++;
            steps++;
        }
    }

    for(let i =0; i < s; i++){
        if(values[i] == '-')return "IMPOSSIBLE";
    }

    return steps;
}
