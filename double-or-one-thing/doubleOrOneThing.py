# By: José Arias
#
# Code Jam 2022 - Round 1A Double or One Thing

def test():
    word = input()
    
    result = []
    count = 1

    for i in range(len(word)):
        # checks if it's not the last character on  the string
        if i + 1 != len(word):
            # adds 1 to count if the current character is the
            # same as the next one
            if word[i] == word[i + 1]:
                count += 1
            # appends to the result the current character times 
            # the value in the count variable
            else:
                if word[i] < word[i + 1]:
                    result.append(word[i] * count)
                count = 1

        # appends current character to our result list
        result.append(word[i])
    return "".join(result)


if __name__ == '__main__':
    result = []

    cases = int(input())

    # asks the user for a number of cases and appends the
    # return of each test to our result list
    for case in range(cases):
        result.append(test())

    # prints the result of each case
    for case in range(cases):
        print(f'Case #{case + 1}: {result[case]}')
