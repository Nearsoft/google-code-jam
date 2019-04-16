import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Ominoes {
	public static void main(String args[])
	{
		new Ominoes();
	}
	public Ominoes() {
		
		String data[] = ReadFile("D-large-practice.in");

		int tests = Integer.parseInt(data[0]);

		for(int i=0;i<tests;i++){
			String input = data[i+1];
			test(i+1, input);
		}
	}
	public static void test(int numberOfCases, String input) {
		String xString = "", rString = "", cString = "";
		int X, R, C; //X is the value choosed by Richard to be the X-omino base, R is the grid #rows and C is the grid #columns

		int firstStart= 0;
		
		for(int i=0; i<input.length(); i++){
			if((input.charAt(i)+"").equals(" "))
				break;
			else
				xString += input.charAt(i);

			firstStart++;
		}
		X = Integer.parseInt(xString);

		int secondStart= 0;
		
		for(int i= firstStart + 1; i<input.length(); i++){
			if((input.charAt(i)+"").equals(" "))
				break;
			else
				rString += input.charAt(i);

			secondStart++;
		}
		R = Integer.parseInt(rString);

		//int thirdStart= 0;
		
		for(int i= firstStart + secondStart + 2; i<input.length(); i++){
			if((input.charAt(i)+"").equals(" "))
				break;
			else
				cString += input.charAt(i);

			//thirdStart++;
		}
		C = Integer.parseInt(cString);
		OminoGame(X, R, C, numberOfCases);

	}
	public static void OminoGame(int X, int R, int C, int numberOfCases) {
		//This method will test the X, R and C values to check if they meet the requirements to make Gabriel win
		
		/* First condition consists of X being less than 7, because otherwise, Richard can choose an omino with and empty square 
		 * inside its borders, making it impossible to Gabriel to fill it
		*/
		int firstIndicator = 7;
		
		/* Second condition consists of the product of R * C being multiple of X, because otherwise, Richard can choose 
		 * an omino not multiple and Gabriel would not have an option to fill the squares
    	*/
		int secondIndicator = ((R * C) % X);
		
		/* Third condition consists of R, C or both being more or equals to X, because otherwise, even with the ones 
		 * multiplied and resulting of X, Richard can choose the straight omino and win
    	*/
		int thirdIndicator = X;
		
		/* Fourth condition consists of R or C (the one who is not equals to X) being more than the half of X (+1 if X is odd), 
		 * because otherwise, richard can choose an omino like a square with more than C and it will not fit on the grid 
    	*/
		int fourthIndicator;
		
		if(X%2 == 0)
			fourthIndicator = (X / 2);
		else
			fourthIndicator = (X / 2) + 1;
		
		int fifthIndicator = thirdIndicator * fourthIndicator;
		
		String winner = "RICHARD";
		
		if(X < firstIndicator) {
			if(secondIndicator == 0) {
				if(R >= thirdIndicator || C >= thirdIndicator) {
					if(R >= thirdIndicator) {
						if(C >= fourthIndicator) {
							if( X > 3 ) {
								if(((R * C) - fifthIndicator) != 0 && ((R * C) - fifthIndicator) % X == 0
										&& C > (X / 2) ) {
									winner = "GABRIEL";
								}
							}
							else {
								winner = "GABRIEL";
							}
						}
					}
					if(C >= thirdIndicator) {
						if(R >= fourthIndicator) {
							if( X > 3 ) {
								if(((R * C) - fifthIndicator) != 0 && ((R * C) - fifthIndicator) % X == 0
										&& R > (X / 2) ) {
									winner = "GABRIEL";
								}
							}
							else {
								winner = "GABRIEL";
							}
						}
					}
				}
			}
		}
		System.out.println("Case #" + numberOfCases + ": " + winner);
	}
	public String[] ReadFile(String fileName){

        // This will reference one line at a time
        String line = null;
        String fileString = "";

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                fileString += line+",";
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }


        return fileString.split(",");
	}
}
