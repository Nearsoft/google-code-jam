process.stdin.resume();
process.stdin.on("data", function(data) {
    var arrayLines = data.toString().split("\n");
    var numberCases = arrayLines[0]
    var arrayAreas = arrayLines.slice(1,numberCases+1)
    var area
    for( var index = 0; index < numberCases ; index++) {
        area = Number(arrayAreas[index]);
        var theta = Math.asin(area/Math.sqrt(3)) - Math.asin(1/Math.sqrt(3))
        var pointA = 0.5*Math.sin(Math.PI/4) + " " + 0.5*Math.sin(Math.PI/4)*Math.sin(theta) + " " + -0.5*Math.sin(Math.PI/4)*Math.cos(theta)
        var pointB = 0 + " " + 0.5*Math.cos(theta) + " " + 0.5*Math.sin(theta)
        var pointC = 0.5*Math.sin(Math.PI/4)  + " " + -0.5*Math.sin(Math.PI/4)*Math.sin(theta) + " " + 0.5*Math.sin(Math.PI/4)*Math.cos(theta)
        console.log('Case #' + (Number(index)+1) + ':')
        console.log(pointA)
        console.log(pointB)
        console.log(pointC)
    }
})