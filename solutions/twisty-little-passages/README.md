# Twisty Little Passages

## Problem 

You are investigating a cave. The cave has N
rooms. There are underground passages that bidirectionally connect some pairs of rooms. Each room has at least one passage connected to it. No passage goes from a room to itself, and no two rooms are connected by more than one passage.

When in a room, you can identify what room you are in and see how many passages it connects to, but you cannot distinguish the passages. You want to estimate the number of passages that exist in the cave. You are allowed to do up to K operations. An operation is either: be magically teleported to a room of your choice, or
walk through a random passage connected to the room you are in, taking you to the room at the other end of that passage.
When you decide to walk through a passage, you are unable to choose which one, because they are all alike. A passage is chosen for you uniformly at random.

You begin the investigation in an arbitrary room. Estimate the number of passages between rooms in the cave with at most K operations.

If E is your estimate and P is the actual number of passages, your solution is considered correct for a test case if and only if P⋅2/3≤E≤P⋅4/3.

To pass a test set, your solution must be correct for at least 90% of the test cases in that set.

More details [here](https://codingcompetitions.withgoogle.com/codejam/round/0000000000876ff1/0000000000a45fc0)