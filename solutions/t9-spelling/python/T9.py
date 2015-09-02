__author__ = 'igomez'

t9_map = {"2": ["a", "b", "c", ],
          "3": ["d", "e", "f", ],
          "4": ["g", "h", "i", ],
          "5": ["j", "k", "l", ],
          "6": ["m", "n", "o", ],
          "7": ["p", "q", "r", "s", ],
          "8": ["t", "u", "v", ],
          "9": ["w", "x", "y", "z", ],
          "0": [" ", ], }


def to_t9(string):
    result = ''

    previous_digit = None

    for c in string:
        digit_map = {k: v for k, v in t9_map.iteritems() if c in v}
        digit = digit_map.keys()[0]
        letters = digit_map.values()[0]

        for i in xrange(len(letters)):
            if digit == previous_digit and i == 0:
                result += ' '
            result += digit
            if letters[i] == c:
                break

        previous_digit = digit

    return result


if __name__ == '__main__':
    with open("C-large-practice.in", "r") as inputfile:
        num_test_cases = inputfile.readline()
        results = []
        for j in xrange(int(num_test_cases)):
            word = inputfile.readline()
            results.append(("Case #%s: %s\n" % (j + 1, to_t9(word.rstrip("\n")))))

        outputfile = open("output.out", "w")
        outputfile.writelines(results)
        outputfile.close()
