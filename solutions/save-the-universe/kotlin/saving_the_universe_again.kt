import kotlin.math.pow
import kotlin.system.exitProcess
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*


fun main(args : Array<String>) {

    val `in` = Scanner(BufferedReader(InputStreamReader(System.`in`)))
    val t = `in`.nextLine().toInt();

        if (t < 1 || t > 100) {
            println("Number of cases excedes limits");
            exitProcess(0);
        } else {
            for (i in 1..t) {
                var input = `in`.nextLine();
                var solution = solveCase(input);
                if (solution >= 0) {
                    println("CASE #$i: $solution")
                } else {
                    println("CASE #$i: IMPOSSIBLE");
                }
            }
        }
}

fun solveCase(shieldAndRobotProgram: String) : Int {

    val regex = Regex(pattern = "^\\d+ [S|C]+\$");
    val matched = regex.containsMatchIn(input = shieldAndRobotProgram);


    if (!matched) {
        println("Alien's program has an incorrect format");
        exitProcess(0);
    }

    val d = shieldAndRobotProgram.split(' ') [0].toInt();
    if(d < 1 || d > 10.0.pow(9.0)){
        println("Shield's defence too high");
        exitProcess(0);
    }

    var p = shieldAndRobotProgram.split(' ') [1];
    if(p.length < 2 || p.length > 30) {
        println("Alien's program too long");
        exitProcess(0);
    }


    var charp = p.toCharArray();
    var hacks = 0;
    do {
        val damage = damage_calc(charp);
        if (damage <= d) {
            return hacks;
        }
        hacks++;
    }while(hack(charp));
    return -1;


}
// Calculates the damage of a robot program
fun damage_calc(program: CharArray):Int {
    var damage = 1;
    var total_damage = 0;
    for (letter in program) {
        if(letter == 'S') {
            total_damage += damage;
        } else {
            damage *= 2;
        }
    }
    return total_damage;
}

// Evaluates if hack can be made
fun hack(program: CharArray) : Boolean {
    val size = program.size - 1;
    for (i in size downTo 1) {
        if (program[i] == 'S' && program[i - 1] == 'C') {
            var aux = program[i];
            program[i] = program[i - 1];
            program[i - 1] = aux;
            return true;
        }
    }
    return false;
}