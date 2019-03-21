t = gets.to_i

for testCase in 0..t -1
    n=gets.to_i
    r=gets.to_i
    c=gets.to_i

    if r*c%c != 0
        printf "Case #%d: RICHARD\n", (testCase + 1)
    elsif n>=7
        printf "Case #%d: RICHARD\n", (testCase + 1)
    elsif n==6
        if [r,c].min() <=3
            printf "Case #%d: RICHARD\n", (testCase + 1)
        else
            printf "Case #%d: GABRIEL\n", (testCase + 1)
        end
    elsif n==5
        if [r,c].min<=2 or m[r,c].min==2 or [r,c].max==5
            printf "Case #%d: RICHARD\n", (testCase + 1)
        else
            printf "Case #%d: GABRIEL\n", (testCase + 1)
        end
    elsif n==4
        if [r,c].min<=2
            printf "Case #%d: RICHARS\n", (testCase + 1)
        else
            printf "Case #%d: GABRIEL\n", (testCase + 1)
        end
    elsif n==3
        if [r,c].min<=1
            printf "Case #%d: GABRIEL\n", (testCase + 1)
        else 
            printf "Case #%d: GABRIEL\n", (testCase + 1)
        end
    else
        printf "Case #%d: GABRIEL\n", (testCase + 1)
    end
end