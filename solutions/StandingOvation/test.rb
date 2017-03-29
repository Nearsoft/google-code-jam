class Shyness

	input = File.open('A-small-practice.in')
	out = File.new('A-small-practice.out', 'w')
	

	#Array for the members of the audience
    arrayAudience = []

	#Variables
    #testCase - It holds the number of test cases
    #maxShyness - It holds the max level of Shyness for each testcase
    #numStanding - It holds the number of people already standing
    #numFriends - It holds the total number of friends to invite
    #lastShynessCovered - It holds the last j level of shyness covered with the people standing
    #personNeeded - It holds the person needed on each Sj to cover the j shyness level
    
	arrayAudience = 0
	testCase = 0
	maxShyness = 0
	numStanding = 0
	numFriends = 0
	lastShynessCovered = 0
	personNeeded = 0

	#Read the first line and assign to current line
    currentLine = input.readline.to_s

    #Get the test cases into testCase variable from the first line
    testCase = currentLine.to_i

    i = 0
	while i <= testCase-1
		
		#Read the next document's line
        currentLine = input.readline.to_s

        #Reset (or set) the control variables
        numStanding = 0;
        numFriends = 0;
        lastShynessCovered = 0;
        personNeeded = 0;

        #Get the maxShyness from the first index (0) result of split the current line with blank spaces delimiter
        maxShyness = (currentLine.split(" ")[0]).to_i

        
        #Get the array of audience members from the previos split, plus a split without delimiter
        arraySplit = currentLine.split(" ")
        arrayAudience = arraySplit[1].split("")

        j = 0
		while j <= maxShyness


			#If the standing people is greater or equal than the j shyness level
            if numStanding >= j
            
                #Mark the current j shyness level as covered
                lastShynessCovered = j
                
                #If the j shyness level has 0 people to stand up, just continue
                if arrayAudience[j] == 0 
                    next
                end


            else #If the standing people is smaller than the j shyness level
            
                #If the amount of people in the j shyness evel is 0, just continue
                if arrayAudience[j] == 0 
                    next
                end

                #Obtain the person needed to cover the j shyness level
                personNeeded = j - numStanding
                    
                #Sum the person needed to the audience in the last j shyness level covered
                sumPersonsNeeded =  arrayAudience[lastShynessCovered].to_i + personNeeded
                arrayAudience[lastShynessCovered] = sumPersonsNeeded.to_s
                    
                #Sum the person needed to the person already standing
                numStanding += personNeeded
                    
                #Sum the person needed to the number of friends
                numFriends += personNeeded

            end

            #Sum the amount of people standing in the j shyness level covered to the number of already standing people
           	numStanding += arrayAudience[j].to_i

           	j += 1

        end

        out.puts "Case #" + (i+1).to_s + ": " + numFriends.to_s

		i += 1

	end

end




