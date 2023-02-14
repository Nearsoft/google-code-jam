#Sanchez Palacios
#Google codejam 2020 3DPrinting
import sys

T = int(input())

for i in range (0,T):
    
    C = []
    M = []
    Y = []
    K = []
    
    for j in range(0,3):
        printer = list(input().split(" "))
        C.append(int(printer[0]))
        M.append(int(printer[1]))
        Y.append(int(printer[2]))
        K.append(int(printer[3]))

    tinta = [min(C),min(M),min(Y),min(K)]
    
    Total = tinta[0] + tinta[1] + tinta[2] + tinta[3]
    P = 1000000
    
    if(Total >= 1000000):
        res = "\nCase #" + str(i+1) + ": "
        z = 0
        for i in range (0,4):
            if P > tinta[i] and P > 0:
                res += str(tinta[i]) + " "
                P -= tinta[i]
            elif P > 0:
                res += str(P) + " "
                P -= P
            else:
                res += " 0 "
            z += 1 
        print(res+"\n")
    else:
        print("\nCase #" + str(i+1) + ": IMPOSSIBLE \n")
    sys.stdout.flush()

      
