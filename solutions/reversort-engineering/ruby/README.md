# Reversort Engineering ⏪👨‍🔧

To run the program, in your console write:

```
ruby reversort_eng.rb
```

Then, it'll ask for the number of cases, in this case, **5** cases

```
5
```
Finally you'll introduce a couple of values separated by space, corresponding to size of the array and desired cost respectively. The algorithm will produce an array that will fit the conditions of those parameters in case that we apply the reversort algorithm to that array. 

Next an example of the inputs and their respective outputs


| inputs        | sample outputs|
| ------------- |:-------------:|
| 4 6           | Case #1: 4 2 1 3|
| 2 1           |Case #2: 1 2|
| 7 12          |Case #3: 7 6 5 4 3 2 1|
| 7 2           |Case #4: IMPOSSIBLE|
| 2 1000        |Case #4: IMPOSSIBLE|


The first case means that the array [4, 2, 1, 3] is produced by the reversort-engineering algorithm (have 4 elements and it'll take 6 steps of the reversort algorithm to solve it)