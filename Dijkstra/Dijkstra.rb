#!/usr/bin/env ruby

def seekij(word)
    tmpi = "1";
    tmpj = "1";

    splitted = word.split("");
    
    i = 0;
    j = 0;

    wl = word.length
    
    while i < wl
            
        tmpi = quaternionMult(tmpi, splitted[i]);

        if tmpi.eql? "i"
                
            j = i + 1;
                
            while j < wl
            
                tmpj = quaternionMult(tmpj, splitted[j])

                if tmpj.eql? "j"
                    return true
                end
                j += 1
            end
        end
        i += 1
    end
    
    return false

end



def quaternionMult(a, b)
    indx = {"1" => 0,
            "i" => 1,
            "j" => 2,
            "k" => 3,
            "-1" => 4,
            "-i" => 5,
            "-j" => 6,
            "-k" => 7}
    
    mult = [["1", "i", "j", "k", "-1", "-i", "-j", "-k"],
            ["i", "-1", "k", "-j", "-i", "1", "-k", "j"],
            ["j", "-k", "-1", "i", "-j", "k", "1", "-i"],
            ["k", "j", "-i", "-1", "-k", "-j", "i", "1"],
            ["-1", "-i", "-j", "-k", "1", "i", "j", "k"],
            ["-i", "1", "-k", "j", "i", "-1", "k", "-j"],
            ["-j", "k", "1", "-i", "j", "-k", "-1", "i"],
            ["-k", "-j", "i", "1", "k", "j", "-i", "-1"]]
    
    return mult[indx[a]][indx[b]]
end


def reduceWordTo1(word)
        
        splitWord = word.split("")
        n = splitWord.length
        reduced = splitWord[0]
    
        for i in 1..(n - 1) 
            reduced = quaternionMult(reduced, splitWord[i])
        end
        return reduced
end

def test(case_number)
    
    input1 = gets.chomp.split(" ") 
    l = input1[0].to_i
    x = input1[1].to_i

    input2 = gets.chomp
    
    word = ""
    
    while word.length < l * x
        word += input2
    end
    
    if !(reduceWordTo1(word).eql?("-1"))
        puts "Case ##{case_number}: NO"
    elsif seekij(word)
        puts "Case ##{case_number}: YES"
    else
        puts "Case ##{case_number}: NO"
    end
end


#puts "Show me the T:"
T = gets.chomp.to_i

for case_number in 1..T
    test(case_number)
end