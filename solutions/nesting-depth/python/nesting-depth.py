"""Nesting Depth

This is a solution for the Google Code Jam 'Nesting Depth' problem
Given a string of digits S, insert a minimum number of opening and
closing parentheses into it such that the resulting string
is balanced and each digit d is inside exactly d pairs of matching parentheses

The first line of the input gives the number of test cases, T.
T lines follow. Each line represents a test case and contains only the string S

More information:
https://codingcompetitions.withgoogle.com/
    codejam/round/000000000019fd27/0000000000209a9f
"""

def solve(s):
    s = '0' + s + '0'
    diff = 0
    i = 1
    while i < len(s):
        # inserts parenthesis based on difference between digits
        diff = int(s[i]) - int(s[i-1])
        if diff > 0:
            s = s[:i] + ('(' * abs(diff)) + s[i:]
        elif diff < 0:
            s = s[:i] + (')' * abs(diff)) + s[i:]

        # next iteration will be next digit and not a parenthesis
        i += abs(diff) + 1

    return s[1:-1] # remove extra zeros added at the beginning
    
t = int(input())

for i in range(1, t + 1):
    print(f'Case #{i}: {solve(input())}')
