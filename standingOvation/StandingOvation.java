import java.io.*;
import java.util.*;
public class StandingOvation{
	public static void main(String arg[]){
		new StandingOvation();
	}
	
	public StandingOvation(){
		String data[] = ReadFile("A-large-practice.in");
		/*Scanner miScanner = new Scanner(System.in);
		
		System.out.print("Tests: ");
		int tests = Integer.parseInt(miScanner.nextLine());*/

		int tests = Integer.parseInt(data[0]);
		
		for(int i=0;i<tests;i++){
			String input = data[i+1];
			int Smax = Integer.parseInt(input.split(" ")[0]);
			String people = input.split(" ")[1];
			test(i+1,Smax,people);
		}
	}
	
	public void test(int numberOfCase, int Smax, String people){
		/*Scanner miScanner2 = new Scanner(System.in);
		System.out.print("input: ");
		String input = miScanner2.nextLine();

		int Smax = Integer.parseInt(input.split(" ")[0]);
		String people = input.split(" ")[1];*/
		//System.out.println("people: "+people);

		if(Smax!=people.length()-1){
			System.out.println("Invalid data!");
			return;
		}
		
		int standingPeople = 0;
		int invited = 0;
		
		for(int i=0;i<people.length();i++){
			//System.out.println("Shyness lvl: "+i);
			int numberOfPeople = Integer.parseInt(people.charAt(i)+"");
			
			//System.out.println("people: "+numberOfPeople);
			//System.out.println("standing people: "+standingPeople);
			
			if(numberOfPeople>0){
				if(i<=standingPeople){
					//System.out.println("Se levantan "+numberOfPeople);
					standingPeople += numberOfPeople;
				}
				else{
					invited += i-standingPeople;
					//System.out.println("Invito a "+(i-standingPeople));
					//System.out.println("Se levantan "+(numberOfPeople+i-standingPeople));
					standingPeople += numberOfPeople+i-standingPeople;
				}
			}
				
			//System.out.println("===================");
		}
		
		System.out.println("Case #"+numberOfCase+": "+invited);
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