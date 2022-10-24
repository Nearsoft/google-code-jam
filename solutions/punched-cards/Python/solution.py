# Function to print the card, takes two arguments rows and colunms
def printPunchCard(row, col):
    # Top left print
    print("..", end='')
    for i in range(col - 1): # O(n)
        print("+-", end='')
    print("+", end='\n')
    print("..", end='')
    for i in range(col - 1): # O(n)
        print("|.", end='')
    print("|", end='\n')
    # End of top left print

    # Constructing the string to print multiple times
    plus = ""
    bars = ""

    # Plus sign string
    for i in range(col): # O(n)
        plus += "+-"
    plus += "+"

    # Bar sign string
    for i in range(col): # O(n)
        bars += "|."
    bars += "|"

    # Printing rest of the punch card
    for i in range(row): # O(n)
        print(plus, end='\n')
        if (i == row - 1):
            continue
        else:
            print(bars, end='\n')


# Input only numbers
while True:
    try:
        cases = int(input())
        break
    except:
        pass

casesArray = []
for i in range(cases): # O(n)
    x, y = input().split()
    casesArray.append([int(x), int(y)])

for i in range(cases): # O(n)
    print(f"Case #{i+1}:")
    printPunchCard(casesArray[i][0], casesArray[i][1])