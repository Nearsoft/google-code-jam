import java.util.*;
import java.io.*;

public class OminousOmino {
    public OminousOmino(){
    }

    public String evaluateInput(List<String> input){
        String result = "";
        try {
            int t = Integer.parseInt(input.remove(0));  // t = total cases
            if(t>=1 && t<=100){                         //verify that the input is on the limit of the program
                if(t == input.size()){                  //verify that the number of test cases given is equal to input size
                    for(int i=0; i<t; i++){
                        String testCase[] = input.get(i).split(" ");
                        result+="Case #"+(i+1)+": "+evaluateCase(testCase)+"\n";
                    }
                }else{
                    System.out.println("Error: the number of test cases given doesn't match with the number received");
                }
            }else{
                System.out.println("Error: this input exceed the number of test cases accepted");
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    /**
     *
     * @param testCase string line that has the values to evaluate
     * @return name of winner
     */
    private String evaluateCase(String[] testCase){
        String winner = "GABRIEL";
        int xomino=0,rows,columns, area, xominoMaxRows,xominoMaxColumns;

        try{
            xomino = Integer.parseInt(testCase[0]);
            rows = Integer.parseInt(testCase[1]);
            columns = Integer.parseInt(testCase[2]);
            area = rows*columns;
            if (xomino>6 || xomino > area || area%xomino >0 || perfectPieceExists(xomino,rows,columns) ) {
                winner = "RICHARD";
            }
        }catch(NumberFormatException e){
            System.out.println(e.getMessage());
        }


        return winner;
    }

    /**
     * find if there is a piece that guarantees is impossible to fill the area
     * @param xomino omino number
     * @param rows rows in area
     * @param columns columns in area
     * @return
     */
    private boolean perfectPieceExists(int xomino,int rows,int columns){
        int length=xomino,height=1;

        //special cases for omino 4 that always block the area to be filled
        if(xomino==4) {
            if(rows == 2 || columns == 2){
                return true;
            }
        }
        //special cases for omino 5 that always block the area to be filled
        if(xomino==5) {
            if(rows == 2 || columns == 2 || rows == 3 && columns ==5 || rows == 5 && columns ==3 ){
                return true;
            }
        }
        //special cases for omino 6 that always block the area to be filled
        if(xomino==6) {
            if(rows == 2 || columns == 2 || rows == 3 || columns == 3){
                return true;
            }
        }
        while(length>=height){
            if(length<=rows && height<=columns || height<=rows && length<=columns) {
                length--;
                height++;
                continue;
            }
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        OminousOmino ovation = new OminousOmino();
        List<String> input = FileManager.readFile("input/");
        String result = ovation.evaluateInput(input);
        System.out.println(result);
        FileManager.saveFile(result,"output/result.txt");
    }
}
