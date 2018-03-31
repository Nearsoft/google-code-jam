import java.util.*;
public class InfiniteHouseOfPancakes{
	public static void main(String arg[]){
		new InfiniteHouseOfPancakes();
	}

	public InfiniteHouseOfPancakes(){
		Scanner miScanner = new Scanner(System.in);
		System.out.print("Number of Cases: ");
		int tests = Integer.parseInt(miScanner.nextLine());

		for(int i=0; i<tests; i++)
			test(i+1);
	}

	public void test(int caseNumber){
		Scanner miScanner2 = new Scanner(System.in);

		System.out.print("Number of non-empty plates: ");
		int arraysize = Integer.parseInt(miScanner2.nextLine());

		System.out.print("Number pankaces per person: ");
		String input = miScanner2.nextLine();
		String[] arrayString = input.split(" ");

		ArrayList<Integer> array = new ArrayList<Integer>();
		for(String s : arrayString){
			array.add(Integer.parseInt(s));
		}

		int minutes = 0;
		boolean finished = false;

		while(!finished){
			
			//for(int i : array)
			//	System.out.print(i+" ");

			int max = Collections.max(array);
			if(max>0){
				boolean dup = isDuplicated(max,array);
				//System.out.println(max+" "+dup);

				if(dup){
					normalMinute(array);
				}
				else{
					//System.out.print("Minuto especial No encontre duplicados");
					specialMinute(array,max);
				}

				minutes++;
				//System.out.println("Minutos: "+ minutes);
			}
			else
				finished = true;

		}

		System.out.println("Case#"+caseNumber+": "+ minutes);
	}

	public boolean isDuplicated(int max, ArrayList<Integer> array){
		int count = 0;
		for(int i : array){
			if(i==max)
				count++;

			if(count>=2)
				return true;
		}
		return false;
	}

	public void normalMinute(ArrayList<Integer> array){
		int size = array.size();
		for(int i=0; i<size; i++){
			if(array.get(i)>0)
				array.set(i, array.get(i)-1);
		}
	}

	public void specialMinute(ArrayList<Integer> array, int max){
		int maxIndex = array.indexOf(max);

		if(max%2==0){
			array.set(maxIndex, max/2);
			array.add(max/2);
		}
		else{
			array.set(maxIndex, (max/2)+1);
			array.add((max/2));
		}
	}

}