# Function to calculate max straight number
def calculateStraight():
    dices = int(input())
    # Array of dices
    diceSides = list(map(int, input().split()))
    # We sort the array for convinience O(n)
    diceSides.sort()
    # We only need two parameters to know if we can keep a straight
    minSides = diceSides[0]
    maxStraight = 0

    # For loop to calculate maxStraight
    for i in range(dices):
        # O(n) Function
        if (diceSides[i] >= minSides) and (diceSides[i] > maxStraight):
            maxStraight += 1
        else:
            continue

    # Our function returns an int
    return maxStraight


# Input routine
cases = int(input())

# Array to store results for further printing
results = []
# O(n^2) but since we know #cases then our function is O(nLogn) time
for i in range(cases):
    results.append(calculateStraight())
# Printing Routine
for i in range(cases):
    print(f"Case #{i + 1}: {results[i]}")
