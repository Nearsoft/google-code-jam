import javafx.util.Pair;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {

    //  private final GenericUserBasedRecommender recommender;
    int numberOfReviews;

    static int numberOfCases;
    static int gridSize;
    static int modelsInGrid;
    static boolean legal = false;
    static int fashionPoints;
    static int modifications;

    static String[][] grid;
    static List<Pair<Integer, Integer>> results;
    static List<String> figures;
    static List<Integer> rows;
    static List<Integer> collumns;
    static List<Integer> diagPos;
    static List<Integer> diagNeg;
    static List<List<Pair<Integer, Integer>>> freeDiag;
    static List<Integer> rangeList = new ArrayList<Integer>();

    static String outputFilePath = "output2.out";

    public static void main(String[] args) {

        File file = new File("LargeInput.in");

        Scanner scan = null;
        try {
            scan = new Scanner(file); // Create scanner
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        numberOfCases = scan.nextInt();

        try {
            eachCase(scan);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static List<Integer> range(int min, int max) {
        rangeList = new ArrayList<Integer>();
        for (int i = min; i <= max; i++) {
            rangeList.add(i);
        }
        return rangeList;
    }

    private static void eachCase(Scanner scan) throws Exception {

        BufferedWriter bufferedWriter = null;
        bufferedWriter = new BufferedWriter(new FileWriter(outputFilePath));
        for (int j = 0; j < numberOfCases; j++) {
            fashionPoints = 0;
            modifications = 0;
            results = new ArrayList();
            gridSize = scan.nextInt();
            modelsInGrid = scan.nextInt();
            rows = range(0, gridSize - 1);
            collumns = range(0, gridSize - 1);
            diagPos = range(0, (2 * gridSize) - 2);
            diagNeg = range(-(gridSize - 1), (gridSize - 1));

            fillGrid(scan);

            List<List<Integer>> zip = zip(rows, collumns);
            int R, C;
            for (List<Integer> pair : zip) {
                R = pair.get(0);
                C = pair.get(1);
                if (grid[R][C].equals("+")) {
                    grid[R][C] = "o";

                    results.add(new Pair<Integer, Integer>(R, C));
                } else if (grid[R][C].equals(".") ) {
                    grid[R][C] = "x";

                    results.add(new Pair<Integer, Integer>(R, C));
                }
            }

            freeDiag = new ArrayList<List<Pair<Integer, Integer>>>();
            List<Pair<Integer, Integer>> actualList;
            Pair<Integer, Integer> pair;
            for (int i = 0; i < gridSize; i++) {
                for (int k = 0; k < gridSize; k++) {
                    if (freeDiag.size() - 1 >= (i + k)) {
                        actualList = freeDiag.get(i + k);
                        pair = new Pair<Integer, Integer>(i, k);
                        actualList.add(pair);
                        freeDiag.set(i + k, actualList);
                    } else {
                        actualList = new ArrayList<Pair<Integer, Integer>>();
                        pair = new Pair<Integer, Integer>(i, k);
                        actualList.add(pair);
                        freeDiag.add(actualList);
                    }
                }
            }
            List<Integer> sortedIndex = sortFreeDiag();

            for (int index = 0; index < sortedIndex.size(); index++) {
                int i = sortedIndex.get(index);
                int length = freeDiag.get(i).size() - 1;
                if (freeDiag.get(i).isEmpty() ) {
                    continue;
                }
                for (int k = length; k >= 0; k--) {
                    pair = freeDiag.get(i).get(k);
                    R = pair.getKey();
                    C = pair.getValue();
                    if (diagNeg.contains(R-C) && diagPos.contains(R+C)) {
                        if (grid[R][C].equals("x")) {
                            grid[R][C] = "o";
                            if (!arrayContains(R,C)) {
                                results.add(new Pair<Integer,Integer>(R, C));
                            }
                        } else if (grid[R][C].equals(".")) {
                            grid[R][C] = "+";
                            results.add(new Pair<Integer,Integer>(R, C));
                        }
                        removeElementFromList(diagPos, R + C);
                        removeElementFromList(diagNeg, R - C);
                        break;
                    }
                }

            }

            for (int row = 0; row < gridSize; row++) {
                for (int col = 0; col < gridSize; col++) {
                    String cell = grid[row][col];
                    if (cell.equals("x") || cell.equals("+")) {
                        fashionPoints = fashionPoints + 1;
                    } else if (cell.equals("o")) {
                        fashionPoints = fashionPoints + 2;
                    }
                }
            }
            List<String> result = new ArrayList();
            System.out.println("Case:"+(j+1));
            result.add("Case #" + (j + 1) + ": " + fashionPoints + " " + results.size() + "\n");
            bufferedWriter.write("Case #" + (j + 1) + ": " + fashionPoints + " " + results.size() + "\n");
            for (Pair<Integer, Integer> rs : results) {
                int row = rs.getKey();
                int col = rs.getValue();
                result.add(grid[row][col] + " " + (col + 1) + " " + (row + 1) + "\n");
                bufferedWriter.write(grid[row][col] + " " + (row + 1) + " " + (col + 1) + "\n");
            }

            for (String rs : result) {
                System.out.println(rs);
            }
        }
        bufferedWriter.close();
    }

    private static boolean arrayContains(int r, int c) {

       int x,y;
        for (Pair<Integer,Integer> par: results ) {
            x=par.getKey();
            y=par.getValue();
            if(x == r && y== c){
                return true;
            }
        }
        return false;
    }

    private static List<Integer> sortFreeDiag() {
        int len = freeDiag.size();
        List<Integer> index = range(0, len - 1);
        Collections.sort(index, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                int A = freeDiag.get(a).size();
                int B = freeDiag.get(b).size();
                if (A < B) {
                    return -1;
                } else if (A > B) {
                    return 1;
                } else { // a must be equal to b
                    if (a < b) {
                        return -1;
                    } else if (a > b) {
                        return 1;
                    }
                }
                return 0;
            }
        });
        return index;
    }


    private static void fillGrid(Scanner scan) {
        grid = new String[gridSize][gridSize];
        for (String[] row : grid) {
            Arrays.fill(row, ".");
        }
        int R;
        int C;
        for (int i = 0; i < modelsInGrid; i++) {
            String figure = scan.next();
            R = scan.nextInt() - 1;
            C = scan.nextInt() - 1;
            grid[R][C] = figure;
            if ( figure.equals("x") || figure.equals("o")) {
                removeElementFromList(rows,R );
                removeElementFromList(collumns,C);
            }
            if (figure.equals("+") || figure.equals("o")) {
                removeElementFromList(diagPos,R + C);
                removeElementFromList(diagNeg,R - C);
            }
        }
    }

    public static <T> List<List<T>> zip(List<T>... lists) {
        List<List<T>> zipped = new ArrayList<List<T>>();
        for (List<T> list : lists) {
            for (int i = 0, listSize = list.size(); i < listSize; i++) {
                List<T> list2;
                if (i >= zipped.size()) {
                    zipped.add(list2 = new ArrayList<T>());
                } else {
                    list2 = zipped.get(i);
                }
                list2.add(list.get(i));
            }
        }
        return zipped;
    }


    static void printMatrix(List<List<int[]>> list) {
        for (List<int[]> num : list) {
            for (int[] arr : num) {
                for (int val : arr) {
                    System.out.print(val + " ");
                }
            }
            System.out.print(", ");
        }
    }

    static void removeElementFromList(List<Integer> list, int elem) {
//    return list.remove(s -> s == elem);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            int value = iterator.next();
            if (value == elem) {
                iterator.remove();
                break;
            }
        }
    }
}