def solve(printers: list) -> str:
    """
    finds a solution consisting of 4 numbers representing cyan, yellow, magenta, and black ink units
    printers : list of (list of int)
               inner lists represent ink units available in a printer, outer list represents the printers
               [[c, y, m, b], [c, y, m, b], ...]
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
        # no combination can be used in EVERY printer to print with 1000000 units
        return "IMPOSSIBLE"
    
    for i in range(0, 4):
        inkUnits = sum(maxInkPerColor)
        diff = inkUnits - 1000000
        if diff == 0:
            # return solution in string format
            return " ".join(str(item) for item in maxInkPerColor)
        if maxInkPerColor[i] >= diff:
            # just need to reduce the diff and next iteration diff will be 0
            maxInkPerColor[i] -= diff
        else:
            maxInkPerColor[i] = 0

# read number of test cases
t = int(input())
for i in range(1, t+1):
    printer1 = [int(s) for s in input().split(' ')]
    printer2 = [int(s) for s in input().split(' ')]
    printer3 = [int(s) for s in input().split(' ')]
    print(f"Case #{i}: {solve([printer1, printer2, printer3])}")
    solve()