

T = int(input())

for i in range(T):

    word = ""
    couple = []
    counter = 1
    doubled = ""

    word = (str(input()))
    #print(word)

    for j in range(len(word) - 1):
        if word[j] == word[j + 1]:
            counter += 1
        else:
            couple.append([word[j], counter])
            counter = 1
    couple.append([word[-1], counter])
    #print(couple)

    for j in range(len(couple) -1):
        if couple[j][0] < couple[j +1][0]:
            doubled = doubled + 2 * couple[j][1] * couple[j][0]
        else:
            doubled = doubled + couple[j][1] * couple[j][0]
    doubled = doubled + couple[-1][1] * couple[-1][0]
    #print(doubled)f
    print(f"Case #{i + 1}: {doubled}")
