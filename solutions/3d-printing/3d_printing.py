# Function that determines the amount of ink needed for a color
def get_ink(ink_sum, current_value):
    if ink_sum >= 1e6: # If the sum is greater than 10e6, we don't need that color
        return 0
    # If the sum is less than 10e6, we need that color to reach 10e6
    elif current_value >= (1e6 - ink_sum):
        # If there's enough ink for 10e6 - sum, we're return 1e6 - ink_sum
        return 1e6 - ink_sum 

# Function that calculates the necessary amount of ink per color
# If the case is impossible it returns null
# If the case is possible, it returns a list of ink amounts
def compute_ink(printer_1, printer_2, printer_3):
    # We get the minimum amount of ink available for each color
    colors = {
        "C": min(printer_1[0], printer_2[0], printer_3[0]),
        "M": min(printer_1[1], printer_2[1], printer_3[1]),
        "Y": min(printer_1[2], printer_2[2], printer_3[2]),
        "K": min(printer_1[3], printer_2[3], printer_3[3])
    }
    
    # We check if the case is possible
    # If the sum of the minimum amount of ink for each color doesn't reach
    # 10e6, that means there's no way each printer can print the image
    if colors['C'] + colors['M'] + colors['Y'] + colors['K'] < 1e6: 
        return 
    else:
        # The case is possible
        # We can use math to determine the amounts of color we need
        for color, value in colors.items():
            if colors['C'] + colors['M'] + colors['Y'] + colors['K'] == 1e6: # If the sum is 10e6, we're done
                return [colors['C'], colors['M'], colors['Y'], colors['K']]

            elif color == "C": # If the color is cyan, we sum the rest
                ink_sum = colors['M'] + colors['Y'] + colors['K']
                colors['C'] = get_ink(ink_sum, value)

            elif color == "M":
                ink_sum = colors['C'] + colors['Y'] + colors['K'] 
                colors['M'] = get_ink(ink_sum, value)
                    
            elif color == "Y": 
                ink_sum = colors['C'] + colors['M'] + colors['K']
                colors['Y'] = get_ink(ink_sum, value)

            elif color == "K":
                ink_sum = colors['C'] + colors['M'] + colors['Y']
                colors['K'] = get_ink(ink_sum, value)
                    
# Function that solves a test case
def solve(num_case):
    printer_1 = list(map(int, input().split()))
    printer_2 = list(map(int, input().split()))
    printer_3 = list(map(int, input().split()))
    
    answer = compute_ink(printer_1, printer_2, printer_3)
    
    print('Case #{}:'.format(num_case), end=' ')
    
    if answer:
        print(*map(int, answer))

    else:
        print('IMPOSSIBLE')

# Main function
def main():
    num_cases = int(input())
    
    for i in range(1, num_cases + 1):
        solve(i)
        
if __name__ == "__main__":
    main()