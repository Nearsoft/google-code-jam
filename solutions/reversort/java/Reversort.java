import java.io.InputStream;
import java.util.Scanner;

public class Reversort {

    /**
     * Esta funcion invierte los valores del array
     * 
     * @param actualPosition Primer elemento de la sublista
     * @param cost           El tamano de la sublista
     * @param numberArray    Lista de los numeros
     */
    public static void reverseArray(int actualPosition, int cost, int[] numberArray) {
        for (int i = 0; i < cost / 2; i++) {
            // Se guarda el primer elemento de la sublista
            int temp = numberArray[i + actualPosition];
            // El valor del primer elemento de la sublista se cambia por el ultimo
            numberArray[i + actualPosition] = numberArray[cost - i - 1 + actualPosition];
            // El valor del ultimo elemento de la sublista se cambia por el primero
            numberArray[cost - i - 1 + actualPosition] = temp;
        }
    }

    /**
     * Funcion que busca la posicion del menor numero dentro del array
     * 
     * @param actualPosition Posicion actual en el array
     * @param numberArray    Lista de los numeros
     * @return Regresa la posicion en el array del menor numero
     */
    public static int findMinNumberPosition(int actualPosition, int[] numberArray) {
        int minNumberPosition = actualPosition;
        for (int i = actualPosition; i < numberArray.length; i++) {
            // Si el valor del menor numero actual es mayor, se cambia
            if (numberArray[minNumberPosition] > numberArray[i]) {
                minNumberPosition = i;
            }
        }

        return minNumberPosition;
    }

    /**
     * Funcion que calcula el costo total de cada iteracion
     * 
     * @param numberArray Lista de los numeros
     * @return Regresa el costo total
     */
    public static int findCost(int[] numberArray) {
        int totalCost = 0;
        for (int actualPosition = 0; actualPosition < numberArray.length - 1; actualPosition++) {
            int minNumberPosition = findMinNumberPosition(actualPosition, numberArray);
            int cost = minNumberPosition - actualPosition + 1;
            reverseArray(actualPosition, cost, numberArray);
            totalCost += cost;
        }

        return totalCost;
    }

    /**
     * Ingresa los test manualmente, primero el numero de tests, despues el tamano
     * del test y por ultimo los valores del test
     * 
     * @param args
     */
    public static void main(String[] args) {
        InputStream is = System.in;
        try (Scanner scanner = new Scanner(is)) {
            int totalTests = scanner.nextInt();
            for (int testCase = 1; testCase <= totalTests; testCase++) {
                int testSize = scanner.nextInt();
                int[] test = new int[testSize];
                for (int i = 0; i < testSize; i++) {
                    test[i] = scanner.nextInt();
                }
                int cost = findCost(test);
                System.out.println("Case #" + testCase + ": " + cost);
            }
        }
    }

}