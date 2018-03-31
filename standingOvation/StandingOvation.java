import java.io.*;
import java.util.*;
import java.io.PrintWriter;
public class StandingOvation{
	public static void main(String arg[]){
		new StandingOvation();
	}
	
	public StandingOvation(){
		WriteFile("small-output.txt",outputString("A-small-practice.in"));
		WriteFile("large-output.txt",outputString("A-large-practice.in"));
	}

	public ArrayList<String> outputString(String file){
		String data[] = ReadFile(file);

		int tests = Integer.parseInt(data[0]);
		ArrayList<String> outputString = new ArrayList<String>();
		for(int i=0;i<tests;i++){
			String input = data[i+1];
			int Smax = Integer.parseInt(input.split(" ")[0]);
			String people = input.split(" ")[1];
			outputString.add(test(i+1,Smax,people));
		}

		return outputString;
	}

	public void WriteFile(String name, ArrayList<String> outputString){
		try{
			PrintWriter out = new PrintWriter(new File(name));
			for(String s:outputString){
				//System.out.println(s);
				out.write(s);
				out.println();
			}
			out.close();
		}catch(FileNotFoundException e){}
	}
	
	public String test(int numberOfCase, int Smax, String people){

		if(Smax!=people.length()-1){
			System.out.println("Invalid data!");
			return "";
		}
		
		int standingPeople = 0;
		int invited = 0;
		
		for(int i=0;i<people.length();i++){
			int numberOfPeople = Integer.parseInt(people.charAt(i)+"");
			
			if(numberOfPeople>0){
				if(i<=standingPeople){
					standingPeople += numberOfPeople;
				}
				else{
					invited += i-standingPeople;
					standingPeople += numberOfPeople+i-standingPeople;
				}
			}
		}
		String s = "Case #"+numberOfCase+": "+invited;
		//System.out.println(s);
		return s;
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