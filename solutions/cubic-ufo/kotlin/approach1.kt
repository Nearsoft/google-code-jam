import java.io.File;
import java.util.Arrays
import kotlin.math.*

fun main(args: Array<String>) {
    val numInput = readLine()!!;
    val numAreas = numInput.toIntOrNull();
    val beta = Math.toDegrees(acos(1 / sqrt(3.0)));
    //cases loop
    if(numAreas != null) {
        for (i in 1 .. numAreas) {
            println("Case #" + i + ":");
            val areaInput = readLine()!!;
            val currentArea =areaInput.toDouble();
            val faceA = yRotation(doubleArrayOf(0.5, 0.0, 0.0));
            val faceB = yRotation(doubleArrayOf(0.0, 0.5, 0.0));
            val faceC = yRotation(doubleArrayOf(0.0, 0.0, 0.5));
            var theta = Math.toDegrees(acos(currentArea / sqrt(3.0)));
            theta = abs(theta - beta);
            val fa = xRotation(faceA, theta);
            println(fa[0].toString() + " " + fa[1].toString() + " " + fa[2].toString());
            val fb = xRotation(faceB, theta);
            println(fb[0].toString() + " " + fb[1].toString() + " " + fb[2].toString());
            val fc = xRotation(faceC, theta);
            println(fc[0].toString() + " " + fc[1].toString() + " " + fc[2].toString());
        }
    }
}

//Y-rotation Function
private fun yRotation(inputArray: DoubleArray): DoubleArray {
    val angle = Math.toRadians(45.0)
    val x = cos(angle) * inputArray[0] + sin(angle) * inputArray[2];
    val z =  -sin(angle)*inputArray[0] + cos(angle)*inputArray[2];
    inputArray[0] = x;
    inputArray[2] = z;
    return inputArray;
}

//X-rotation Function
private fun xRotation(inputArray: DoubleArray, theta: Double): DoubleArray {
    val angle = Math.toRadians(theta)
    val y =   cos(angle)*inputArray[1] + -sin(angle)*inputArray[2];
    val z =   sin(angle)*inputArray[1] + cos(angle)*inputArray[2];
    inputArray[1] = y;
    inputArray[2] = z;
    return inputArray;
}