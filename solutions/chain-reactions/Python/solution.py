def main():
    # Read number of cases
    cases = int(input())

    # Array of Results
    resultsArray = []

    # Read and solve each case
    for i in range(cases):
        resultsArray.append(solveTree())
    
    for i in range(cases):
        print(f'Case #{i+1}:', resultsArray[i])

def solveTree():
    N = int(input()) # Number of nodes
    F = [0] + [int(f) for f in str(input()).split(' ')] # List of fun factors + an extra 0 at the begining to store the abyss (root value)
    P = [int(p) for p in str(input()).split(' ')] # List of pointers 
    adj = [[] for i in range(N+1)] # Create an array of adjacency lists of all the nodes + 1 list for the abyss adj nodes

    for i in range(len(P)): # For each pointer 
        # Add the adj nodes to the node pointed
        adj[P[i]].append(i+1) # append the index + 1 as we added the abyss as a node,so adj[0] are the nodes pointing to the abyss then

    curr_fun = 0 # Initialice accumulated fun

    for curr_node in reversed(range(N)): # Start from the last node registered (reversed order)

        if len(adj[curr_node])==0: # If the current node has no adj nodes, they are initiators, go to the previous one in the list
            continue

        # otherwise, they are parents, so do the following
        min_node = min(adj[curr_node], key=F.__getitem__) # from the list of adj nodes of curr node, get the node with the minimum F to be activated first, this is like a resumed loop among all the adj nodes and their Fs

        F[curr_node] = max(F[curr_node], F[min_node]) # Compare parent node with the min_node. Update the fun factor of curr_node, conserve the one with the biggest F

        curr_fun += sum(F[nu] for nu in adj[curr_node] if nu != min_node) # Sum all the other children nodes Fs of curr_node to the total sum of fun

    curr_fun += F[0] # After summing all the resulting fun factors, we are only missing to sum the root value of the tree

    #print(type(curr_fun))

    return curr_fun

if __name__ == '__main__':
    main()