import java.io.*;
import java.util.*;
import java.io.PrintWriter;
public class InfiniteHouseOfPancakes{
	int minutes = 0;

	public static void main(String arg[]){
		new InfiniteHouseOfPancakes();
	}

	public InfiniteHouseOfPancakes(){
		WriteFile("small-output.txt",outputString("B-small-practice.in"));
		WriteFile("large-output.txt",outputString("B-large-practice.in"));
		/*String[] arr = new String[]{"9"};
		test2(1,arr.length,arr);*/
	}

	public ArrayList<String> outputString(String file){
		String data[] = ReadFile(file);
		int tests = Integer.parseInt(data[0]);
		int index = 1;
		ArrayList<String> outputString = new ArrayList<String>();
		for(int i=0; i<tests; i++){
			int arraysize = Integer.parseInt(data[index]);
			index++;
			String[] arrayString = data[index].split(" ");
			index++;
			outputString.add(test2(i+1,arraysize,arrayString));
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



	//The test method solve some of the cases but not all of them
	public void test(int caseNumber, int arraysize, String[] arrayString){

		ArrayList<Integer> array = new ArrayList<Integer>();
		for(String s : arrayString){
			array.add(Integer.parseInt(s));
		}

		boolean finished = false;

		while(!finished){
			
			/*for(int i : array)
				System.out.print(i+" ");
			System.out.println("");*/

			int max = Collections.max(array);
			if(max>0){
				boolean normal = isNormalMinute(max,array);
				//System.out.println(max+" "+normal);

				if(normal||max==1){
					normalMinute(array);
					minutes++;
				}
				else{
					//System.out.print("Minuto especial No encontre duplicados");
					specialMinute(array,max);
				}

				//System.out.println("Minutos: "+ minutes);
			}
			else
				finished = true;

		}

		System.out.println("Case #"+caseNumber+": "+ minutes);
		minutes = 0;
	}

	public boolean isNormalMinute(int max, ArrayList<Integer> array){

		int count = 0;

		for(int i : array){
			if(i==max)
				count++;
		}

		double minByDividing = max/2.0+count;
		//System.out.println("w"+minByDividing);
		if(Math.ceil(minByDividing)>=max)
			return true;//normal minute


		return false;//special minute
	}

	public void normalMinute(ArrayList<Integer> array){
		int size = array.size();
		for(int i=0; i<size; i++){
			if(array.get(i)>0)
				array.set(i, array.get(i)-1);
		}
	}

	public void specialMinute(ArrayList<Integer> array, int max){
		ArrayList<Integer> indexes = getIndexesOfMax(array,max);
		for(int i:indexes){

			if(max%2==0){
				array.set(i, max/2);
				array.add(max/2);
			}
			else{
				array.set(i, (max/2)+1);
				array.add((max/2));
			}

			minutes++;
		}
	}

	public ArrayList<Integer> getIndexesOfMax(ArrayList<Integer> array, int max){
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		
		for(int i=0; i<array.size(); i++){
			if(array.get(i)==max)
				indexes.add(i);
		}

		return indexes;

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

	//test2 does solve all of the cases. It tries every posibility, and returns the minimum value
	public String test2(int caseNumber, int arraysize, String[] arrayString){

			//List of pancakes
            int[] input = new int[arraysize];
            for (int i = 0; i < arraysize; i++)
                input[i] = Integer.parseInt(arrayString[i]);

            int best = 9001;//Over 9000!
            for (int time = 1; time < 1000; time++) {
                int moves = 0;
                for (int p : input)
                    moves += ((int) Math.ceil(p / (time*1.0))) - 1;
                best = Math.min(best, moves + time);
            }

            String s = "Case #"+caseNumber+": " + best;
            System.out.println(s);

            return s;
	}
}