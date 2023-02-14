"""ESAb ATAd

This is a solution for the Google Code Jam 'ESAb ATAd' problem
Initially, this program reads a single line containing two integers T and B:
the number of test cases and the number of bits in the array, respectively

The program then asks for each bit of information in the array
with queries to the stdout and readings via stdin

More information:
https://codingcompetitions.withgoogle.com/
    codejam/round/000000000019fd27/0000000000209a9e
"""

import sys

def getBit(index):
    print(index)
    sys.stdout.flush()
    return input() 

def getPair(data, left, b):
    """Gets a symmetric pair of bits in the array

    Queries for a symmetric pair of bits and stores the values in data
    it also stores if the pair has equal or different values

    Args:
        data: list of dicts where local data is being stored
        left: index of the left bit of the pair to query
        b: length of the data array in the db system
    """
    right = b - 1 - left
    data[left]['val'] = getBit(left+1) # add 1 because db system indexes from 1
    data[right]['val'] = getBit(right+1) # add 1 because db system indexes from 1
    
    # info about different or equal symmetric values are stored in 'sym' property
    data[left]['sym'] = data[left]['val'] == data[right]['val']
    data[right]['sym'] = data[left]['sym']

def getFluctuationType(data):
    # finds the index on the local data array of the first bit
    # with a symmetric pair with equal value or None if does not exist such bit
    symIndex = next((i for i,v in enumerate(data) if v['sym'] == True), None)

    # finds the index on the local data array of the first bit
    # with a symmetric pair with different value or None if does not exist such bit
    asymIndex = next((i for i,v in enumerate(data) if v['sym'] == False), None)
    
    # sym refers to the bit with symmetric pair with equal value
    # asym refers to the bit with symmetric pair with different value

    # symB and asymB will store the new values of the known bits after fluctuation

    # dSym and dAsym will store a boolean of wether the known bits changed
    # after the fluctuation

    symB = asymB = dSym = dAsym = None

    if symIndex is not None:
        symB = getBit(symIndex+1)
        dSym = data[symIndex]['val'] != symB
    else:
        getBit(1)
    if asymIndex is not None:
        asymB = getBit(asymIndex+1)
        dAsym = data[asymIndex]['val'] != asymB
    else:
        getBit(1)

    if (symB is not None) and (asymB is not None):
        if dSym and dAsym:
            return "C"
        elif (not dSym) and dAsym:
            return "R"
        elif dSym and (not dAsym):
            return "CR"
        elif (not dSym) and (not dAsym):
            return None
    elif (symB is not None) and (asymB is None):
        if dSym:
            return "C"
        else:
            return None
    elif (symB is None) and (asymB is not None):
        if dAsym:
            return "C"
        else:
            return None

def complement(data):
    for d in data:
        if d['val'] == '0':
            d['val'] = '1'
        elif d['val'] == '1':
            d['val'] = '0'

def update(data, fluctuationType):
    if fluctuationType == "C":
        complement(data)
    elif fluctuationType == "R":
        data.reverse()
    elif fluctuationType == "CR":
        complement(data)
        data.reverse()

# reads number of cases t and length of data array
t, b = (int(s) for s in input().split(' '))
q = 0 # counts how many queries we have made so far

for testCase in range(1, t + 1):
    # creates a list of dicts to store bit values
    # and info about wether the bit has a symmetrical pair
    # with equal value or not
    data = [ {'val': None, 'sym': None } for _ in range(b)]
    for i in range(0, int(b/2)):
        if q > 0 and q%10 == 0:
            update(data, getFluctuationType(data))
            q += 2
        getPair(data, i, b)
        q += 2
    r = ""
    for dict in data:
        r += dict['val']
    print(r) # attempts to give an answer about current bits in the data array
    sys.stdout.flush()
    ans = input()
    q = 0
    if ans != 'Y':
        break # if answer was incorrect no more processing is done