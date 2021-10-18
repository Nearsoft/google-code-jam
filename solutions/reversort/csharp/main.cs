using System;

class Program {
  public static void Main (string[] args) {
    run();
  }

  static void run() {
    Console.WriteLine("Enter number of cases: ");
    int num_cases = Convert.ToInt32(Console.ReadLine());
    for (int i = 0; i < num_cases; i++) {
      Console.WriteLine("Enter array length: ");
      int len = Convert.ToInt32(Console.ReadLine());
      
      Console.WriteLine("Enter array values separated by spaces");
      string inp = Console.ReadLine();
      string[] input_numbers = inp.Split(" ");

      int[] array = Array.ConvertAll(input_numbers, s => int.Parse(s));
      int cost = reversort(array);

      Console.WriteLine("Case #{0}: {1}", i + 1, cost);
      
    }
  }

  static int reversort(int[] array) {
    int cost = 0;
    for (int i = 0; i < array.Length - 1; i++) {
      int j = min_index(array, i);
      Array.Reverse(array, i, j - i + 1);
      cost += j - i + 1;
    }
    return cost;
  }

  static int min_index(int[] array, int i) {
    int minimum = Int32.MaxValue, min_index = -1;
    for (int j = i; j < array.Length; j++) 
      if (array[j] < minimum) {
        min_index = j;
        minimum = array[j];
      } 

    return min_index;
  }
}