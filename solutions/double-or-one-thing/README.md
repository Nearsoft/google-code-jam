# Double or One thing

You are given a string of uppercase English letters. You can highlight any number of the letters (possibly all or none of them). The highlighted letters do not need to be consecutive. Then, a new string is produced by processing the letters from left to right: non-highlighted letters are appended once to the new string, while highlighted letters are appended twice.

Double or One Thing example.

For example, if the initial string is HELLOWORLD, you could highlight the H, the first and last Ls and the last O to obtain
HELLOWORLD ⇒ HHELLLOWOORLLD. 
Similarly, if you highlight nothing, you obtain HELLOWORLD, and if you highlight all of the letters, you obtain HHEELLLLOOWWOORRLLDD. Notice how each occurrence of the same letter can be highlighted independently.

Given a string, there are multiple strings that can be obtained as a result of this process, depending on the highlighting choices. Among all of those strings, output the one that appears first in alphabetical (also known as lexicographical) order.

Note: A string s appears before a different string t in alphabetical order if s is a prefix of t or if at the first place s and t differ, 
the letter in s is earlier in the alphabet than the letter in t. 

For example, these strings are in alphabetical order: CODE, HELLO, HI, HIM, HOME, JAM.

More details [here](https://codingcompetitions.withgoogle.com/codejam/round/0000000000877ba5/0000000000aa8e9c)