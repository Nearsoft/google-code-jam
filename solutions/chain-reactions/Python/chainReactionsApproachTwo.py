import math
import sys

sys.setrecursionlimit(10**8)

output = 0

def get_max_fun_factor(module_chain : dict, current_module : int, roots : list):
    """Get the max fun function of the current node
    
    Args:
        module_chain: A dictionary, which contains all the modules in the forest.
        current_module: The index of the current module
        roots: A list, which contains the index of the root nodes (Point to the abyss)

    
    Returns:
        The max fun factor in the current node.
    """

    # Store the total fun factor in the forest.
    global output

    module = module_chain[current_module]

    # Store the max fun factor in the current node
    max_fun_factor = module["max_fun_factor"]


    """ In order to get the max fun fact in the forest,
    for each node that has more than 1 children, we're 
    gonna follow the path with the smaallest one, and 
    we're gonna add up the others to the total fun factor
    in order to increase the sum.
    """
    smallest_fun_factor = math.inf
    children_fun_factor_sum = 0
    
    if not module["children"]: 
        return max_fun_factor

    for child in module["children"]:
        child_fun_factor = get_max_fun_factor(module_chain, child, roots)
        children_fun_factor_sum += child_fun_factor
        smallest_fun_factor = min(smallest_fun_factor, child_fun_factor)
    
    output += children_fun_factor_sum - smallest_fun_factor

    module["max_fun_factor"] = max(smallest_fun_factor, max_fun_factor)
    max_fun_factor = module["max_fun_factor"]

    return max_fun_factor



if __name__ == "__main__":
    test_cases = int(input())

    for _ in range(test_cases):
        modules = int(input())
        fun_factors = [int(x) for x in input().split()]
        pointing = [int(x) for x in input().split()]
        module_chain = {}
        roots = set()
        output = 0

        # Creating the forest structure.
        for module in range(modules):
            module_chain[module + 1] = {
                "fun_factor" : fun_factors[module],
                "children" : [], 
                "max_fun_factor" : fun_factors[module],
            }

            if pointing[module] == 0: roots.add(module + 1)
            else: module_chain[pointing[module]]["children"].append(module  + 1)
        
        for root in roots:
            root_fun_factor = get_max_fun_factor(module_chain, root, roots)
            output += root_fun_factor

        print(f"Case #{_ + 1}: {output}")
