$RICHARD = "RICHARD"
$GABRIEL = "GABRIEL"

def even_n_r(x, r, c)
  return x < 7 && r >= x && (r * c) % x == 0 && x % 2 == 0 && c >= x / 2
end

def even_n_c(x, r, c)
  return x < 7 && c >= x && (r * c) % x == 0 && x % 2 == 0 && r >= x / 2
end

def odd_n_r(x, r, c)
  return x < 7 && r >= x && (r * c) % x == 0 && x % 2 != 0 && c >= (x / 2) + 1
end

def odd_n_c(x, r, c)
  return x < 7 && c >= x && (r * c) % x == 0 && x % 2 != 0 && r >= (x / 2) + 1
end

def predict_who_wins(x, r, c, test_case)
  winner = $RICHARD

  if even_n_r(x, r, c)
    winner = $GABRIEL
  end

  if even_n_c(x, r, c)
    winner = $GABRIEL
  end

  if odd_n_r(x, r, c)
    winner = $GABRIEL
  end

  if odd_n_c(x, r, c)
    winner = $GABRIEL
  end

  output = 'Case #' + "#{test_case}: #{winner}\n"

  open("./large_dataset.out", "a") do |f|
    f.puts output
  end
end

File.open("./D-large-practice.in", "r") do |f|
  f.each_with_index do |line, index|
    if index == 0
      next
    end

    line_arr = line.split(" ")
    x = line_arr[0].to_i
    r = line_arr[1].to_i
    c = line_arr[2].to_i

    predict_who_wins(x, r, c, index)
  end
end
