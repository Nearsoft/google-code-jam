"""
Solution to the Double or One thing 
from the Round 1A 2022 - Code Jam 2022

name: double_or_one_thing.py
problem link: https://codingcompetitions.withgoogle.com/codejam/round/0000000000877ba5/0000000000aa8e9c
"""

def double_or_one_thing(s:str='')->str:
    """Returns the string that comes first alphabetically 
    from the set of strings that can be produced from s
    by highlighting different characters in s.

    Args:
        s: A non-empty string. Each character of s is
          an uppercase letter from the English alphabet.   

    Returns:
        The string that comes first alphabetically 
        from the set of strings that can be produced from 
        s by highlighting different characters in S.
        For example:
        CODEJAMDAY -> CCODDEEJAAMDAAY

    Raises:
        TypeError: The s contains character that are not from 
            the English alphabet.
    """

    if not s.isalpha():
        raise TypeError('The string must be non-empty and all the character must be alphabetic.')
    
    if len(s) == 1:
        return s
    
    output = ''

    current_char = ''
    # Store the last character that was processed.
    last_char = s[0]
    # Store the amount of times the last character processed appeared, 
    # in case of similar contiguos characters.
    char_count = 1

    for i in range(1, len(s)):
        current_char = s[i]

        if ord(current_char) != ord(last_char):
            """If the last character(s) is smaller than the current, it appends the 
            previous character(s) highlighted to create a string lexicographically 
            smaller, else it only appends the previous character(s) without highlight."""
            output += (last_char * char_count) if ord(last_char) > ord(current_char) \
                else (last_char * (char_count * 2))
            last_char = current_char
            char_count = 1
        else:
            """Keep the count of how many similar characters have appeared in a row."""
            char_count += 1
    
    """Appends the last character(s) that appeared in the string, and it covers the case
    all the characters are the same."""
    output += last_char * char_count

    return output.upper()


if __name__ == '__main__':
    tests = int(input())

    for i in range(tests):
        test_string = input()
        test_output = double_or_one_thing(test_string)
        print(f'Case #{i+1}: {test_output}')