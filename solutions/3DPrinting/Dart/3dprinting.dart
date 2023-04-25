// Google Code Jam doesn't allow `import 'dart:io';`. Now what? :C
import 'dart:io';
import 'dart:math';

int get_input_int()
{
    var my_input = stdin.readLineSync();
    if (my_input != null)
        return int.parse(my_input);
    else
        return 0;
}
String get_input_string()
{
    var my_input = stdin.readLineSync();
    if (my_input != null)
        return my_input;
    else
        return '';
}


List<List<int>> get_printers_input()
{
    List<List<int>> printers = [];
    for (int j = 0; j < 3; j++)
    {
        String input_line = get_input_string();
        List<String> input_split = input_line.split(' ');
        List<int> input_ints = [
            int.parse(input_split[0]),
            int.parse(input_split[1]),
            int.parse(input_split[2]),
            int.parse(input_split[3])
        ];
        printers.add(input_ints);
    }
    return printers;
}


List<dynamic> give_color_x(List<List<int>> printers, int color_pos)
{
    return <dynamic>[
        printers[0][color_pos],
        printers[1][color_pos],
        printers[2][color_pos]
        ];
}

List<int> return_solution(List<List<int>> printers)
{
    List<int> my_colors = [0, 0, 0, 0];
    for (int i = 0; i < 4; i++)
    {
        num min_units = give_color_x(printers, i).cast<num>().reduce(min);
        my_colors[i] = min_units.toInt();
        int sum_of_units = my_colors.reduce((a, b) => a + b);

        if (sum_of_units >= 1000000)
        {
            int difference = sum_of_units - 1000000;
            my_colors[i] = my_colors[i] - difference;
            return my_colors;
        }
    }
    return my_colors;
}


void print_solution(int test_case, List<int> result)
{
    stdout.write('Case #${test_case}: ');
    int sum_of_units = result.reduce((a, b) => a + b);
    if (sum_of_units == 1000000)
    {
        for (int i = 0; i < 4; i++)
        {
            stdout.write(result[i]);
            stdout.write(' ');
        }
        stdout.write('\n');
    }
    else
    {
        print("IMPOSSIBLE");
    }
}

void main() {
    int Times = get_input_int();

    for (int i = 1; i <= Times; i++)
    {
        List<List<int>> my_printers = get_printers_input();
        
        List<int> result = return_solution(my_printers);
        print_solution(i, result);
    }
}
