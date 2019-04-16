$RICHARD = "RICHARD"
$GABRIEL = "GABRIEL"

def even_n_r(x, r, c)
  if (x < 7 && r >= x && (r * c) % x == 0 && x % 2 == 0 && c >= x / 2)
    if (x > 3)
      return (((r * c) - x * (x / 2)) != 0 && ((r * c) - x * (x / 2)) % x == 0 && c > (x / 2))
    else
      return true
    end
  end
end

def even_n_c(x, r, c)
  if (x < 7 && c >= x && (r * c) % x == 0 && x % 2 == 0 && r >= x / 2)
    if (x > 3)
      return (((r * c) - x * (x / 2)) != 0 && ((r * c) - x * (x / 2)) % x == 0 && r > (x / 2))
    else
      return true
    end
  end
end

def odd_n_r(x, r, c)
  if (x < 7 && r >= x && (r * c) % x == 0 && x % 2 != 0 && c >= (x / 2) + 1)
    if (x > 3)
      return (((r * c) - x * ((x / 2) + 1)) != 0 && ((r * c) - x * ((x / 2) + 1)) % x == 0 && c > (x / 2))
    else
      return true
    end
  end
end

def odd_n_c(x, r, c)
  if (x < 7 && c >= x && (r * c) % x == 0 && x % 2 != 0 && r >= (x / 2) + 1)
    if (x > 3)
      return (((r * c) - x * ((x / 2) + 1)) != 0 && ((r * c) - x * ((x / 2) + 1)) % x == 0 && r > (x / 2))
    else
      return true
    end
  end
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

  open("./small_dataset.out", "a") do |f|
    f.puts output
  end
end

File.open("./D-small-practice.in", "r") do |f|
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
