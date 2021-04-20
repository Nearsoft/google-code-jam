 <h1> Cheating Detection Problem </h1>

There are 100 players are competing in a 10000-question trivia tournament,  the players are numbered from 1 to 100. <br>
Player i has a skill level of Si [-3.00, 3.00] and question j has a difficulty level of Qj [-3.00, 3.00].<br>
When player i tries to answer question j, the probability that they answer it correctly is f(Si−Qj), where f is the sigmoid function.<br>
There is one exception: exactly one of the players is a cheater! The cheater will flip a coin (before answering a question) to decide wheather to cheat or not.<br>

 <h1> Input & Output </h1>
You are given the total number of test cases T, and the percentage P, of correct guesses to pass the challenge.
Each case consists of 100 lines of 10000 characters each. 
The j-th character on the i-th line is 1 if the i-th player answered the j-th question correctly, or 0 if they answered it incorrectly.
For each test case, output one line containing Case #x: y, where x is the test case number (starting from 1) 
and y is the number of the cheater (with player numbers starting from 1).

 <h1> Approach </h1>
Estimate the actual skill level and the actual difficulty of questions. 
Then, using those two, estimate the expected number of correct answers for each player in the extreme questions. 
The player with the largest difference between that estimation and the real value is the cheater.
