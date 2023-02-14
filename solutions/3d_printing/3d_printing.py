def find_same_color(colors_column):
  c_min = min(colors_column[0])
  m_min = min(colors_column[1])
  y_min = min(colors_column[2])
  k_min = min(colors_column[3])
  
  minimum = [c_min, m_min, y_min, k_min]
  sum = 0
  result = ""
  for i, m in enumerate(minimum):
      sum += m
      if sum == 1000000:
          result += str(minimum[i]) + " 0"*(3-i)
          return result
      if sum < 1000000:
          result += str(minimum[i]) + " "
      if sum > 1000000:
          result += str(m - sum + 1000000) + " 0"*(3-i)
          return result 
  return "IMPOSSIBLE"

def main():
  printers = 3
  tests = int(input())
  for i in range(tests):
    colors_column = [[], [], [], []]
    for j in range(printers): 
      colors = input().split(" ")
      for k, color in enumerate(colors):
        colors_column[k].append(int(color))
    print(f"Case #{i+1}: {find_same_color(colors_column)}")

main()
