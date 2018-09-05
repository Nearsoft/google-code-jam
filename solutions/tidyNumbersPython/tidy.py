numbers = []
numberOfRepeats = 0
i = 0
#t = TicToc()


testCases = int(input())
while testCases > 0:
    numbers.append(int(input()));
    i += 1
    testCases -= 1

#t.tic()
case = 1
for originalNumber in numbers:
    tidy = False
    maxTidyNumber = originalNumber
    # print ('Number of digits: '+str(len(str(maxTidyNumber))))
    while not tidy:
        stringNumber = str(originalNumber)
        i = len(stringNumber) - 1;
        tidy = True
        delta = 0
        while i > 0:
            numberOfRepeats += 1
            if int(stringNumber[i]) < int(stringNumber[i - 1]):
                tidy = False
                delta = int(stringNumber[i:])
                break
            i -= 1
        maxTidyNumber = originalNumber
        originalNumber -= delta+1

    print('Case #'+ str(case) + ": "+str(maxTidyNumber))
    case += 1
    #print('Number of attempts: '+str(numberOfRepeats))
#t.toc()