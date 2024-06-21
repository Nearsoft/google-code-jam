const readline = require('readline');
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
    });

// Function to update our dictionary of best possible shots
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
    var smallestDifference = a
    for (let l = 0; l < Math.floor(a); l++) {
        // We just keep the combination of 'n' and 'm' that multiplied together equal A and the difference between
        // 'n' and 'm' its the lowest
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


// 'T' its the number of test cases the tester will iterate
let T = 0;
rl.question('', answer => {
T = parseInt(answer);
var i = 0;

function do_process() {
    if (i >= T) {
        console.error(i)
        // Process exit will terminate iteration and begin with the next test case
        process.exit();
        return;
    }
    var targets = {}
    var n = 0
    var m = 0
    var dimentionsOfSquare = []
    // Make a list for the places that are already mark so we don't count them twice
    var alreadyPrepared = []
    
    process_case(
    (A) => {
        dimentionsOfSquare = findSquareDimentions(A, n, m)
        n = dimentionsOfSquare[0]
        m = dimentionsOfSquare[1]
        for (let h = 2; h < n; h++) {
            for (let k = 2; k < m; k++) {
                targets[[h,k]] = 9
            }
        }
    },
    () => {
        // Function to pick the shot where it has a higher chance to hit an unpainted tile within A
        var highestMissingSquares = 0
        var nextTarget = ""
        for (var key in targets) {
            if (targets[key] > highestMissingSquares) {
                nextTarget = key
                highestMissingSquares = targets[key]
            }
        }
        var targetX = parseInt(nextTarget.split(",")[0]);
        var targetY = parseInt(nextTarget.split(",")[1]);
        return [targetX, targetY]
    },
    (i, j) => {
        // Get best target from map
        // pickTarget()
        // result = calculateHit(targets, targetX, targetY) // random shot
        // console.log(targetX + " " + targetY)
        // result = await readLinePromise()
        // tiros++
        // x = parseInt(result.split(" ")[0])
        // y = parseInt(result.split(" ")[1])
        x = parseInt(i)
        y = parseInt(j)
        lastShot = [x.toString() + ' ' + y.toString()]
        if (!(alreadyPrepared.includes(lastShot.toString()))) { 
            // alreadyPrepared.push([x,y])
            alreadyPrepared.push(lastShot.toString())
            updateTargets(targets, x, y)
        }
    }, 
    do_process
    );
    i++
}

// Initiates Test case
do_process();
});



// Function to execute for every shot we are going to make
function process_case(select_dimension_callback, select_target_fn, process_result, done) {
    //This function loops all our shots until we cover al Area we are looking for
    function gopher_loop() {
        //Call func to select target to shoot
        const [i, j] = select_target_fn();
        //Shoot
        console.log(`${i} ${j}`)
        //Read Gopher's painted area
        rl.question('', line => {
        var ip = parseInt(line.split(' ')[0])
        var jp = parseInt(line.split(' ')[1])
        //If '0 0' then we are done with this test case.
        if (ip === 0 && jp === 0) {
            done();
            return;
        }
        process_result(ip, jp);
        gopher_loop();
        }); 
    }


    // Asks question to retrieve Area of the field to paint
    rl.question('', A => {
        select_dimension_callback(parseInt(A));
        gopher_loop();
    })
}
