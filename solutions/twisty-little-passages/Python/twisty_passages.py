def solution():
    """
    We will teleport to a randomly-chosen subset of the rooms, calculate the
    average degree (number of adjoining passages) of those rooms we've visited,
    and assume that this is a good estimate of the average degree of all the
    rooms. The number of passages is half the sum of the room degrees (since
    each passage connects two rooms.)
    """
    import random
    
    test_cases = int(input())
    
    for _ in range(test_cases):
        nu_rooms, nu_actions = map(int, input().split(' '))
    
        rooms_not_seen = list(range(1, nu_rooms + 1))
        nu_passages_seen = []

        current_room, nu_passages = map(int, input().split(' '))

        # Remove the current room from the list and append the passages number.
        del rooms_not_seen[current_room - 1]
        nu_passages_seen.append(nu_passages)

        # Shuffle the rooms list and select the first K rooms.
        random.shuffle(rooms_not_seen)
        rooms_not_seen = rooms_not_seen[:nu_actions]

        # TP to every room and append the number of passages they have.
        for room in rooms_not_seen:
            print(f"T {room}")
    
            current_room, nu_passages = map(int, input().split(' '))
            nu_passages_seen.append(nu_passages)

        # Compute the average of passages for one room
        # >>> result = sum(nu_passages_seen) / len(nu_passages_seen),
        # multiply it with the total amount of rooms
        # >>> result = result * nu_rooms
        # and divide by two, because each passage is counted twice
        # >>> result = result / 2
        result = nu_rooms * sum(nu_passages_seen)
        result = result / (2 * len(nu_passages_seen))
        print(f"E {int(result)}")


if __name__ == '__main__':
    solution()
