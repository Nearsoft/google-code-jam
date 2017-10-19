import java.util.*;
import java.io.*;

public class OminousOmino {
    public OminousOmino(){
    }

    public String evaluateInput(List<String> input){
        String result = "";
        try {
            int t = Integer.parseInt(input.remove(0)); // t = total cases
            if(t>=1 && t<=100){ //verify that the input is on the limit of the program
                if(t == input.size()){ //verify that the number of test cases given is equal to the t
                    for(int i=0; i<t; i++){
                        String testCase[] = input.get(i).split(" ");
                        result+="Case #"+(i+1)+": "+evaluateCase(testCase)+"\n";
                    }
                }else{
                    System.out.println("Error: the number of test cases given doesn't match with the number received");
                }
            }else{ // if t is off the limits then we put an error
                System.out.println("Error: this input exceed the number of test cases accepted");
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    private String evaluateCase(String[] testCase){
        String winner = "GABRIEL";
        int xomino=0,rows,columns, area, xominoMaxRows,xominoMaxColumns;

        try{
            xomino = Integer.parseInt(testCase[0]);
            rows = Integer.parseInt(testCase[1]);
            columns = Integer.parseInt(testCase[2]);
            area = rows*columns;
            if (xomino > area || area%xomino >0  || xomino>6 || perfectPieceExists(xomino,rows,columns) ) {
                winner = "RICHARD";
            }
        }catch(NumberFormatException e){
            System.out.println(e.getMessage());
        }


        return winner;
    }
    private boolean perfectPieceExists(int xomino,int rows,int columns){
        int length=xomino,height=1;

        if(xomino==4) {
            if(rows == 2 && columns >2 || columns == 2 && rows >2){
                return true;
            }
        }
        if(xomino==5) {
            if(rows == 2 || columns == 2 ||
                    rows == 3 && columns ==5 ||
                    rows == 5 && columns ==3 ){
                return true;
            }
        }
        if(xomino==6) {
            if(rows == 2 && columns >3 || columns == 2 && rows >3 ||
                    rows == 3 || columns == 3 ||
                    rows == 4 && columns ==4 ){
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
