#!/usr/bin/env python3

from math import gcd
import sys

def cryptopangram(N, L, integers):
    """
    N is the upper limit for the primes used
    L is the length of the list of values
    integers is the list of ciphered integers
    """
    # define alphabet
    alphabet = ['A', 'B', 'C', 'D', 'E', 'F', 'G','H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']

    # create a list for the ciphered primes and empty message
    primes = [-1] * (L+1)
    message = ""

    # calculate the gcd of the two first non equal ciphered primes
    index = -1
    for i in range(len(integers) - 1):
        if integers[i] != integers[i+1]:
            factor = gcd(integers[i], integers[i+1])
            index = i+1
            primes[index] = factor
            break

    #calculates backpropagation
    for i in range(index, 0, -1):
        prev_factor = integers[i-1] // primes[i]
        primes[i-1] = prev_factor

    #calculates frontpropagation
    for i in range(index, len(integers)):
        next_factor = integers[i] // primes[i]
        primes[i+1] = next_factor

    # remove repetitions
    unique_primes = list(set(primes))

    # copy into new list and sort it
    unique_primes_sorted = unique_primes[:]
    unique_primes_sorted.sort()

    # map the sorted list to each letter in a dictionary
    prime_map = dict(zip(unique_primes_sorted, alphabet))

    # map the calculated factors to their respective letters
    for prime in primes:
        message += prime_map[prime]


    return message

##############################################################################

def test_cryptopangrams():
    n_cases = int(sys.stdin.readline().strip())

    for case in range(n_cases):
        line1 = list(sys.stdin.readline().strip().split())
        N, L = [int(i) for i in line1]
        line2 = list(sys.stdin.readline().strip().split())
        integers = [int(i) for i in line2]

        message = cryptopangram(N, L, integers)

        print("Case #" + str(case+1)+ ": " + message)

##############################################################################

if __name__ == "__main__":
    test_cryptopangrams()

##############################################################################

#Test the program with the following input without the "#":

# 3
# 103 31
# 217 1891 4819 2291 2987 3811 1739 2491 4717 445 65 1079 8383 5353 901 187 649 1003 697 3239 7663 291 123 779 1007 3551 1943 2117 1679 989 3053
# 10000 25
# 3292937 175597 18779 50429 375469 1651121 2102 3722 2376497 611683 489059 2328901 3150061 829981 421301 76409 38477 291931 730241 959821 1664197 3057407 4267589 4729181 5335543
# 197 29
# 15 15 15 15 21 49 77 143 221 323 437 667 899 1147 1517 1763 2021 2491 3127 3599 4087 4757 5183 5767 6557 7387 8633 9797 10403

