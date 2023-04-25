# Constants:
# There are many ways to do this
# But I find this easiest to visualize
ABC = ['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z']

# Functions:
# We want the first function to output a list of tuples like so: 
# PEEL --> [(P, 1), (E, 2), (L, 1)]
def preprocessing(w):
  pre_word = []
  letter_list = list(w)
  
  for i, letter in enumerate(letter_list):
    happens = 1
    j = i + 1

    # First part of the statement makes sure we don't go out of range
    # Second part compares the current letter with the following one
    while j < len(letter_list) and letter == letter_list[j]:
      happens += 1
      letter_list.pop(j)

    # Put the values into a tuple and attach them to a list
    dupe_tuple = (letter, happens)
    pre_word.append(dupe_tuple)
    
  return pre_word


#This function returns the lexicographically smallest word:
def write(ww):
  duped_chars = []
  # For every letter except the final one
  for i in range(len(ww) - 1):
    tup = ww[i]

    #If a letter is earlier in the alphabet...
    if ABC.index(tup[0]) < ABC.index(ww[i+1][0]): 
      times = tup[1] 
      while times > 0:
        # ...duplicate it
        duped_chars.append(tup[0]) 
        duped_chars.append(tup[0])
        times -= 1
        
    # Otherwise just append it as many times as it originally appears
    else: 
      times = tup[1]
      while times > 0:
        duped_chars.append(tup[0])
        times -= 1

  # The final letter is added on its own, it is never duplicated
  times = ww[-1][1] 
  while times > 0:
    duped_chars.append(ww[-1][0])
    times -= 1

  return "".join(duped_chars)    
                

tests = int(input())
case = 1

while tests > 0:
    #Call functions here:
    current_word = input()
    working_word = preprocessing(current_word)
    res = write(working_word)
  
    # This is just a saving throw in case a weird input glitched the code
    # It makes sure the result doesn't have less letters than the original word
    if len(res) < len(current_word):
        res = current_word
        
    print(f"Case #{case}: {res}")
        
    tests -= 1 
    case += 1