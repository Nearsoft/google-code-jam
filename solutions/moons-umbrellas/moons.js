const readline = require('readline')
var rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

let ncaso = 0;
let cont = 1;

rl.on("line", function(line){
    if (ncaso > 0)
    {
        let vars, x, y, stri;
        vars= line.split(" ");
        x = parseInt(vars[0]);
        y = parseInt(vars[1]);
        stri = vars[2];
        /*stri = stri.replace(/\?/g,'')
        let respuesta= (stri.match(/CJ/g) || []).length*x
        + (stri.match(/JC/g) || []).length*y; */

        let n=stri.length;

        let arreglo = new Array(n).fill(100000).map( i => new Array(2).fill(100000) )

        const dimensions = [ arreglo.length, arreglo[0].length ];
        
        if(stri[0] != "C"){ //J o ?
            arreglo[0][1] = 0;
        }
        if(stri[0] != "J"){ //C o ?
            arreglo[0][0] = 0;
        }
       
    
        for(let i=1; i<n;i++){
            if (stri[i] != "C"){
                arreglo[i][1] = Math.min(arreglo[i][1], arreglo[i-1][1], arreglo[i-1][0]+x) 
            }
            if (stri[i] != "J"){
                arreglo[i][0] = Math.min(arreglo[i][0], arreglo[i-1][0], arreglo[i-1][1]+y)
            }
        }

        let respuesta = Math.min(arreglo[n-1][0], arreglo[n-1][1]);

        console.log("Case #" + cont+ ": " + respuesta);
        cont = cont+1
        
    }
    ncaso++
})