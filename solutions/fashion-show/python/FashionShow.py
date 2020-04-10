from collections import defaultdict

def getTestCases():
    global T
    T = int(inputFile.readline())

def fashionShow():
    # Get the values for N and M
    N, M = [int(x) for x in inputFile.readline().split()]
    # Create matrix
    matrix = ["."] * N
    for i in range(N):
        matrix[i] = ["."] * N
    # Create rows and columns to check for "x"
    freeRows = list(range(N))
    freeCols = list(range(N))
    # Create diagonals to check for "+"
    freePositiveDiag = list(range(2 * N - 1))
    freeNegativeDiag = list(range(-N + 1, N))
    # Create empty list for adding or replacing models for results
    modelsPlaced = list()
    # Add existing model to matrix, and remove coordinates that are already used
    for i in range(M):
        model, R, C = inputFile.readline().split()
        R = int(R) - 1
        C = int(C) - 1
        if model in ["x", "o"]:
            matrix[R][C] = model
            freeRows.remove(R)
            freeCols.remove(C)
        if model in ["+", "o"]:
            matrix[R][C] = model
            freePositiveDiag.remove(R+C)
            freeNegativeDiag.remove(R-C)
    # Check and add for "x"
    for R, C in zip(freeRows,freeCols):
        if matrix[R][C] == "+":
            matrix[R][C] = "o"
            modelsPlaced.append([R,C])
        elif matrix[R][C] == ".":
            matrix[R][C] = "x"
            modelsPlaced.append([R,C])
    # Create dictionary with coordinates linked to R + C
    freeDiag = defaultdict(list)
    for R in range(N):
        for C in range(N):
            freeDiag[R+C].append([R,C])
    # Arrange dictionary to check coordiantes from less to greater coordinates points per diagonal (Greedy Method)
    for i in sorted(range(2 * N  - 1), key=lambda i:len(freeDiag[i])):
        if i not in freePositiveDiag:
            continue
        for j in reversed(range(len(freeDiag[i]))):
            R, C = freeDiag[i][j]
            # Check and add for "+"
            if R - C in freeNegativeDiag:
                if matrix[R][C] == "x":
                    matrix[R][C] = "o"
                    if [R,C] not in modelsPlaced:
                        modelsPlaced.append([R,C])
                elif matrix[R][C] == ".":
                    matrix[R][C] = "+"
                    modelsPlaced.append([R,C])
                freePositiveDiag.remove(R+C)  
                freeNegativeDiag.remove(R-C)
                break
    # Add points in matrix
    sumPoints = 0
    for R in range(N):
        for C in range(N):
            if matrix[R][C] in ["+", "x"]:
                sumPoints = sumPoints + 1
            elif matrix[R][C] == "o":
                sumPoints = sumPoints + 2
    # Print results to file
    outputFile = open("outputFile.in","a")
    outputFile.write("Case #{}: {} {}\n".format(testcases, sumPoints, len(modelsPlaced)))
    for R, C in modelsPlaced:
        outputFile.write("{} {} {}\n".format(matrix[R][C], R + 1, C + 1))

inputFile = open("D-large-practice.in","r")
outputFile = open("outputFile.in","w")
testcases = 1
getTestCases()
while testcases != T +1:
    fashionShow()
    testcases += 1
inputFile.close()
outputFile.close()