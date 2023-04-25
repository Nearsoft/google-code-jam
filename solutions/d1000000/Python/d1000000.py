"""
Name: d1000000.py
Last update: 18/04/2023
"""

import heapq

def get_used_dice(dice_bucket : list) -> int:
    used_dice = 0
    target_die = 1
    # Greedy approach.
    while len(dice_bucket) > 0:
        current_die = heapq.heappop(dice_bucket)
        if current_die >= target_die:
            used_dice += 1
            target_die += 1
        

    return used_dice
        

if __name__ == '__main__':
    testCases = int(input())

    for testCase in range(testCases):
        dice = int(input())
        # Keep track of the times a n sides die appears. 
        input_dice = [int(x) for x in input().split(' ')]
        # Using a heap to sort the input dice.
        heapq.heapify(input_dice)

        used_dice = get_used_dice(input_dice)
        print(f'Case #{testCase + 1}: {used_dice}')