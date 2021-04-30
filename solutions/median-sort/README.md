Median Sort

You want to sort N
N
 distinct items, x1,x2,…,xN
x
1
,
x
2
,
…
,
x
N
. Unfortunately, you do not have a way of comparing two of these items. You only have a way to, given three of them, find out which one is the median, that is, which one is neither the minimum nor the maximum among the three.

For example, suppose N=5
N
=
5
 and you know that:
x1
x
1
 is the median of {x1,x2,x3}
{
x
1
,
x
2
,
x
3
}
x2
x
2
 is the median of {x2,x3,x4}
{
x
2
,
x
3
,
x
4
}
x3

Then, it is guaranteed that the sorted order of the elements is either x4,x2,x1,x3,x5
x
4
,
x
2
,
x
1
,
x
3
,
x
5
 or its reverse x5,x3,x1,x2,x4
x
5
,
x
3
,
x
1
,
x
2
,
x
4
. Notice that by knowing only medians, it is impossible to distinguish the order of any list from its reverse, since the median question has the same result for any three elements in both cases.

Your program will have to find the order of T
T
 lists of N
N
 elements using at most Q
Q
 median questions in total (or Q/T
Q
/
T
 queries per list on average). In each case, finding either the right order or its reverse is considered correct. The order for each case is generated uniformly at random from all possible orders, and independently of any other information.
 
Input and output

This is an interactive problem. You should make sure you have read the information in the Interactive Problems section of our FAQ.
Initially, the judge will send you a single line containing three integers T
T
, N
N
, and Q
Q
: the number of test cases, the number of elements to sort within each test case, and the total number of questions you are allowed across all test cases, respectively. Then, you must process T
T
test cases. Each test case consists of a series of question exchanges plus an additional exchange to provide the answer.
For a question exchange, your program must print a single line containing three distinct integers i,j,k
i
,
j
,
k
 all between 1
1
 and N
N
, inclusive, which corresponds to asking the judge "which element is the median of the set {xi,xj,xk}
{
x
i
,
x
j
,
x
k
}
?" The judge will respond with a single line containing a single integer L
L
, meaning that the median of that set is xL
x
L
 (L
L
 is always equal to one of i
i
, j
j
, or k
k
). If you try to perform a (Q+1)
(
Q
+
1
)
-th question exchange, the judge will simply output -1.
Once you are ready to state the result, print a line containing N
N
 integers representing the indices of the elements in sorted or reverse sorted order. The judge will respond with a single integer 1 if your answer is correct or -1 if it is not. After receiving the judge's answer for the T
T
-th case, your program must finish in time in order to not receive a Time Limit Exceeded error. In addition, if you print additional information after receiving the result for the T
T
-th case, you will get a Wrong Answer judgment.
If the judge receives an invalidly formatted line or invalid values from your program at any moment, the judge will print a single number -1. After the judge prints -1 for any of the reasons explained above, it will not print any further output. If your program continues to wait for the judge after receiving a -1, your program will time out, resulting in a Time Limit Exceeded error. Notice that it is your responsibility to have your program exit in time to receive a Wrong Answer judgment instead of a Time Limit Exceeded error. As usual, if the memory limit is exceeded, or your program gets a runtime error, you will receive the appropriate judgment.
x
3
 is the median of {x3,x4,x5}
