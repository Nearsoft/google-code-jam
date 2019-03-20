var rl = require('readline');

var prompts = rl.createInterface(process.stdin, process.stdout);

function Sort(list) 
{
    var done = false;
    while(!done)
    {
        done = true;
        for(var i = 0; i< (list.length - 2); i++)
        {
            if (list[i] > list[i+2])
            {
                done = false;
                var aux = list[i];
                list[i] = list[i + 2];
                list[i + 2] = aux;
            }
        };
    };
    return list;
};

var checkCount = 1;
function Check(list)
{
    var error = false;
    for(var i = 0; i < list.length - 1; i++)
    {
        if(list[i] > list[i+1])
        {
            error = true;
            console.log("Case #" + checkCount + ": " + i);
            break;
        }
    }
    if (!error)
    {
        console.log("Case #" + checkCount + ": " +"OK"); 
    }
    
    checkCount++;
};

let numberCases;
const howManyCases = () => {
    return new Promise((resolve, reject) =>{
        prompts.question("how many cases?:", (manyCases) => {
            numberCases = manyCases;
            resolve();
        })
    })
};
let arrayLen;
const howLong = () => {
    return new Promise((resolve, reject) =>{
        prompts.question("how long?:", (lenArray) => {
            arrayLen = lenArray;
            resolve();
        })
    })
};

const main = async () =>{
    await howManyCases();
    console.log(numberCases);
    for(let i = 0; i < numberCases; i++){
        await howLong();
        let finalArray = [];
        for(let j = 0; j < arrayLen; j++){
            
            let arrayValue;
            const readValue = () =>{
                return new Promise((resolve, reject) =>{
                    prompts.question("Value: ", value =>{
                        arrayValue = value;
                        resolve();
                    })
                })
            }
            await readValue();
            finalArray.push(arrayValue);
        };
        Check(Sort(finalArray));
    };
    prompts.close();
}

main();