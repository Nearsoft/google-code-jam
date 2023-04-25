def give_me_last_equal(string: str, position: int) -> int:
    """
    Given a position in a string, output the last position such as:
    string[position] == string[position + 1] == ... == string[last_position].
    """
    test_letter = string[position]

    last_position = position
    while last_position < len(string) - 1 and test_letter == string[last_position + 1]:
        last_position += 1

    return last_position


def first_alphabetically(my_str: str) -> str:
    result = []

    current = 0
    while current < len(my_str) - 1:
        # If the current letter comes before the next letter, highlight it.
        if my_str[current] < my_str[current + 1]:
            result += my_str[current] * 2
            current += 1

        # If the current letter comes after the next letter, don’t highlight it.
        elif my_str[current] > my_str[current + 1]:
            result += my_str[current]
            current += 1

        else:
            # Search for the index of the first letter distinct from the
            # current letter.
            last_equal = give_me_last_equal(my_str, current)

            # If the string ends with repeating letters, don't highlight them.
            if last_equal == len(my_str) - 1:
                result += my_str[current] * (last_equal - current)
                break

            # If the current letters comes before, highlight all of them.
            elif my_str[last_equal] < my_str[last_equal + 1]:
                result += my_str[current] * (2 * (last_equal - current + 1))

            # If the current letters comes after, don't highlight them.
            else:
                result += my_str[current] * (last_equal - current + 1)

            # Walk `last_equal - current + 1` letters forward.
            current += last_equal - current + 1

    # The last letter will never be highlighted.
    result += my_str[-1]
    return ''.join(result)
    


times = input()
for i in range(int(times)):
    my_string = input()
    
    print(f"Case #{i + 1}: {first_alphabetically(my_string)}")
