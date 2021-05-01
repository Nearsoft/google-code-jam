def reverse(arr, i, j):
    while i < j:
        arr[i], arr[j] = arr[j], arr[i]
        i += 1
        j -= 1


def reversort_engineering():
    n, c = map(int, input().strip().split())

    if c < n - 1 or c > ((n * (n + 1)) / 2) - 1:
        return "IMPOSSIBLE"
    arrOfRange = list(range(1, n + 1))

    for i in reversed(range(n - 1)):
        m = min(c - i, n - i)
        # print(i)
        c -= m
        # print(arrOfRange)
        # print(f"[i]: {i}")
        # print(f"[j]: {i + m - 1}")
        reverse(arrOfRange, i, i + m - 1)
        # reversort(arrOfRange, i, m + i - 1)
    return " ".join(map(str, arrOfRange))


if __name__ == '__main__':
    for case in range(1, int(input()) + 1):
        print(f'case #{case}: {reversort_engineering()}')
