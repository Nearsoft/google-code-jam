function SavingUniverse (){
    var T, D, P, PC;
    var results=new Array();
    T = prompt("How many tests?");
    var i = 0;
    while(i<T){
        var splitted = inputVal();
        D = splitted[0];
        P = splitted[1];
        PC = P.split("");
        var M = 0;
        var TA = countAttack(PC);
        var possible = true;
        while(D < TA){
            var temp1, temp2;
            var chSearch = 1;
            try{
                do{
                    temp1 = PC[PC.length - chSearch];
                    temp2 = PC[PC.length - chSearch - 1];
                    chSearch++;
                }while((temp1 != 'S' || temp2 == 'S'));
                chSearch--;
                PC[PC.length - chSearch] = temp2;
                PC[PC.length - chSearch - 1] = temp1;
                M++;
                TA = countAttack(PC);
            }catch(e){
                possible = false;
                break;
            }
        }
        if (possible) {
            results[i] = "Case #" + (i + 1) + ": " + M;
        }else{
            results[i] = "Case #" + (i + 1) + ": IMPOSSIBLE";
        }
        i++;
    }
        for (var r of results) {
            console.log(r);
        }
 }
 function inputVal(){
    do{
        var inp = prompt("Input shield power and attack pattern: ");
    }while(!(inp.match("\\d+[ ][S|C]+")))
    return inp.split(" ");
 }
 function countAttack(PC){
    var CA = 1, TA = 0
    for(var A of PC){
        if(A === 'S'){
            TA += CA
        }else{
            if(A == undefined){
                throw "IMPOSSIBLE";
            }else{
                CA = CA * 2;
            }
        }
    }
    console.log("Array: ")
    for(var A of PC){
        console.log(A)
    }
    console.log("\Damage: "+TA)
    return TA;
 }