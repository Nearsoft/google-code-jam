# A = 3
# B = 5
# C = 7
# D = 11
# E = 13
# F = 17
# G = 19
# H = 23
# I = 29
# J = 31
# K = 37
# L = 41
# M = 43
# N = 47
# O = 53
# P = 59
# Q = 61
# R = 67
# S = 71
# T = 73
# U = 79
# V = 83
# W = 89
# X = 97
# Y = 101
# Z = 103

#choose M (26) different primes up to N
#sort list of primes in increasing order
#map prime to alphabet letters (A: prime1, B: prime2, etc)

#remove space from pangram to form plaintext message
#write product of prime of first letter times prime of second letter
#write product prime of second letter times prime of third letter
#all the way to the last pair (if there were 26 letter, there will be 25 products)



# CJ QUIZ: KNOW BEVY OF DP FLUX ALGORITHMS
# CJQUIZKNOWBEVYOFDPFLUXALGORITHMS
# C x J = 7 x 31 = 217
# J x Q = 31 x 61 = 1891
# ...
# Y x Z = 101 x 103 = 10403

#HOW TO SOLVE
    # define alphabet
    # create a list for the ciphered primes and empty message
    # calculate the first two factors of the first integer
    # gets the next factor by dividing Integer_i // last_prime
    # remove repetitions
    # copy into new list and sort it
    # map the sorted list to each letter in a dictionary
    # map the calculated factors to their respective letters

import math

def get_prime_factors(product):
    factors = []
    for x in range(2, int(math.sqrt(product)) + 1):
        if product % x == 0:
            factors.append(x)
            factors.append(product // x)

            return factors

    return factors


def cryptopangram(N, L, integers):
    """
    N is the upper limit for the primes used
    L is the length of the list of values
    integers is the list of ciphered integers
    """
    # define alphabet
    alphabet = ['A', 'B', 'C', 'D', 'E', 'F', 'G','H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']

    # create a list for the ciphered primes and empty message
    primes = []
    message = ""

    # calculate the first two factors of the first integer
    factor1, factor2 = get_prime_factors(integers[0])
    primes.append(factor1)
    primes.append(factor2)

    # gets the next factor by dividing Integer_i // last_prime
    for i in range(1, len(integers)):
        next_factor = integers[i] // primes[-1] # here primes[i] instead also works
        primes.append(next_factor)

    # remove repetitions
    unique_primes = list(set(primes))

    # copy into new list and sort it
    unique_primes_sorted = unique_primes[:]
    unique_primes_sorted.sort()

    # map the sorted list to each letter in a dictionary
    prime_map= { primes_2:letter for primes_2,letter in zip(unique_primes_sorted, alphabet) }

    # map the calculated factors to their respective letters
    for prime in primes:
        message += prime_map[prime]

    return message

if __name__ == "__main__":
    print("CRYPTOPANGRAM TEST")
    # CASE 1
    N = 103
    L = 31
    integers = [217, 1891, 4819, 2291, 2987, 3811, 1739, 2491, 4717, 445, 65, 1079, 8383, 5353, 901, 187, 649, 1003, 697, 3239, 7663, 291, 123, 779, 1007, 3551, 1943, 2117, 1679, 989, 3053]

    message1 = cryptopangram(N, L, integers)
    expected_msg1 = "CJQUIZKNOWBEVYOFDPFLUXALGORITHMS"

    print("Expected msg 1: ")
    print(expected_msg1)
    print("Calculated msg 1: ")
    print(message1)

    assert message1 == expected_msg1, "First case failed!"

    # CASE 2
    N = 10000
    L = 25
    integers = [3292937, 175597, 18779, 50429, 375469, 1651121, 2102, 3722, 2376497, 611683, 489059, 2328901, 3150061, 829981, 421301, 76409, 38477, 291931, 730241, 959821, 1664197, 3057407, 4267589, 4729181, 5335543]

    message2 = cryptopangram(N, L, integers)
    expected_msg2 = "SUBDERMATOGLYPHICFJKNQVWXZ"

    print("Expected msg 2: ")
    print(expected_msg2)
    print("Calculated msg 2: ")
    print(message2)

    assert message2 == expected_msg2, "Second case failed!"

