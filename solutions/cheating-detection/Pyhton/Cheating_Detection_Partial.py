import numpy as np
#Jennifer Manriquez

def getDiff(arg):
    val = round((((arg  *  (-6.00))  / 100)  +  3.00),  2)
    return val

def getSkill(arg):
    val = round((((arg  *  6.00)  / 10000)  -  3.00),  2)
    return val

def findProb(pSkill, qNum):
    diff =pSkill-qNum
    prob = 1/(1+np.exp(-diff))
    return prob

testCases = int(input())
p = int(input())

for testCase in range(1, testCases + 1): # Key = Q#, Val = #Correct
    second_round = []
    qDiffD = {}
    pSkillD = {}
    for i in range(0, 100):
        line = input()
        list1 = list(line)
        second_round.append(line)
        for ind in range(0, 10000):
            if i in pSkillD:
                if list1[ind] == "1":
                    pSkillD[i] += 1
            else:
                pSkillD[i] = 1
            if ind in qDiffD:
                if list1[ind] == "1":
                    qDiffD[ind] += 1
            else:
                qDiffD[ind] = 1
    qD = sorted(qDiffD.keys(), key=lambda k: qDiffD[k], reverse=True)
    easiest = qD[0:300] # 5%
    hardest = qD[9701:10000] # 5%
    qDiffD.update((x, getDiff(y)) for x, y in qDiffD.items())

    pS = sorted(pSkillD.keys(), key=lambda k: pSkillD[k], reverse=True)
    pSkillD.update((x, getSkill(y)) for x, y in pSkillD.items())

    cheater = 0
    greatest_streak = 0
    for ind, val in enumerate(second_round):
        sus_ans_diff = 0
        for i, v in enumerate(hardest):
            probability = findProb(pSkillD[ind], qDiffD[hardest[i]])
            if str(round(probability)) != val[hardest[i]]:
                sus_ans_diff += 1

        for i, v in enumerate(easiest):
            probability = findProb(pSkillD[ind], qDiffD[easiest[i]])
            if str(round(probability)) != val[easiest[i]]:
                sus_ans_diff += 1

        if sus_ans_diff >= greatest_streak:
            greatest_streak = sus_ans_diff
            cheater = ind
    cheater = cheater + 1

    print("Case #" + str(testCase) + ": " + str(cheater))