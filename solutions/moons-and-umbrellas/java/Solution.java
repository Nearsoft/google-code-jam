import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
	public static short ncases=0;
	public static void main(String[] args) {
		//Creates the input object
		Scanner input = new Scanner(System.in);
		//Creates the solver object
		Solver solver = new Solver();
		//Read the number of cases
		int n = Integer.parseInt(input.nextLine());
		//Creates the object that stores the information structured
		Case caso = new Case();
		String data;
		short i=0;
		//Reads every case
		for (i=0;i<n;i++){
			//Read the data input
			data = input.nextLine();
			
		    //Split the line by spaces 
		    String[] datas = data.split(" ");
		    //Get and converts the weights X and Y
		    short X = Short.parseShort(datas[0]);
		    short Y = Short.parseShort(datas[1]);
		    //Extracts the string input
		    String S = datas[2];
			//Assigns the values to data
		    caso.set_data(X, Y, S);
		    //Assign the values to its array
		    String sol = solver.solve(caso, i);
		}
	}
}
// Class that contains the information
class Case{
	short X,Y;
	String S;
	public void set_data(short x, short y, String s) {
		this.X=x;
		this.Y=y;
		this.S=s;
	}
}
//Class that contains the methods used to solve the problem
class Solver{
	short X,Y,i;
	boolean neg;
	String S;
	public String solve(Case datIn,short i) {
		this.X = datIn.X;
		this.Y = datIn.Y;
		this.S = datIn.S;
		this.i=(short) (i+1);
		this.neg = (this.X+this.Y)<=0;
		
		String sol = "";
		if(this.X>0 && this.Y>0){sol=solveEasy();}
		else {sol=solveHard();}
		return sol;
	}
	//Solves the problem when it has negative weights
	public String solveHard(){
		String sol = "";
		String s;
		String s2;
		String qm = Pattern.quote("?");
		//Replaces some easy patterns
		s = subAQQA(this.S);
		int cost =0;
		
		Pattern pattern = Pattern.compile(qm);
		Matcher matcher = pattern.matcher(s);
		//Check if the string has ? symbol
		if(!matcher.find()) {
			return solveEasy();
		}
		//Solves when J or C are not present in S
		pattern = Pattern.compile("C|J");
		matcher = pattern.matcher(s);
		if(!matcher.find()) {
			if(this.neg) {
				if(s.length()%2==0) {
					if(this.X>this.Y) {cost=this.Y;}
					else {cost= this.X;}
					cost+=(s.length()-1)/2*(this.X+this.Y);
				}
				else {
					if(this.X>this.Y) {cost=this.Y;}
					else {cost= this.X;}
					cost = s.length()/2*(this.X+this.Y);
					}
			}
			else {
				cost =0;
			}
			sol =String.format("Case #%d: %d",this.i,cost);
			System.out.println(sol);
			return sol;
		}
		//Creates a patterns that maths ?ns 
		pattern = Pattern.compile(qm+"+");
	    matcher = pattern.matcher(s);
	    //Checks if ? appears
	    if(matcher.find()){
	    	int fE = matcher.end();
	    	int cE = fE+1;
	    	int l =s.length();
	    	if(cE>=l-1) {
	    		cE=l;
	    		}
	    	s2=s.substring(0,cE);
			//Get the cost of the first chunk
	    	cost = Cost(s2);
	    	int cS = cE-1;
			//Creates several chunks and calculates their costs
	    	while(matcher.find()){
	    		fE = matcher.end();
		    	cE = fE+1;
		    	l =s.length();
		    	if(cE>=l-1) {
		    		cE=l;
		    		}
		    	s2=s.substring(cS,cE);
		    	//Sum the cost of every chunk
		    	cost += Cost(s2);
		    	cS = cE-1;
	    	}
	    	s2=s.substring(cS);
	    	//Get the last chunk and sum its cost
	    	cost += Cost(s2);
	    }
	    else {
	    	//Get the cost
	    	cost =Cost(s);
	    	}
	    
		sol =String.format("Case #%d: %d",this.i,cost);
		System.out.println(sol);
		
		return sol;
	}
	// Calculates the cost of a string
	public int Cost(String s) {
		int cost;
		String s2;
		Pattern pattern;
		Matcher matcher;
		//If the string contains only one 
		if (s.length()<=1) { cost =0;}
		else { 
			//L1 first letter
			//L2 second letter
		 	char L1 = s.charAt(0);
		 	char L2 = s.charAt(1);
		 	if (L1=='?') {
				 //If the first letter is ?
		 		s2=s.substring(1);
				 //Tries with J and C and obtains the minimum cost
		 		cost = minimum(s2, 'x');
		 	}
		 	else {
				 //If the second letter is ?
		 		if(L2=='?') {
					 //Tries with J and C and obtains the minimum cost
		 			s2=s.substring(2);
		 			cost = minimum(s2,L1);
		 		}
				 //If the second letter is equal to the first letter
		 		else if(L1==L2 ) {
					 //Finds when this patterns ends
		 			pattern = Pattern.compile("^"+L1+"+");
		 		    matcher = pattern.matcher(s);
		 		    matcher.find();
		 		    int i = matcher.end();
					 //Cut the patterns and calculates its cost
		 		    s2 = s.substring(i-1);
		 			cost = Cost(s2);
		 			}
				//If the letters are defined calculates its cost
		 		else if(L1=='C'&&L2=='J') {
		 			s2=s.substring(2);
		 			cost = this.X+Cost("J"+s2);
		 		}
		 		else  {
		 			s2=s.substring(2);
		 			cost = this.Y+Cost("C"+s2);
		 			
		 		}
		 	}
		}
		return cost;
	}
	//Evalutes ? and returns the minimum cost
	public int minimum(String s,char a) {
		int costC,costJ;
		String op1,op2;
		if(a != 'x') {
			op1=a+"C";
			op2=a+"J";
		}
		else {
			op1="C";
			op2="J";
		}
		costC=Cost(op1+s);
		costJ=Cost(op2+s);
		if (costC>costJ) {return costJ;}
 		else {return costC;}
	}
	// Replace common patterns
	public String subAQQA(String S) {
		String s=S;
	    String cc = getQQpat("C","C");
	    s=s.replace("C??C", cc);

	    String jj = getQQpat("J","J");
	    s=s.replace("J??J", jj);
	    
	    
	    String cj = getQQpat("C","J");
	    s=s.replace("C??J", cj);
	    
	    String jc = getQQpat("J","C");
	    s=s.replace("J??C", jc);
	    
	    cc = getQpat("C","C");
	    s=s.replace("C?C", cc);
	    
	    jj = getQpat("J","J");
	    s=s.replace("J?J", jj);
	    
	    cj = getQpat("C","J");
	    s=s.replace("C?J", cj);
	    
	    jc = getQpat("J","C");
	    s=s.replace("J?C", jc);
	    
		return s;
	}
	public String getQpat(String A,String B) {
		String r=A+"?"+B;
		int cost;
		int locost=Cost(r);
		char[] a = {'J','C'};
		for(char i:a) {
			cost=Cost(A+i+B);
			if (cost<=locost){
				r=A+i+B;
				locost=cost;}
		}
		return r;
	}
	public String getQQpat(String A,String B) {
		String r=A+A+B+B;
		int cost;
		int locost=Cost(r);
		char[] a = {'J','C'};
		for(char i:a) {
			for(char j:a) {
				cost=Cost(A+i+j+B);
				if (cost<=locost){
					r=A+i+j+B;
					locost=cost;}
			}
		}
		return r;
	}
	
	//Solves the problem when it doesn't have negatives weights
	public String solveEasy() {
		int cost=0;
		String sol;
		this.S = this.S.replace("?", "");
		
		cost = coPat("CJ")*this.X+this.Y*coPat("JC"); 
		sol =String.format("Case #%d: %d",this.i,cost);
		System.out.println(sol);
		return sol;
		
	}
	//Counts the number of pattern's appearences 
	public short coPat(String pat) {
		short count=0;
		Pattern P = Pattern.compile(pat);
		Matcher m = P.matcher(this.S);
		while(m.find()) {
			count+=1;
		}
		return count;
	}
}