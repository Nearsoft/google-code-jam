import numpy as np 

# number of Test Cases
T = int(input())

# iterate through all cases
for t in range(1, T+1):
  # read case string
  #   and append a ! to make it easier to iterate through the string 
  S = input()+"!"

  # encode original string into a histogram-like format
  # COOOMMETT - > [(C, 1), (O, 3), (E, 1), (T, 2)]
  count = 0
  occ = []
  for i in range(len(S)-1):
    
    # set current character and add one to its consecutive occurrences
    s = S[i]
    count = count + 1

    # set next character
    next_s = S[i+1]

    # append occurrences
    if next_s !=s:
      occ.append([s, count])
      count = 0
    #else:
    #  continue

  # append artificial tuple (!, 0) to facilitate iteration over list
  occ.append(("!", 0))

  # iterate over the histogram-like string
  for i in range(len(occ)-1):
    ch1, times = occ[i]
    ch2, _ = occ[i+1]

    # if the following character has a greater order than the current one, 
    #    double the ocurrences of the current character
    if ch2>ch1:
      occ[i] = (ch1, 2*times)
  
  # print output
  print(f"Case #{t}: {''.join([a*b for a, b in occ])}")
