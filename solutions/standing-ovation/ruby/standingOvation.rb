def getResults(args)
    lines = args.split("\n")
    results = []

    return "Error" unless isTestCasesQuantityValid(lines)

    for i in 1...lines.length
      line = lines[i]
      return "Error" unless isTestCaseValid(line)

      results.push(getTestCaseResult(line))
    end

    results
end

def isTestCasesQuantityValid(lines)
  testCasesQuantity = Integer(lines[0])
  testCasesQuantity >= 1 && testCasesQuantity <= 100 && lines.length - 1 == testCasesQuantity
end

def isTestCaseValid(testCase)
  testCaseLine = testCase.split(" ")
  maxShyness = Integer(testCaseLine[0])
  audienceString = testCaseLine[1]

  maxShyness == audienceString.length - 1 && maxShyness >= 0 && maxShyness <= 1000
end

def getTestCaseResult(testCase)
        maxShyness = Integer(testCase.split(" ")[0])
        audience = testCase.split(" ")[1].split("")

        standingPeopleCount = 0
        friendsCount = 0

        for shynessLevel in 0..maxShyness
            peopleCount = Integer(audience[shynessLevel])
            if shynessLevel > standingPeopleCount
                friendsNeeded = shynessLevel - standingPeopleCount
                friendsCount += friendsNeeded
                standingPeopleCount += friendsNeeded + peopleCount
            else
                standingPeopleCount += peopleCount
            end
        end

        friendsCount
end

file = File.open("./A-small-practice.in", "r")
contents = file.read

results = getResults(contents)
results.each_with_index do |result, i|
  puts "Case ##{i + 1}: #{result}"
end
