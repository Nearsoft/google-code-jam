## input:
# T: number of test cases
# N: for each test case, N is the number of jamcoins, has at least a 4 
## output:
# A,B: Two integers that have no 4's and A+B = N

T = int(input()) 

for j in range(T):
  N = input() 
  idx = -1
  A = 0
  for i in range(N.count('4') ):        # get each 4 position
    idx = N.find('4', idx + 1, len(N))  
    A = A + 2*10**(len(N)-idx-1)        # Set a 2 in said position in A, it will have a 0 if it is not a 4 in N
  print('Case #%d: %d %d'%((j+1),(int(N) - A), A)) # Return A and B = N - A