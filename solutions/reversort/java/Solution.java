import java.util.*;
import java.lang.*;
import java.io.*;

public class Solution{
	public static void main (String[] args) throws java.lang.Exception{
	    Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		for(int i=0; i<t; i++){
			int sum = 0;
			LinkedList <Integer> list = new LinkedList<>();
			int n = scanner.nextInt();
			for(int j=0; j<n; j++){
				int l = scanner.nextInt();
				list.add(l);
			}
			for(int j=0; j<n-1; j++){
				int index = list.indexOf(j+1);
				Stack<Integer> stack = new Stack<>();
				for(int k=j; k<=index; k++){
					stack.push(list.get(k));
				}
				for(int k=j; k<=index; k++){
					list.set(k, stack.pop());
				}
				sum += index - j + 1;
			}
			System.out.println("Case #" + (i+1) + ": " + sum);
		}
	}
}
