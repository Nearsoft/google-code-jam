def reverse(arr, i, j):
    while i < j:
        arr[i], arr[j] = arr[j], arr[i]
        i += 1
        j -= 1


def reversort_engineering():
    size_of_array, cost = map(int, input().strip().split())

    if cost < size_of_array - 1 or cost > ((size_of_array * (size_of_array + 1)) / 2) - 1:
        return "IMPOSSIBLE"
    array_from_one_to_size = list(range(1, size_of_array + 1))

    for i in reversed(range(size_of_array - 1)):
        minimum_cost = min(cost - i, size_of_array - i)
        cost -= minimum_cost
        reverse(array_from_one_to_size, i, i + minimum_cost - 1)
    return " ".join(map(str, array_from_one_to_size))


if __name__ == '__main__':
    test_cases = int(input())
    for case in range(1, test_cases + 1):
        print(f'case #{case}: {reversort_engineering()}')
