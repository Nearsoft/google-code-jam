// Code Jam Individual Problem - Equal Sum - Carolina Garma
// Prev knowledge: binary representation, partitions

/*
My Approach:

This problem is more a math/partition problem rather than a programming one since 
the implementation requires a well planed output made by us (first half of the N numbers)
before any user input (second half of the N numbers).

Having done a well planed sequence of N numbers than can fulfill any N subsequent set provided 
by the machine, the implementation is easier.

In easy words, what we have to do is generate a set of N numbers than can fulfill any difference
obtained after minimizing the difference of the two subsets provided, from small units to bigger ones.

Punctually, and by having the problem constraint that our N numbers lie between 1 and 10**9, we can
be 100% sure that the minimized difference of any two given subsets A and B will be between 0 and 10**9.

**Hint: 

    This can be easily obtained thanks to binary (powers of two) representations.

    10**9 can be represented as the binary "111011100110101100101000000000"

    Therefore, we can represent any number between 1 and 10**9 using the first 30 powers of two,
    so lets add our first 30 tactical numbers to our array.

    The remaining 70 numbers could be whatever we want as long as they are different from the 
    previous 30 and inside the interval (1, 10**9). So lets add the 70 last integers before
    the interval ends, i.e. 10**9-70, 10**9-69 ... 10**9-1, 10**9

Then,

The minimized difference can be obtained using a greedy approach:
    Given a set of sorted N different numbers, and two subsets s1 and s2 with current sums c_sum1 and c_sum2
    For each n in sorted(N):
        If c_sum1 > c_sum2:
            Append current N number to s2
            Add current n to c_sum2
        else:
            Append current N number to s1
            Add current n to c_sum1

And having provided our tactical N numbers, we know that this minimized diff will be 0.
*/

using System;
using System.Collections.Generic;
using System.Linq;

namespace GoogleJam
{
  class EqualSum
  {
    static List<long> powers_of_two(int N)
    /*
    This function generates my proposed tactical N sets of numbers made of powers of 2
    and the remaining N-30 numbers will be the last N numbers before 10**9 inclusive.

    As N is always 100, this fuction is always performed without problems
    */
    {
        var A = new List<long>();

        for (double i = 0; i < 30; i++) // Add 30 tattical numbers
        {
            A.Add(Convert.ToInt64(Math.Pow(2, i)));
        }

        for (int i = 0; i < N-30; i++) // Add N-30 ramaining numbers (they can be whatever)
        {
            A.Add(Convert.ToInt64((Math.Pow(10, 9)))-i);
        }
        return A;

    }

    static List<long> solve_sum(List<long> A, List<long> B)
    /*
    This function implements a greedy approach to minimize the sum difference
    among two sets A and B
    */
    {
        // Join A and B into a new list C
        var C =  new List<long>(); 
        C.AddRange(A); 
        C.AddRange(B);

        // Sort C in descending order for a faster search
        C = C.OrderByDescending(i => i).ToList();

        long a_sum = 0L; // initialize a_sum
        long b_sum = 0L; // initialize b_sum
        var equal_sum_set = new List<long>(); // A set containing N_1 numbers with equal sum as the N_2 numbers

        foreach(var item in C) // Minimize difference and distribute numbers
            {
                if (a_sum > b_sum) {
                    b_sum += item;
                    equal_sum_set.Add(item);
                } else {
                    a_sum += item;
                }
            }

        return equal_sum_set; // Return resulting set with equal_sum, sum diff is 0 now

    }

    static void Main(string[] args)
    {
        long T = Convert.ToInt64(Console.ReadLine()); // Read # of Cases

        for (int i = 0; i < T; i++){
            int N = Convert.ToInt32(Console.ReadLine()); // Read N
            if (N == -1) { // if N==-1, end program
                break;
            }

            // Generate our A set of powers of two
            var A = powers_of_two(N);

            // Print A set in terminal
            foreach(var item in A)
            {
                Console.Write(item + " ");
            }
            Console.WriteLine();

            // Read B set from terminal
            var B_string = Console.ReadLine();
            List<long> B = B_string.Split(' ').ToList().Select(x => Convert.ToInt64(x)).ToList();

            // Solve Case
            var C = solve_sum(A,B);

            // Print result
            foreach(var item in C)
            {
                Console.Write(item + " ");
            }
            Console.WriteLine();


        }

    }
  }
}