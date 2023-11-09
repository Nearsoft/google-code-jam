using System;
using System.Linq;
using System.Text.RegularExpressions;
using static System.Math;


namespace Moons
{
    class Program
    {
        static void Main(string[] args)
        {
            int arr = int.Parse(Console.ReadLine());
            
            for (int i = 0; i < arr; i++)
            {
                string[] parts = Console.ReadLine().Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries);
                int X = Int32.Parse(parts[0]);
                int Y = Int32.Parse(parts[1]);
                string chars = parts[2];
                int charsLong = chars.Length;
                int n = charsLong;
                int[,] dp = new int[n,2];
                for(int k=0; k<n; k++){
                    dp[k,0] = 1000000;
                    dp[k,1] = 1000000;
                }

                if(chars[0] != 'C'){
                    // J or ?
                    dp[0,1] =0;
                }
                if(chars[0] != 'J'){
                    // C or ?
                    dp[0,0] =0;
                }
                for(int j = 1; j<n; j++){
                    //Let's check my string 
                    if(chars[j] != 'C'){
                        dp[j,1]=Math.Min(dp[j-1,1],dp[j-1,0]+X);
                    }
                    if(chars[j]!= 'J'){
                        dp[j,0]=Math.Min(dp[j-1,0],dp[j-1,1]+Y);
                    }
                }

                int end = Math.Min(dp[n-1,0],dp[n-1,1]);

                Console.WriteLine("Case #" + (i + 1) + ": " + end);

            }
    
        }
    }

    
}