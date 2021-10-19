using System;
using System.Collections.Generic;
using System.Linq;

class Program {
  public static void Main (string[] args) {
    int intNumbers = Convert.ToInt32(Console.ReadLine());
    for(int i = 0; i<intNumbers; i++){
      string name = "Case #" + (i+1);
      string input = Console.ReadLine();
      string[] sInput = input.Split(" ");
      int n = Convert.ToInt32(sInput[0]);
      int cost = Convert.ToInt32(sInput[1]);
      solve(name, n, cost);
    }
  }

  static void solve(string name, int n, int cost) {

    int min = n-1;
    int max = (n+2)*(n-1)/2;

    if(cost < min || cost > max){
      Console.WriteLine($"{name}: IMPOSSIBLE");
    }else{
      List<int> listN = new List<int>();
      List<int> listOfIndexes = new List<int>();
      for (int i = 1; i<=n; i++){
        listOfIndexes.Add(i-1);
        listN.Add(i);
      }

      int remainCost = cost;
      List<int> listCost = new List<int>();
      for(int i=n; i>=2; i--){
        int minVal = i - 3;
        int tryVal = i;
        while(remainCost - tryVal <= minVal){
          tryVal--;
        }
        remainCost -= tryVal;
        listCost.Add(tryVal);
      }

      for(int i = 0; i<n-1; i++){
        int toPosition = listCost[i] + i;
        listOfIndexes.Reverse(i, toPosition-i);
      }

      List<int> results = new List<int>( new int[n] );
      for(int i = 1; i<=n; i++){
        results[listOfIndexes[i-1]] = i;
      }

      string result = string.Join(" ", results);
      Console.WriteLine($"{name}: {result}");

    }
  }

  static int factorial(int n) {
              int fact=1;
              for (int i = 1; i <= n; i++)
              {
                  fact *= i;
              }
              return fact;
          }
}