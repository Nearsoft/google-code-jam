#define the function used to build the cards
def card_builder(row, col):
  
  # We create two lists that will serve as our rows
  row_a = [] 
  row_b = []
  
  # Special rows that will have elements replaced by dots
  row_x = []
  row_y = []
  
  i = 0 
  
  while i <= col:
    if i == 0: # To draw the leftmost column
      row_a.append("+")
      row_b.append("|")
      row_x.append("+")
      row_y.append("|")
    else: # This will draw every other column
      row_a.append("-+")
      row_b.append(".|")
      row_x.append("-+")
      row_y.append(".|")
    i += 1

  # Replacing the firs values with dots
  row_x[0] = "."
  row_x[1] = ".+"
  row_x.append("\n")
  
  row_y[0] = "."
  row_y[1] = ".|"
  row_y.append("\n")
  
  # We add line breaks
  row_a.append("\n")
  row_b.append("\n")
  
  # We add the first two rows 
  printable = []
  printable.append("".join(row_x))
  printable.append("".join(row_y))
  
  j = 1 # Will keep track of how many rows have been drawn
  
  while j < row:
    printable.append("".join(row_a))
    printable.append("".join(row_b))
    j += 1
  
  printable.append("".join(row_a)) # We print this once more to close off cells left open
  
  return "".join(printable)


# Receive the number of tests and use a variable to count them
tests = int(input())
case = 1

# Using a while loop to call the function once for each test
while tests > 0: 
    current_input = input()
    row_col = current_input.split()
    
    r = int(row_col[0])
    c = int(row_col[1])
    
    result = card_builder(r, c)
    
    print(f"Case #{case}: \n{result}")
    case += 1
    tests -= 1