# Function to calculate the minimum ink possible, takes 1 argument
# 1 multidimensional array [[inks],[inks],[inks]] ... etc which will be the ink
# on each tree printers
def calculateInk(arr):
    TOTAL = 1000000
    # renamed the array, each arr[] is one printer 
    printers = [arr[0], arr[1], arr[2]]
    c = m = y = k = 0
    # For loop to know if it will be possible to print
    # Double loop which will result on O(n^2) complexity, but we know how
    # many times it will loop (4*3), so O(n)
    for i in range(3):
        curr = sum(printers[i])
        if curr < TOTAL:
            return "IMPOSSIBLE"

    # Checking minimum values for each printer to sum up to 1million units
    # We construct an array with mentioned values
    minInk = printers[0]
    for i in range(4):
        for j in range(3):
            if minInk[i] > printers[j][i]:
                minInk[i] = printers[j][i]

    # Double check to confirm the 1m units
    curr = sum(minInk)
    if curr < 1000000:
        return "IMPOSSIBLE"
    else:
        ink = 0
        currPrint = [0, 0, 0, 0]
        
        # We have minimum values already, but we need to sum 1m units
        # This function will reduce the array to sum 1m
        for i in range(4):
            if (ink + minInk[i]) >= 1000000:
                currPrint[i] = minInk[i] - abs(minInk[i] + ink - 1000000)
                break
            else:
                ink += minInk[i]
                currPrint[i] = minInk[i]
    return currPrint

# Input routine

cases = int(input())
results = [] * cases

# For Loop to calculate each case
# The results will be stored in "results" for further printing
for i in range(cases): # O(n^2), max inputs = 100 * 3, so O(n)
    caseArray = [[], [], []]
    for j in range(3):
        c, m, y, k = input().split()
        caseArray[j] = ([int(c), int(m), int(y), int(k)])
    results.append(calculateInk(caseArray))

# For loop for printing the resultss
for i in range(cases): # O(n)
    if len(results[i]) != 4:
        print(f"Case #{i+1}: {results[i]}")
    else:
        print(f"Case #{i+1}:", end=" ")
        for j in range(4):
            print(results[i][j], end=" ")
        print("")