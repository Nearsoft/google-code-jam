// Moons and Umbrellas 
import java.util.Scanner;
import java.lang.*;
public class Solution {
public static void main(String[] args) 
{
Scanner in = new Scanner(System.in);
//-------------------------------
    int t = in.nextInt();
    
    for(int j = 0; j < t; j ++) 
    {
        int x = in.nextInt();
        int y = in.nextInt();
        String line = in.next();
        char[] arr = line.toCharArray();
        
        
        int n = arr.length;
        int [][] m = new int[n][2];
        //array of the same string length
		for ( int i = 0; i < n; i++ )
		{
		    
			m[i][0] = 1000000 ;
			m[i][1]= 1000000 ;
		}
		if (arr[0] != 'C')
		{
			// J or ?
            m[0][1] = 0;
		}
		
		if (arr[0] != 'J')
		{
			// C or ?
            m[0][0] = 0;
		}
			for(int i = 1; i < n; i ++) 
	        {
	            // Review the minimums
	            
	        	if(arr[i] != 'C')
	        	{
	        		m[i][1] = Math.min( m[i-1][1], m[i-1][0]+x);
	        	}
	        	if(arr[i] != 'J')
	        	{
	        		 m[i][0] = Math.min( m[i-1][0], m[i-1][1]+y);
	        	}
	        }
        	int ans = Math.min(m[n-1][0], m[n-1][1]);
         
        System.out.println("Case #" + (j + 1) + ": " + ans);
       
    }
    in.close();
}
//-------------------------------
}