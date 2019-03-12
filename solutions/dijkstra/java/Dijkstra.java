import java.util.HashMap;
import java.util.List;

public class Dijkstra {
    private HashMap<String,String> multiplicationMatrix;

    public Dijkstra(){
        multiplicationMatrix = new HashMap<>();
        //Line 1 from the matrix
        multiplicationMatrix.put("11","1");
        multiplicationMatrix.put("1i","i");
        multiplicationMatrix.put("1j","j");
        multiplicationMatrix.put("1k","k");
        //Line i from the matrix
        multiplicationMatrix.put("i1","i");
        multiplicationMatrix.put("ii","-1");
        multiplicationMatrix.put("ij","k");
        multiplicationMatrix.put("ik","-j");
        //Line j from the matrix
        multiplicationMatrix.put("j1","j");
        multiplicationMatrix.put("ji","-k");
        multiplicationMatrix.put("jj","-1");
        multiplicationMatrix.put("jk","i");
        //Line k from the matrix
        multiplicationMatrix.put("k1","k");
        multiplicationMatrix.put("ki","j");
        multiplicationMatrix.put("kj","-i");
        multiplicationMatrix.put("kk","-1");
    }

    /**
     * multiply a string x times to increment his length
     * @param l the string to be multiplied
     * @param x the number of times to multiply the string
     * @return the string multiplied
     */
     private String getLX(String l, long x){
         StringBuilder lx = new StringBuilder(l);
         if(x > 24){
             x = x%4 + 24;
         }
         for(int i=1;i<x;i++){
             lx.append(l);
         }
         return lx.toString();
     }

    /**
     * Evaluate a string to see if it can be converted to "ijk"
     * @param testCase the string to be converted
     * @return YES if is possible to convert the string and NO if it's not possible
     */
    private String dijkstra(String testCase){
        String weExpect = "ijk";
        int i = 0;
        while(!testCase.isEmpty()){
            if(i<3 && testCase.charAt(0) == weExpect.charAt(i)){
                i++;
                testCase=testCase.substring(1);
            }else{
                if(testCase.length() >= 2){
                    if(testCase.length() == 2 && testCase.charAt(0) == '-'){
                        break;
                    }
                    String xy = getFirstElements(testCase);
                    testCase = testCase.replaceFirst(xy,getMultiplication(xy));
                }else{
                    if(testCase.equals("1")){
                        testCase = "";
                    }
                    break;
                }
            }
        }
        if(i == 3 && testCase.isEmpty()){
            return "YES";
        }
        return "NO";
    }

    /**
     * gets the first 2 elements of the string even if one is negative
     * @param value the string of elements
     * @return the first 2 elements
     */
    private String getFirstElements(String value){
        if(value.charAt(0) == '-'){
            return value.substring(0,3);
        }
        return value.substring(0,2);
    }

    /**
     * get the result of the multiplication of 2 elements from the matrix
     * @param xy the 2 elements to be multiplied
     * @return the result of the multiplication (value stored in the matrix)
     */
    private String getMultiplication(String xy){
        boolean negative = false;
        if(xy.length()==3){
            xy = xy.substring(1);
            negative = true;
        }
        String res = multiplicationMatrix.get(xy);
        if(negative){
            return negate(res);
        }
        return res;
    }

    /**
     * return the opposite value of a string
     * @param value the value to be negated
     * @return the negation of the value
     */
    private String negate(String value){
        if(value.charAt(0) == '-'){
            return value.substring(1);
        }
        return "-"+value;
    }

    /**
     * evaluate the input for all the cases
     * @param input the list of strings containing the test cases
     * @return a string with the result for all the cases
     */
    public String evaluateInput(List<String> input){
        String result = "";
        try{
            long t = Long.parseLong(input.remove(0).trim()); // t = Total test cases
            if(input.size() == (t*2)){ // check if the number of test cases given is equal to t
                for(int i=0;i<(t*2);i+=2){
                    String lx[] = input.get(i).split(" ");
                    long l = Long.parseLong(lx[0]); // size of the string
                    long x = Long.parseLong(lx[1]); // number of repetitions for the string
                    if(l == input.get(i+1).length()){ // check if the size of the test case is l
                        result += "Case #"+((i/2)+1)+": "+dijkstra(getLX(input.get(i+1),x))+"\n";
                    }else{
                        System.out.println("Error: the test case is not the size that was given");
                    }
                }
            }else{
                System.out.println("Error: The number of test cases given is wrong");
            }
        }catch (NumberFormatException e){
            System.out.println(e.getMessage());
        }
        return result;
    }


    /**
     * The main function of this program
     * @param args
     */
    public static void main(String args[]){

        List<String> input = FileManagement.readFile("in/");
        Dijkstra dij = new Dijkstra();
        String result = dij.evaluateInput(input);
        System.out.println(result);
        FileManagement.saveFile(result,"src/output/result.txt");

    }
}
