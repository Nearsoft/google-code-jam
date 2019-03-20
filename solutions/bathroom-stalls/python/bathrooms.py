input_file  = open("input.txt", "r")
output_file = open("output.txt", "w")
testCases = int(input_file.readline())
resolvedCases = []
case = 1

while testCases > 0:

    bathroomsAndPersons = input_file.readline().split()

    numOfBathrooms = int(bathroomsAndPersons[0])
    print numOfBathrooms
    numOfPersons = int(bathroomsAndPersons[1])
    print numOfPersons

    def getNumsRightAndLeft(numOfBathrooms, numOfPersons, case):
        if numOfPersons == 1:
            left = (numOfBathrooms)/2
            print left
            right = (numOfBathrooms-1)/2
            print left
            return "Case #" + str(case) + ": " + str(left) + " " + str(right) + "\n"
        else:
            if numOfPersons % 2 == 0:
               return getNumsRightAndLeft(numOfBathrooms/2, numOfPersons/2, case)
            else:
               return getNumsRightAndLeft((numOfBathrooms-1) / 2, numOfPersons/2, case)

        # print "Started working"
        # middle = (numOfBathrooms+1)/2
        # evenOrdOdd = False
        #
        # if numOfBathrooms == numOfPersons:
        #     return "Case #" + str(case) + ": " + str(0) + " " + str(0) + "\n"
        #
        #
        # if numOfBathrooms%2 == 0:
        #     evenOrdOdd = True
        # else:
        #     evenOrdOdd = False
        #
        # left = numOfBathrooms - middle
        # right = left
        #
        # if evenOrdOdd and left > 0:
        #     left = left -1
        #
        # if numOfPersons > 1:
        #     if left > right:
        #         return getNumsRightAndLeft(left, numOfPersons-1, case)
        #     else:
        #         return getNumsRightAndLeft(right, numOfPersons-1, case)
        # else:
        #     return "Case #"+ str(case) + ": " + str(left) + " " + str(right)+"\n"


    resolvedCases.append(getNumsRightAndLeft(numOfBathrooms, numOfPersons, case))
    case = case + 1
    testCases = testCases - 1


# print resolvedCases

for case in resolvedCases:
    print case
    output_file.write(case)
input_file.close()
output_file.close()