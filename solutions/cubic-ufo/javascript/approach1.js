process.stdin.on('data', function (input) {
    var n = Number(input.toString().split('\n')[0]);
    var data = input.toString().split('\n').slice(1);

    for (var i = 1; i <= n; i++) {
        console.log("Case #" + i + ":");
        calculateShadow(data[i - 1]);
    }
});

function calculateShadow(area) {
    var theta;
    if (area <= Math.sqrt(2)) {
        theta = Math.asin(Math.pow(area, 2) - 1) / 2;
        console.log(0.5 + " " + 0 + " " + 0);
        console.log(0 + " " + 0.5 * Math.cos(theta) + " " + 0.5 * Math.sin(theta));
        console.log(0 + " " + -0.5 * Math.sin(theta) + " " + 0.5 * Math.cos(theta));
    } else {
        theta = Math.asin(area / Math.sqrt(3)) - Math.asin(Math.sqrt(2) / Math.sqrt(3));
        console.log(Math.cos(theta) / 2 + " " + Math.sin(theta) / 2 + " " + 0);
        console.log(-Math.sin(theta) / Math.sqrt(8) + " " + Math.cos(theta) / Math.sqrt(8) + " " + 1 / Math.sqrt(8));
        console.log(Math.sin(theta) / Math.sqrt(8) + " " + -Math.cos(theta) / Math.sqrt(8) + " " + 1 / Math.sqrt(8));
    }
}