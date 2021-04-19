import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
	public static short ncases=0;
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		//String readLine =
		//Case[] Cases = ReadCases(getName("tests","3", "input"));
		//String[] Solutions = ReadSol(getName("tests","3", "output"));
		Solver solver = new Solver();
		
		//System.out.format("Number of cases %d%n",ncases);
		//System.out.print("N: ");
		int n = Integer.parseInt(input.nextLine());
		//System.out.println(n);
		Case caso = new Case();
		String data;
		short i=0;
		short corr=0;
		//System.out.println(Solutions[0]);
		for (i=0;i<n;i++){
			//System.out.format("X:%d Y:%d S:%s %n", cas.X,cas.Y,cas.S);
			//System.out.println("Line :");
			data = input.nextLine();
			//System.out.println(data);
			//System.out.format("S :%s", data);
		    //Split the line by spaces 
		    String[] datas = data.split(" ");
		    //Get and converts the weights X and Y
		    short X = Short.parseShort(datas[0]);
		    short Y = Short.parseShort(datas[1]);
		    //Extracts the string input
		    String S = datas[2];
		    caso.set_data(X, Y, S);
		    //Assign the values to its array
		    String sol = solver.solve(caso, i);
		}
	}
	
	public static String getName(String type,String number, String IO) {
		String name ="";
		String pathBase = "/Users/nsl-intern/eclipse-workspace/Test1/src/data/";
		String typTest = type;
		String noTest =number;
			
			
		String end = "_"+IO+".txt";
		if (typTest=="test"){
			// "/Test1/src/data/test_set_2/ts2_input.txt"
			name=pathBase+typTest+"_set_"+noTest+"/ts"+noTest+end;
		}
		else {
			//  "/Test1/src/data/sample_test_set_1/sample_ts1_input.txt"
			name=pathBase+"sample_"+"test_set_"+noTest+"/sample_ts"+noTest+end;;
		}
		return name;
	}
	// Read the file to be processed and returns an object with the information ordered
	public static Case[] ReadCases(String filename) 
	{
		Case[] Cases;
		File myObj = new File(filename);
		try {
			try (Scanner myReader = new Scanner(myObj)) {
				ncases = Short.parseShort(myReader.nextLine());
				Cases = new Case[ncases];
				short i =0;
				while (myReader.hasNextLine()) {
					//Reads next line of the file
				    String data = myReader.nextLine();
				    //Split the line by spaces 
				    String[] datas = data.split(" ");
				    //Get and converts the weights X and Y
				    short X = Short.parseShort(datas[0]);
				    short Y = Short.parseShort(datas[1]);
				    //Extracts the string input
				    String S = datas[2];
				    //Assign the values to its array
				    Cases[i] = new Case();
				    Cases[i].set_data(X, Y, S);
				    i++;
				    }
			}
			return Cases;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Cases = null;
			return Cases;
		}
	}
	public static String[] ReadSol(String filename){
		String[] sol = new String[ncases];
		File myObj = new File(filename);
		try {
			try (Scanner myReader = new Scanner(myObj)) {
				short i =0;
				while (myReader.hasNextLine()) {
					//Reads next line of the file 
				    //
				    sol[i]= myReader.nextLine();
				    i++;
				    }
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sol = null;
		}
		return sol;
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
	public String solveHard(){
		String sol = "";
		String s;
		String s2;
		String qm = Pattern.quote("?");
		//System.out.format("Initial S:%n%s%n",this.S);
		s = subAQQA(this.S);
		int cost =0;
		
		Pattern pattern = Pattern.compile(qm);
		Matcher matcher = pattern.matcher(s);
		if(!matcher.find()) {
			return solveEasy();
		}
		if (this.i>98) {
			//System.out.println("Cuidado");
		}
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
		
		pattern = Pattern.compile(qm+"+");
	    matcher = pattern.matcher(s);
	    //System.out.format("S : %n%s%n", s);
	    
	    if(matcher.find()){
	    	int fE = matcher.end();
	    	int cE = fE+1;
	    	int l =s.length();
	    	if(cE>=l-1) {
	    		cE=l;
	    		}
	    	s2=s.substring(0,cE);
	    	//System.out.println(s2);
	    	cost = Cost(s2);
	    	int cS = cE-1;
	    	while(matcher.find()){
	    		fE = matcher.end();
		    	cE = fE+1;
		    	l =s.length();
		    	if(cE>=l-1) {
		    		cE=l;
		    		}
		    	s2=s.substring(cS,cE);
		    	//System.out.println(s2);
		    	cost += Cost(s2);
		    	cS = cE-1;
	    	}
	    	s2=s.substring(cS);
	    	//System.out.println(s2);
	    	cost += Cost(s2);
	    }
	    else {
	    	//System.out.println("Caso raro");
	    	//System.out.println(s);
	    	cost =Cost(s);
	    	}
	    
	    //System.out.format("SC:%d EC:%d %n",cutS,cutE);
		sol =String.format("Case #%d: %d",this.i,cost);
		System.out.println(sol);
		//System.out.println();
		return sol;
	}
	public int Cost(String s) {
		int cost;
		String s2;
		Pattern pattern;
		Matcher matcher;
		//String qm = Pattern.quote("?");
		
		if (s.length()<=1) { cost =0;}
		else { 
		 	char L1 = s.charAt(0);
		 	char L2 = s.charAt(1);
		 	if (L1=='?') {
		 		s2=s.substring(1);
		 		cost = minimum(s2, 'x');
		 	}
		 	else {
		 		if(L2=='?') {
		 			
		 			s2=s.substring(2);
		 			cost = minimum(s2,L1);
			 		
		 			
		 		}
		 		else if(L1==L2 ) {
		 			pattern = Pattern.compile("^"+L1+"+");
		 		    matcher = pattern.matcher(s);
		 		    matcher.find();
		 		    int i = matcher.end();
		 		    s2 = s.substring(i-1);
		 			cost = Cost(s2);
		 			}
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
	public int[] minimS(int l,char A,char B) {
		int[] cost =new int[2];
		int cost1;
		int cost2;
		if(l==0) {
			cost1 = Cost(A+"CJ"+B);
			cost2 = Cost(A+"JC"+B);
		}
		else {
			cost1 = Cost(A+"C"+B);
			cost2 = Cost(A+"J"+B);
		}
		
		if(cost1>cost2) {
			cost[0]=cost2;
			cost[1]=0;
		}
		else {
			cost[0]=cost1;
			cost[1]=1;
			}
		return cost;
	}
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
	public String subAQQA(String S) {
		String s=S;
		//s=s.replaceAll("CC+", "C");
		//s=s.replaceAll("JJ+", "J");
		
	    String cc = getQQpat("C","C");
	    //System.out.print(cc);
	    s=s.replace("C??C", cc);
	    
	    String jj = getQQpat("J","J");
	    //System.out.print(jj);
	    s=s.replace("J??J", jj);
	    
	    
	    String cj = getQQpat("C","J");
	    //System.out.print(jj);
	    s=s.replace("C??J", cj);
	    
	    String jc = getQQpat("J","C");
	    //System.out.print(jc);
	    s=s.replace("J??C", jc);
	    
	    cc = getQpat("C","C");
	    //System.out.print(jc);
	    s=s.replace("C?C", cc);
	    
	    jj = getQpat("J","J");
	    //System.out.print(jc);
	    s=s.replace("J?J", jj);
	    
	    cj = getQpat("C","J");
	    //System.out.print(cj);
	    s=s.replace("C?J", cj);
	    
	    jc = getQpat("J","C");
	    //System.out.print(jc);
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
	public String solveEasy() {
		int cost=0;
		String sol;
		this.S = this.S.replace("?", "");
		
		cost = coPat("CJ")*this.X+this.Y*coPat("JC"); 
		sol =String.format("Case #%d: %d",this.i,cost);
		System.out.println(sol);
		return sol;
		
	}
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