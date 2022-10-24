"""
Double or One Thing - Google Jam
Solution: Ricardo Enrique Ortega Atayde
October 24, 2022
"""

# Input
def main():
    cases = int(input())
    #Array of results for later printing
    resArray = []
    for i in range(cases):
        # We execute the function N times and add the result to array
        resArray.append(minLexOrd(input()))
    
    for i in range(len(resArray)):
        # Printing array
        print(f"Case #{i + 1}: {resArray[i]}")
        
# Double or One Thing function declaration
def minLexOrd(str):
    res = '' # Variable to store the result
    for i in range(len(str)):
        letter = str[-i - 1] # We loop backwards through each char.
 
        """ We compare wether adding 1 or 2 characters to the word
        would would result in a lower alphabetical order"""
        
        if letter + letter + res > letter + res: 
            res = letter + res
        else:
            res = letter + letter + res
        
    return res
 
 
if __name__ == '__main__':
    main()
