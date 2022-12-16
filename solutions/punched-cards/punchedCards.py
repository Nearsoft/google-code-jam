def punchCard(R, C):

    for r in range(R * 2 + 1):
        for c in range(C * 2 + 1):
            if r < 2 and c < 2:
                print(".", end="")
            else:
                if r % 2 == 0:
                    if c % 2 == 0:
                        print("+", end="")
                    else:
                        print("-", end="")
                else:
                    if c % 2 == 0:
                        print("|", end="")
                    else:
                        print(".", end="")
        print("")


# Read the number of test cases.
T = int(input())
# Loop over the number of test cases.
for test_no in range(1, T + 1):
    # get rows and columns
    (R, C) = tuple(map(int, input().split()))
    print("Case #%d:" % test_no)
    punchCard(R, C)
