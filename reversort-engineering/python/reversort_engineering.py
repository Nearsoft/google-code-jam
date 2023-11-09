def reversort(size, cost):
    cost -= size - 1

    if cost < 0 or size * (size - 1) // 2 < cost:
        return None

    list_solution = list(range(1, size + 1))

    for i in range(size - 2, -1, -1):
        if cost >= size - i - 1:
            list_solution[i:] = reversed(list_solution[i:])
            cost -= size - i - 1
        else:
            j = cost + i + 1
            list_solution[i:j] = reversed(list_solution[i:j])
            break

    return list_solution


num_costases = int(input())


for costase in range(1, num_costases + 1):
    size, cost = map(int, input().split())
    list_solution = reversort(size, cost)
    result = "IMPOSSIBLE" if list_solution is None else " ".join(map(str, list_solution))
    print('costase #{}: {}'.format(costase, result))




