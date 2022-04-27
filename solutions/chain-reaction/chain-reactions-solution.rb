T = gets.to_i

for t in 1..T
  n = gets.to_i
  fun = gets.split(" ").map(&:to_i)
  points = gets.split(" ").map(&:to_i)
  mod_num = fun.length;
  children_ref = []
  nodes = []
  stack = []
  answer = 0

  #Initialize and get children
  for i in 0..mod_num-1
    children_ref[i] = []
  end
  for i in 0..mod_num-1
    children_ref[points[i]].push(i+1);
  end

  #Build nodes
  for i in 0..mod_num-1
    node = {
  index: i+1,
  fun: fun[i],
  parent: points[i],
  children: children_ref[i+1],
  branches: []
    }
    nodes.push(node)
  end

  #Push leaves to stack
  for i in 0..mod_num-1
    if nodes[i][:children].nil?||nodes[i][:children].length==0
        stack.push(nodes[i])
    end
  end 
  #Iterate through the leaves
    while stack.length>0
        curr_node = stack.pop()
        parent =  nodes[curr_node[:parent]-1]
        #If leaf has no parents just add its fun value to the answer
        if parent.nil?||curr_node[:parent] == 0
            answer += curr_node[:fun]
        #If the parent just has One child (the current one), push the parent
        #as a leaf to the stack with an updated fun value and no children
        elsif parent[:children].length == 1
            parent[:fun] = [parent[:fun],curr_node[:fun]].max
            parent[:children] = []
            stack.push(parent)
        #Else, the parent has multiple children, save the possible branches in 
        #the parent and choose a branch with min value, compare it with parent
        # and push updated parent, the other branches will be pushed as leaves with
        #no parents
        else
            parent[:branches].push([curr_node[:index],curr_node[:fun]])
            if parent[:branches].length==parent[:children].length
                chosen_branch = 0;
                min_value = 1_000_000_005
                for i in 0..parent[:branches].length-1
                    branch = parent[:branches][i]
                    if branch[1]<min_value
                        chosen_branch = i
                        min_value = branch[1]
                    end
                end
                for i in 0..parent[:branches].length-1
                    branch = parent[:branches][i]
                    if chosen_branch!=i
                        nodes[branch[0]-1][:parent]=0
                        stack.push(nodes[branch[0]-1])
                    else
                        parent[:children] = []
                        parent[:fun] = [branch[1],parent[:fun]].max
                        stack.push(parent)
                    end
                end
            end
        end
    end
  puts "Case #" + t.to_s + ": "+answer.to_s + "\n"
end
