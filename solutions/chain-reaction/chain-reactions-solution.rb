$respuesta_chida = 0
T = gets.to_i
def dfs(curr_node, fun, mod)
  if (!mod[curr_node].nil?)
    min_val = 1000000001
    sum = 0
    for child in mod[curr_node]
        aux = dfs(child, fun, mod).to_i
        sum += aux.to_i
        if aux < min_val
            min_val = aux
        end
    end 
    $respuesta_chida += sum - min_val
    return [min_val, fun[curr_node]].max
  end 
  return fun[curr_node]
end
for t in 1..T
  $respuesta_chida = 0
  n = gets.to_i
  fun = [0] + gets.split(" ").map(&:to_i)
  mod = []
  points = gets.split(" ").map(&:to_i)
  i = 1 
  for p in points
    mod[p] ||= []
    mod[p] << i
    i += 1
  end
  puts "Case #" + t.to_s + ": " + (dfs(0, fun, mod) + $respuesta_chida).to_s + "\n"
end