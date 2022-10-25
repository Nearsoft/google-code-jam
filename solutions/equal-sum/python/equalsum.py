# Prev knowledge: binary representation, partitions

def powers_of_two(N):
    """
    This function generates a tactical N sets of numbers made of powers of 2
    and the remaining N-30 numbers will be the last N numbers before 10**9 inclusive.

    As N is always 100, this fuction is always performed without problems
    """
    A = [2**i for i in range(30)] + [10**9 - n for n in range(N-30)]

    return A

def solve_sum(A, B):
    """
    This function implements a greedy approach to minimize the sum difference
    among two sets A and B
    """
    C = sorted(A + B, reverse=True) # Join both subsets and sort them in reversed order
    a_sum = 0
    b_sum = 0
    equal_sum_set = [] # A set containing N_1 numbers with equal sum as the N_2 numbers

    for c in C:  
        if a_sum > b_sum:
            equal_sum_set.append(c)
            b_sum += c
        else:
            a_sum += c
    
    return equal_sum_set


def main():

    T = int(input())

    for t in range(T):
        N = int(input()) # Read N 
        if N==-1:
            break

        A = powers_of_two(N) # Generate our A set
        print(' '.join(map(str, A)))

        B = list(map(int, input().split())) # Read the B set provided by machine

        # Solve case
        C = solve_sum(A,B)
        print(' '.join(map(str, C)))


if __name__ == '__main__':
    main()
