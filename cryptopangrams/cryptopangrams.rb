#!/usr/bin/env ruby

def cryptopangram(n, l, integers)
  # define alphabet
  alphabet = ['A', 'B', 'C', 'D', 'E', 'F', 'G','H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']

  # create a list for the ciphered primes and empty message
  primes = Array.new(l + 1)
  message = ""

  # calculate the gcd of the two first non equal ciphered primes
  index = -1
  for i in 0..(integers.size-1)-1
    if integers[i] != integers[i+1]
      factor = integers[i].gcd(integers[i+1])
      index = i+1
      primes[index] = factor
      break
    end
  end

  #calculates backpropagation
  for i in (index).downto(1)
    prev_factor = integers[i-1] / primes[i]
    primes[i-1] = prev_factor
  end

  #calculates frontpropagation
  for i in index..integers.size-1
    next_factor = integers[i] / primes[i]
    primes[i+1] = next_factor
  end

  # remove repetitions
  unique_primes = primes.uniq

  # copy into new list and sort it
  unique_primes_sorted = unique_primes.sort

  # map the sorted list to each letter in a dictionary
  prime_map = Hash[unique_primes_sorted.zip(alphabet)]

  # map the calculated factors to their respective letters
  primes.each { |prime|
    message += prime_map[prime]
  }

  return message
end

##############################################################################

if __FILE__ == $0

  # CASE 1

  puts "\nSTART CASE 1"

  n = 103
  l = 31
  integers = [217, 1891, 4819, 2291, 2987, 3811, 1739, 2491, 4717, 445, 65, 1079, 8383, 5353, 901, 187, 649, 1003, 697, 3239, 7663, 291, 123, 779, 1007, 3551, 1943, 2117, 1679, 989, 3053]

  message1 =  cryptopangram(n, l, integers)
  expected_msg1 = "CJQUIZKNOWBEVYOFDPFLUXALGORITHMS"

  puts ("Expected msg 1: ")
  puts (expected_msg1)
  puts ("Calculated msg 1: ")
  puts (message1)

  if message1 == expected_msg1
    puts "Test passed!"
  else
    puts "Test failed..."
  end

  # CASE 2

  puts "\nSTART CASE 2"

  n = 10000
  l = 25
  integers = [3292937, 175597, 18779, 50429, 375469, 1651121, 2102, 3722, 2376497, 611683, 489059, 2328901, 3150061, 829981, 421301, 76409, 38477, 291931, 730241, 959821, 1664197, 3057407, 4267589, 4729181, 5335543]

  message2 = cryptopangram(n, l, integers)
  expected_msg2 = "SUBDERMATOGLYPHICFJKNQVWXZ"

  puts ("Expected msg 2: ")
  puts (expected_msg2)
  puts ("Calculated msg 2: ")
  puts (message2)

  if message2 == expected_msg2
    puts "Test passed!"
  else
    puts "Test failed..."
  end

  # CASE 3

  puts "\nSTART CASE 3"

  n = 107
  l = 29
  integers = [15, 15, 15, 15, 21, 49, 77, 143, 221, 323, 437, 667, 899, 1147, 1517, 1763, 2021, 2491, 3127, 3599, 4087, 4757, 5183, 5767, 6557, 7387, 8633, 9797, 10403]

  message3 = cryptopangram(n, l, integers)
  expected_msg3 = "ABABACCDEFGHIJKLMNOPQRSTUVWXYZ"

  puts ("Expected msg 3: ")
  puts (expected_msg3)
  puts ("Calculated msg 3: ")
  puts (message3)

  if message3 == expected_msg3
    puts "Test passed!"
  else
    puts "Test failed..."
  end

end

