def main():
    cases = int(input())

    for case in range(cases):
        solve()

def solve():
    # N: number of rooms, K: number of tries to solve case
    N, K = list(map(int, input().split()))
    # R: current room, P: number of passages seen from room
    R, P = list(map(int, input().split()))
    
    # set {1, 2, ..., N + 1}
    rooms_left = set(range(1, N + 1))

    # removes room R from the set
    if R in rooms_left:
        rooms_left.remove(R)

    # degree of room
    degree = P

    # degree and count of every teleported room
    degree_vt = degree
    count_vt = 1


    for i in range(K):
        # if there's no room left break
        if not rooms_left:
            break

        # in even cases
        if i % 2 == 0:
            print(f'W')
            R, P = list(map(int, input().split()))

        # in odd cases
        else:
            print(f'T {next(iter(rooms_left))}')
            R, P = list(map(int, input().split()))

            degree_vt += P
            count_vt += 1

        if R in rooms_left:
            rooms_left.remove(R)
            degree += P
    
    # degrees average of every visited room
    degree_avg = degree_vt / count_vt

    # sum of degree of rooms visited and average degree times
    # number of rooms left
    result = int((degree + degree_avg * len(rooms_left)) / 2)
    print(f'E {result}')

if __name__ == "__main__":
    main()
