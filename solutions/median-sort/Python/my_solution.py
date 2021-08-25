import sys

def check_error(result) -> int:
    if result == -1:
        sys.exit()
    return result

def check_answer(result) -> None:
    print(" ".join(map(str, result)), flush=True)
    answer = int(input())
    check_error(answer)

def query(i:int, j:int, k:int) -> int:
    # Display and flush the given question-query
    print(i, j, k, flush=True)
    # Get the median of the query
    result = int(input())
    return check_error(result)

def median_sort(i_list: list):
    # Variables
    result = [1, 2]
    i_size = len(i_list)

    # Process
    for number in range(3, i_size + 1):
        left = 0 
        right = len(result) - 1

        # If there is more than two elements, otherwise is sorted
        while right-left >= 1:
            # Assign indexes
            a = left + (right - left) // 3
            b = right - (right - left) // 3
            # Asks for the median
            median = query(result[a], result[b], number)

            # In the case that the median is a, (a,b,c)
            if median == result[a]:
                right = a - 1
                if left == right:  
                    right += 1

            # In the case that the median is b, (a,b,c)
            elif median == result[b]:
                left = b + 1
                if left == right:
                    left -= 1

            # In the case that the median is c, (a,b,c)
            elif median == number:
                left = (a + 1)
                right = (b - 1)
                if left == right:
                    left -= 1

        result.insert(left, number)

    check_answer(result)

# Gets the parameters of the judge initial answer
T, N, Q = [int(initial_answer) for initial_answer in input().split()]

# Runs through all cases
for test_case in range(T):
    initial_list = range(1, N + 1)
    median_sort(initial_list)

# Exits when finishes
sys.exit()