# We import the sys library to make our program exits when it needs
import sys

# Check if the median given by the judge was not a '-1',
# which it means that the program has a problem and need to exits
def check_error(result:int) -> int:
    if result == -1:
        sys.exit()
    return result

# Prints the final result to the judge and verifies the code it gives
def check_answer(result) -> None:
    print(" ".join(map(str, result)), flush=True)
    answer = int(input())
    check_error(answer)

# Display and flush the given question-query
def query(i:int, j:int, k:int) -> int:
    print(i, j, k, flush=True)
    # Waits for the judge answer
    result = int(input())   # Get the median of the query
    return check_error(result)

# Sort the list
def median_sort(i_list: list):
    result = [1, 2] # We start our result list by a predifined value of 1, 2 
                    # where we know that 1 < 2, a < b
    i_size = len(i_list)

    # Checks which position in our result array the 'number' should be
    for number in range(3, i_size + 1):
        left = 0    # From the beginning of the result array
        right = len(result) - 1 # From the last element in the result array 

        # If there is two elements, then it's sorted
        while right-left >= 1:
            # Assign indexes according to ternary search
            a = left + (right - left) // 3
            b = right - (right - left) // 3
            
            # Asks for the median
            median = query(result[a], result[b], number)

            # In the case that the median is a, (c,a,b)
            if median == result[a]:
                right = a - 1
                if left == right:  
                    right += 1

            # In the case that the median is b, (a,b,c)
            elif median == result[b]:
                left = b + 1
                if left == right:
                    left -= 1

            # In the case that the median is c, (a,c,b)
            elif median == number:
                left = a + 1
                right = b - 1
                if left == right:
                    left -= 1

        # Insert the given number at the leftist position
        result.insert(left, number)

    # Prints the result and checks if the judge gave an error code
    check_answer(result)

# Gets the parameters of the judge initial answer
T, N, Q = [int(initial_answer) for initial_answer in input().split()]

# Runs through all cases
for test_case in range(T):
    initial_list = range(1, N + 1)
    median_sort(initial_list)

# Exits when it finishes
sys.exit()