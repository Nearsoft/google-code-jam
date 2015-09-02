__author__ = 'igomez'


def minimum_scalar_product(vector1, vector2):
    vector1.sort()
    vector2.sort(reverse=True)

    return sum(vector1[j] * vector2[j] for j in xrange(len(vector1)))


if __name__ == '__main__':
    with open("A-large-practice.in", "r") as inputfile:
        num_test_cases = inputfile.readline()
        results = []
        for i in xrange(int(num_test_cases)):
            inputfile.readline()
            first = [int(item) for item in inputfile.readline().split()]
            second = [int(item) for item in inputfile.readline().split()]

            results.append(("Case #%s: %d\n" % (i + 1, minimum_scalar_product(first, second))))

        outputfile = open("output.out", "w")
        outputfile.writelines(results)
        outputfile.close()
