
def main():
    # INPUT DATA: Read number of cases
    cases = int(input())
    # declare Array of Results
    resultsArray = []
    # Read and solve each case
    for i in range(cases):
        resultsArray.append(solve())
    # Print cases results
    for i in range(cases):
        print(f'Case #{i + 1}:', resultsArray[i])

def solve():
    # Input: Case numberOfExersices (e) and Weight types (w)
    e, w = list(map(int, input().split()))
    # Input of all exercise data and position the data on the array that correspond
    allExercises = [list(map(int, input().split())) for _ in range(e)]
    # prepare array for dynamic programing
    dp = [[0]*e for _ in range(e)]

    # OPERATION: here starts the resolution of the problem
    # i = exercises arrays(line)
    for i in range(e):
        current = [INF]*w
        # j = Index of exercise line within allExercises/ The array inside of the array of arrays
        for j in range(i, e):
            # k = index withing each exercise whithin allExercises/ the position of the element inside of the arrays
            for k in range(w):
                current[k] = min(current[k], allExercises[j][k])
            dp[i][j] = sum(current)

    dpDouble = [[INF]*e for _ in range(e)]

    for row in range(e):
        # duplicate movements to account the put on/off each weight
        dpDouble[row][row] = 2*dp[row][row]
        #Loop for calculate the delta movements aside the common ones in all the arrays(combination of exercises))
        for revRow in reversed(range(row)):
            #It returns the minimum movements whithin all the combinations calculated before
            dpDouble[revRow][row] = min(dpDouble[revRow][k]+dpDouble[k+1][row]-2*dp[revRow][row] for k in range(revRow,row))
    return dpDouble[0][e-1]


if __name__ == '__main__':
    INF = float("inf")
    main()
