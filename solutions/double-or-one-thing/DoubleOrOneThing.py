"""
This class: groups the letters and their respective amount.  
"""
class groupOfLetters():

    def __init__(self):
        self.letter = []
        self.conter = []
    
    def saveLetter(self, letter):
        self.letter.append(letter)

    def saveconter(self, amount):
        self.conter.append(amount)

"""
This function receives the input to separate every letter and send them
 to groupOfLetters class. Then it receives the data to make the new string 
 as a result. 
    conditions to make the string:
    *result should be in alphabetic order
        example inputs AAB ; BBA ; AAA 
        
    1-If the letter is smaller than the next letter, it should double
    the letter( or group of letters): result AAAAB

   2 -If the letter is  bigger than the next letter, it 
    doesn't double the letter( or group of letters): result BBA 

    3-If the string that we receive contains the same group of letters,
    it should result the same string: result AAA
"""
def doubleOrOneThing():
    word = str(input())
    result = ""
    conter = 1

    #change the input to uppercase
    word= word.upper()
    large = len(word)
    data = groupOfLetters()


    for i in range(large):
        j= i+1
        """
        condition to avoid the program breaks when index j is bigger than 
        the array's length
        """ 
        if(j < (large )):
            #if the comparision it's the same, we count how many times 
            #the letter is repeating
            if(word[i] == word[j]):
                conter += 1
            else:
                #if the letter changed, we save the data with the last letter
                # and how many times it was repeated.
                data.saveLetter(word[i])
                data.saveconter(conter)
                #reset the conter
                conter = 1
        else:
            # if j is bigger than the array's lenght we save the last data
            data.saveLetter(word[i])
            data.saveconter(conter)

    #print(data.letter)
    #print(data.conter)     

    #For to make the new string with double letters or not
    for i in range(len(data.letter)):
        j= i+1
        # condition 3: Is the same letter in the whole word
        #we break the "for" and save time. 
        if(i == len(data.letter)):
            result = str(data.letter[i] * data.conter[i])
            break
        """
        condition to avoid the program breaks when index j is bigger than 
        the array's length
        """ 
        if (j < len(data.letter)):
            #condition 1: double letter
            if(data.letter[i] < data.letter[j]):
                result += str(data.letter[i] * (data.conter[i] * 2))
            else:
                #condition 2: not double letter
                result += str(data.letter[i] * data.conter[i])
        else:
            #the last data doesn't double because there's nothing to compare
            #it means it is the biggest like condition 2
            result += str(data.letter[i] * data.conter[i])


    return result;
            

cases = int(input())
results = []

#Program stars here:
#loop into the number of cases
for case in range(cases):
    results.append(doubleOrOneThing())

#Final step
#loop into the received answers in the list to print 
for i in range(cases):
    print(f"Case #{i + 1}: {results[i]}")