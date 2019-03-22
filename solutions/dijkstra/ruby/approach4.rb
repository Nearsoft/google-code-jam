#!/usr/bin/env ruby



def mul(a, b)
  mul_table = [[0,  0,  0,  0,  0],
               [0,  1,  2,  3,  4],
               [0,  2, -1,  4, -3],
               [0,  3, -4, -1,  2],
               [0,  4,  3, -2, -1]]
  sign = a * b > 0 ? 1 : -1
  return sign * mul_table[a.abs][b.abs]
end

def reduce(codes, times)
  value = codes.inject(1){ |value, code| mul(value, code) }
  return exponentiate(value, times)
end

def exponentiate(value, times)
  limit = times % 4
  power = 1
  while limit > 0 do
    power = mul(power, value)
    limit -= 1
  end
  return power
end

def viable_split(codes, times)
  maybe2 = 1
  maybe3 = 1
  while times > 0 do
    codes.map{|code|
      if maybe2 != 2 then
        maybe2 = mul(maybe2, code)
      elsif maybe3 != 3 then
        maybe3 = mul(maybe3, code)
      else
        return true
      end
    }
    times -= 1
  end
  return maybe2 == 2 && maybe3 == 3
end


#x = gets
#y = gets
#puts "x = #{x}"
#puts "y = #{y}"

test_cases_str = gets
test_cases = test_cases_str.to_i

for test in 1..test_cases do
  inputs_str = gets
  inputs = inputs_str.split.map{|str| str.to_i}
  test_L = inputs[0]
  test_X = inputs[1]
  inputs_str = gets
  test_I = inputs_str.split[0].chars.map{|ch| ch.ord - "i".ord + 2}

  test1 = reduce(test_I, test_X) == -1
  test2 = viable_split(test_I, [8,test_X].min)
  puts "Case ##{test}: #{test1 && test2 ? "YES" : "NO"}\n"
end
