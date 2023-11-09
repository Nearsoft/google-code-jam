def solution(inp):
    repeat = 1
    answer = "" 

    for l in range(len(inp)-1):
        if inp[l] == inp[l+1]:
            repeat+=1
        else:
            if inp[l] < inp[l+1]: # A < B , H > C, E < T, ...
                answer+=inp[l]*2*repeat
            else:
                answer+=inp[l]*repeat
            repeat = 1
            
    answer += inp[len(inp)-1]*repeat
    return answer

if __name__ == '__main__':
    cases = int((input()))
    for t in range(0,cases):
        case = str(input())
        print('Case #{}:'.format(t+1),solution(case))