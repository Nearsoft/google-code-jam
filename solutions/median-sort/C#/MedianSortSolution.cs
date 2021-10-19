
using System;
using System.IO;

public class Solution
{
	public static void Main(String[] args)
	{
		var fs = Console.ReadLine().Split();
		int T = Convert.ToInt32(fs[0]);
		int n = Convert.ToInt32(fs[1]);
		int Q = Convert.ToInt32(fs[2]);

		for (var tt = 1; tt <= T; tt++)
		{
			int[] arr = {
				1 , 2
			};
			for (var i = 2; i < n; i++)
			{
				arr = add(arr, i + 1, fs);
			}
			if (!printAns(arr, fs))
			{
				return;
			}
		}
	}
	public static int[] add(int[] arr, int toAdd, string[] fs)
	{
		var targetIndex = getTargetIndex(arr, toAdd, 0, arr.Length, fs);
		int[] res = new int[arr.Length + 1];
		for (var i = 0; i < targetIndex; i++)
		{
			res[i] = arr[i];
		}
		res[targetIndex] = toAdd;
		for (var i = targetIndex + 1; i < res.Length; i++)
		{
			res[i] = arr[i - 1];
		}
		return res;
	}
	public static int getTargetIndex(int[] arr, int toAdd, int l, int r, string[] fs)
	{
		if (l == r)
		{
			return l;
		}
		if (l + 1 == r)
		{
			if (r != arr.Length)
			{
				r++;
			}
			else
			{
				l--;
			}
		}
		var leftmostpivot = l;
		var rightmostpivot = r - 1;
		var nSplits = rightmostpivot - leftmostpivot + 1;
		var m1 = leftmostpivot + (int)((nSplits - 1) / 3);
		var m2 = rightmostpivot - (int)((nSplits - 1) / 3);
		if (m1 >= m2)
		{
			throw null;
		}
		var med = ask(toAdd, arr[m1], arr[m2], fs);
		if (med == arr[m1])
		{
			return getTargetIndex(arr, toAdd, l, m1, fs);
		}
		else if (med == arr[m2])
		{
			return getTargetIndex(arr, toAdd, m2 + 1, r, fs);
		}
		else
		{
			return getTargetIndex(arr, toAdd, m1 + 1, m2, fs);
		}
	}
	public static int ask(int a, int b, int c, string[] fs)
	{
		Console.WriteLine(a.ToString() + " " + b.ToString() + " " + c.ToString());
		return Convert.ToInt32(Console.ReadLine());
	}
	public static bool printAns(int[] ans, string[] fs)
	{
		for (var i = 0; i < ans.Length; i++)
		{
			if (i != 0)
			{
				Console.Write(" ");
			}
			Console.Write(ans[i]);
		}
		Console.WriteLine();
		var res = Convert.ToInt32(Console.ReadLine());
		return res == 1;
	}
}