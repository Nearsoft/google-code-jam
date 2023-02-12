"""3d printing solution

This is a solution for the Google Code Jam '3d printing' problem
The first line of the input gives the number of test cases, T.
T test cases follow. Each test case consists of 3 lines.
The i-th line of a test case contains 4 integers
Ci, Mi, Yi, and Ki, representing the number of ink units
in the i-th printer's cartridge for the colors
cyan, magenta, yellow, and black, respectively.
More information:
codingcompetitions.withgoogle.com/
    codejam/round/0000000000876ff1/0000000000a4672b
"""

def solve(printers):
    """Finds a 4-digit color solution

    Finds a solution consisting of 4 numbers
    representing cyan, yellow, magenta, and black ink units
    
    Args:
        printers: list of (list of int)
            inner lists represent ink units available in a printer,
            outer list represents the printers
            [[c, y, m, b], [c, y, m, b], ...]
        
    Returns:
        A string containing 4 space separated numbers
        representing a color made up of
        cyan, yellow, magenta and black ink units
    """
    
    # Initialize the "available ink units" per ink type
    #                    C        Y        M        B
    maxInkPerColor = [1000000, 1000000, 1000000, 1000000]

    # finds the min value per ink type in the printers
    for printer in printers:
        for i in range(0, 4):
            maxInkPerColor[i] = min(maxInkPerColor[i], printer[i])

    inkUnits = sum(maxInkPerColor)
    
    if (inkUnits < 1000000):
        # no combination can be used in EVERY printer
        # to print with 1000000 units
        return "IMPOSSIBLE"
    
    for i in range(0, 4):
        inkUnits = sum(maxInkPerColor)
        diff = inkUnits - 1000000
        if diff == 0:
            # return solution in string format
            return " ".join(str(item) for item in maxInkPerColor)
        # if current sum is not 1000000 we need to reduce ink units
        if maxInkPerColor[i] >= diff:
            # just need to reduce the diff
            # and at next iteration diff value will be 0
            maxInkPerColor[i] -= diff
        else:
            maxInkPerColor[i] = 0

t = int(input()) # read number of test cases
for i in range(1, t+1):
    printer1 = [int(s) for s in input().split(' ')]
    printer2 = [int(s) for s in input().split(' ')]
    printer3 = [int(s) for s in input().split(' ')]
    print(f"Case #{i}: {solve([printer1, printer2, printer3])}")
    solve()