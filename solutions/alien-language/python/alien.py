import re

__author__ = 'igomez'


def words_match(language, pattern):
    pattern = pattern.replace("(", "[")
    pattern = pattern.replace(")", "]")

    return sum(1 if re.search(pattern, word) else 0 for word in language)


if __name__ == '__main__':
    with open("A-large-practice.in", "r") as inputfile:
        config = inputfile.readline().split()

        words = [inputfile.readline() for i in xrange(int(config[1]))]
        results = [("Case #%s: %d\n" % (i + 1, words_match(words, inputfile.readline()))) for i in
                   xrange(int(config[2]))]
        outputfile = open("output.out", "w")
        outputfile.writelines(results)
        outputfile.close()
