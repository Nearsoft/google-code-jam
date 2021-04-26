# Reversort Engineering Solve
This is a Python solution for the [Reversort Engineering](https://codingcompetitions.withgoogle.com/codejam/round/000000000043580a/00000000006d12d7#problem) problem of Google Code Jam.

## How to run
You just need to run the main file with python:
```bash
python3 main.py
```
## Inputs
- First you should insert **T** the number of tests you want to run: 
	```bash
	1
	```
- Later you should insert two numbers **N** and **C**, the size of the list and the cost, separate by an space,: 
	```bash
	4 6
	```
## Outputs
For each test case, if there is no list of size **N** such that applying Reversort to it costs exactly **C**, output one line containing `Case #x: IMPOSSIBLE`, where **x** is the test case number (starting from 1). Otherwise, output one line containing `Case #x:  y1  y2  ...  yN`, where **x** is the test case number (starting from 1) and each **yi** is a distinct integer between **1** and **N**, representing the **i**-th element of one such possible list:
```bash
Case #1: 4 2 1 3
```

## How the code works
```python
def reverse(arr, i, j):
    while i < j:
        arr[i], arr[j] = arr[j], arr[i]
        i += 1
        j -= 1
```
The **reverse** function change the position between i and j, while i is minor than j. On this way we sort the list.

```python
def reversort_engineering():
    n, c = map(int, input().strip().split())

    if c < n - 1 or c > ((n * (n + 1)) / 2) - 1:
        return "IMPOSSIBLE"
    arrOfRange = list(range(1, n + 1))

    for i in reversed(range(n - 1)):
        m = min(c - i, n - i)
        c -= m
        reverse(arrOfRange, i, i + m - 1)
    return " ".join(map(str, arrOfRange))
```
The **reversort_engineering** function receive the **N** and **C** variables, processes them, and then it sends those **variables** to the **reverse** function. 

```python
for case in range(1, int(input()) + 1):
	print(f'case #{case}: {reversort_engineering()}')
```
we take the input of the user that is **T**, print, and execute the **reversort_engineering** function.