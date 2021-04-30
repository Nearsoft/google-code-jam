class Case:

    def __init__(self, list_length, my_list):
        self.list_length = list_length
        self.my_list = my_list
        self.cost = 0

    def compute_cost(self):

        for i in range(self.list_length-1):

            min_idx = self.my_list.index(\
            min(self.my_list[i:]))                  #Obtaining the index with the minimum value
            sublist = self.my_list[i:min_idx + 1]   #Obtaining the sublist that will be reversed
            sublist.reverse()
            self.my_list[i:min_idx+1] = sublist     #Inserting the reversed sublist in the main list

            self.cost += min_idx - i + 1            #Calculating the cost of reversing the sublist
            

        return self.cost

#End class Case


t = int(input())    #Reading the number of test cases

for i in range(1, t + 1):
    list_length = int(input())      #Reading the length of the list
    my_list = [int(j) for j in input().split()]     #Reading the elements of the list
    case = Case(list_length, my_list)               #Object that solves the problem
    print("Case #{}: {}".format(i, case.compute_cost()))    #printing the results



