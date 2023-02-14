# ESAb ATAd

Last year, a research consortium had some trouble with a distributed database system that sometimes lost pieces of the data. You do not need to read or understand that problem in order to solve this one!

The consortium has decided that distributed systems are too complicated, so they are storing B bits of important information in a single array on one awesome machine. As an additional layer of security, they have made it difficult to obtain the information quickly; the user must query for a bit position between 1 and B, and then they receive that bit of the stored array as a response.

Unfortunately, this ultra-modern machine is subject to random quantum fluctuations! Specifically, after every 1st, 11th, 21st, 31st... etc. query is sent, but before the response is given, quantum fluctuation causes exactly one of the following four effects, with equal probability:

- 25% of the time, the array is complemented: every 0 becomes a 1, and vice versa.
- 25% of the time, the array is reversed: the first bit swaps with the last bit, the second bit swaps with the second-to-last bit, and so on.
- 25% of the time, both of the things above (complementation and reversal) happen to the array. (Notice that the order in which they happen does not matter.)
- 25% of the time, nothing happens to the array.

Moreover, there is no indication of what effect the quantum fluctuation has had each time. The consortium is now concerned, and it has hired you to get its precious data back, in whatever form it is in! Can you find the entire array, such that your answer is accurate as of the time that you give it? Answering does not count as a query, so if you answer after your 30th query, for example, the array will be the same as it was after your 21st through 30th queries.

More details: https://codingcompetitions.withgoogle.com/codejam/round/000000000019fd27/0000000000209a9e
