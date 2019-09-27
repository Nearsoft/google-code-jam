var random = Math.random()
const readline = require('readline');
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});
function calculateHit(targets, x, y){
    var done = true
    for (var key in targets) {
        if (targets[key] > 0) {
            done = false
        }
    }
    if (done) {
        return "0 0"
    }

    var myX = Math.floor((Math.random() * 3) + (x-1))
    var myY = Math.floor((Math.random() * 3) + (y-1))
    return myX.toString() + " " + myY.toString()
    
}

// function randomIntFromInterval(min, max) { // min and max included
//     return Math.floor(Math.random() * (max - min + 1) + min);
// }


    

function updateTargets(targets, x, y){

    var keys = Object.keys(targets)

    for (var target in keys) {
        tX = parseInt(keys[target].split(",")[0])
        tY = parseInt(keys[target].split(",")[1])
        if ((tX - 1) <= x && x <= (tX + 1) && (tY - 1) <= y && y <= (tY+1)) {
            targets[keys[target]]--
            
        }
    }
}
// # Finds the optimal dimentions for a rectangle that can fit 'a' inside
function findSquareDimentions(a, n, m){
    var smallestDifference = a

    for (let l = 0; l < Math.floor(a); l++) {

        if(a % l === 0 && Math.abs(l-Math.floor(a/l)) < smallestDifference){
            smallestDifference = Math.abs(l-Math.floor(a/l))
            n = l
            m = Math.floor(a / l)
        }
    }
    if(n < 3 || m < 3){
        return findSquareDimentions(a+1, n, m)
    }
    return [n,m]
}
async function main(){
    // var blabla = await readLinePromise()
    // console.log(blabla)
    var t = await readLinePromise()
    // console.log('t is: ' + t)
    t = parseInt(t)
    var total = 0
    for (let i = 0; i < t; i++) {
        var tiros = 0
        var a = parseInt(await readLinePromise())
        // console.log('a is: ' + a)
        var n
        var m
        var dimentionsOfSquare = []
        dimentionsOfSquare = findSquareDimentions(a, n, m)
        n = dimentionsOfSquare[0]
        m = dimentionsOfSquare[1]
        // # Create a map of the places where we can deploy the gopher
        // # and assign them a starting scores of the number of squares
        // # that haven't been marked yet (9)
        // # For example, for a = 16 we would get the dimensions of x=4 and y=4
        // #   1 2 3 4 5 6
        // # 1|0 0 0 0 0 0
        // # 2|0 x x x 0 0 
        // # 3|0 x x x 0 0 
        // # 4|0 0 0 0 0 0 
        // # 5|0 0 0 0 0 0
    
        // # the places where 'x' it's the only places we would want to shoot, notice how the 0's
        // # surrounding the x's form the 4x4 square that we want to fill up
    
    
        var targets = {}
        for (let h = 2; h < n; h++) {
    
            for (let k = 2; k < m; k++) {
    
                targets[[h,k]] = 9
            }
        }
        var x = -1
        var y = -1
        
        // # Make a list for the places that are already mark so we don't count them twice
        var alreadyPrepared = []
        // Start iterating, shooting and updating the map, until we get 0 0 which signals the end of the test case
        while (!(x === 0 && y === 0)) {
            // Get best target from map
            var highestMissingSquares = 0
            var nextTarget  
                      
            for (var key in targets) {
    
                if (targets[key] > highestMissingSquares) {
    
                    nextTarget = key
                    highestMissingSquares = targets[key]
                }
            }
    
            
            var result // '1 1'
            var targetX = nextTarget.split(",")[0]
            var targetY = nextTarget.split(",")[1]
            // result = calculateHit(targets, targetX, targetY) // random shot
            console.log(targetX + " " + targetY)
            result = await readLinePromise()
            tiros++
            x = parseInt(result.split(" ")[0])
            y = parseInt(result.split(" ")[1])
            lastShot = [x.toString() + ' ' + y.toString()]
    
    
    
            if (!(alreadyPrepared.includes(lastShot.toString()))) { 
                // alreadyPrepared.push([x,y])
                alreadyPrepared.push(lastShot.toString())
                updateTargets(targets, x, y)
            }
    
        }
        total+=tiros
        // console.log('Done!')
    
    
    
            
    }
    // console.log(total/t)
    // console.log('finished.')
    process.exit()
}




function readLinePromise(){
    return new Promise(function(resolve, reject){
        rl.question("", function(answer){
            resolve(answer);
        });
    });
}




main();