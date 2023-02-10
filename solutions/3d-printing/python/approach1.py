def solve(printers):
    maxInkPerColor = [1000000, 1000000, 1000000, 1000000]

    for printer in printers:
        for i in range(0, len(printer)):
            maxInkPerColor[i] = min(maxInkPerColor[i], printer[i])

    inkUnits = sum(maxInkPerColor)
    
    if (inkUnits < 1000000):
        return "IMPOSSIBLE"
        
    for i in range(0, len(maxInkPerColor)):
        inkUnits = sum(maxInkPerColor)
        diff = inkUnits - 1000000
        if diff == 0:
            return " ".join(str(item) for item in maxInkPerColor)
        if maxInkPerColor[i] >= diff:
            maxInkPerColor[i] -= diff
        else:
            maxInkPerColor[i] = 0

t = int(input())
for i in range(1, t+1):
    printer1 = [int(s) for s in input().split(' ')]
    printer2 = [int(s) for s in input().split(' ')]
    printer3 = [int(s) for s in input().split(' ')]
    print(f"Case #{i}: {solve([printer1, printer2, printer3])}")