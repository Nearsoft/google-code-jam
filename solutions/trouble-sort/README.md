# Trouble sort

Deep in Code Jam's secret algorithm labs, we devote countless hours to wrestling with one of the most complex problems of our time: efficiently sorting a list of integers into non-decreasing order. We have taken a careful look at the classic bubble sort algorithm, and we are pleased to announce a new variant.

The basic operation of the standard bubble sort algorithm is to examine a pair of adjacent numbers, and reverse that pair if the left number is larger than the right number. But our algorithm examines a group of three adjacent numbers, and if the leftmost number is larger than the rightmost number, it reverses that entire group. Because our algorithm is a "triplet bubble sort", we have named it Trouble Sort for short.


```
  TroubleSort(L): // L is a 0-indexed list of integers
    let done := false
    while not done:
      done = true
      for i := 0; i < len(L)-2; i++:
        if L[i] > L[i+2]:
          done = false
          reverse the sublist from L[i] to L[i+2], inclusive
```

For example, for L = 5 6 6 4 3, Trouble Sort would proceed as follows:

* First pass:
  * inspect 5 6 6, do nothing: 5 6 6 4 3
  * inspect 6 6 4, see that 6 > 4, reverse the triplet: 5 4 6 6 3
  * inspect 6 6 3, see that 6 > 3, reverse the triplet: 5 4 3 6 6
* Second pass:
  * inspect 5 4 3, see that 5 > 3, reverse the triplet: 3 4 5 6 6
  * inspect 4 5 6, do nothing: 3 4 5 6 6
  * inspect 5 6 6, do nothing: 3 4 5 6 6
* Then the third pass inspects the three triplets and does nothing, so the algorithm terminates.

We were looking forward to presenting Trouble Sort at the Special Interest Group in Sorting conference in Hawaii, but one of our interns has just pointed out a problem: it is possible that Trouble Sort does not correctly sort the list! Consider the list 8 9 7, for example.

We need your help with some further research. Given a list of N integers, determine whether Trouble Sort will successfully sort the list into non-decreasing order. If it will not, find the index (counting starting from 0) of the first sorting error after the algorithm has finished: that is, the first value that is larger than the value that comes directly after it when the algorithm is done.

More details: https://codingcompetitions.withgoogle.com/codejam/round/00000000000000cb/00000000000079cb 