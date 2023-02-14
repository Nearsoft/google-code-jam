
## input:
# T: number of test cases
# N: grid will have NxN size
# y: String that represents Lydia's path, it has N-1 characters
## output:
# path that will not have a step as Lydias' one
T = int(input()) # numero de casos de prueba

for j in range(T):
  N = input() #ni siquiera voy a usar esta variable
  y = input()
  sol = ""
  for l in y: # for each step in Lydia's Path, we take the opposite step
    if l == 'S':
      sol = sol + 'E'
    else:
      sol = sol + 'S'
  print('Case #%d: %s'%((j+1),sol)) # return new path