def find_color(test_cases, printers):
  results = []
  for t in range(test_cases):
    min_units = 0
    my_colors = [0, 0, 0, 0]
    for i in range(4):
      min_units = min(printers[t][0][i], printers[t][1][i], printers[t][2][i])

      my_colors[i] = min_units
      if sum(my_colors) < 10**6:
        my_colors[i] = min_units
      elif sum(my_colors) >= 10**6:
        a = sum(my_colors) - 10**6
        my_colors[i] = my_colors[i] - a
        break

    if sum(my_colors) < 10**6:
      results.append(["IMPOSSIBLE"])
    else:
      results.append(my_colors)
  return results


def main():
  test_cases = int(input().strip())
  printers = []
  for _ in range(test_cases):
    printer_info = [list(map(int, input().split())) for _ in range(3)]
    printers.append(printer_info)

  results = find_color(test_cases, printers)
  for i, result in enumerate(results):
    print(f"Case #{i+1}: ", end='')
    print(*result)


if __name__ == "__main__":
  main()
