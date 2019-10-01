use strict;
use warnings;
use IO::Handle;

autoflush STDOUT;
my $t = <>; 
for (1 .. $t){
    my $a = <>;
    (my $n, my $m) = &findSquareDimentions($a);
    

    my $result;
    my @resultSplitted;

    my %targets = ();
    for my $i (2 .. $n) {
        for my $j (2 .. $m){
            my $key = "$i $j";
            $targets{$key} = 9; 
        }
    }
    my $x = -1;
    my $y = -1;
    my $nextTarget; 
    my %alreadyPrepared = ();
    my $missingSquares;
    my $target;
    my $hits = 0;
    while (!($x == 0 && $y == 0)){
        my $highestMissingSquares = 0;
        for $target (keys %targets) {
            $missingSquares = $targets{$target};
            if ($missingSquares > $highestMissingSquares){
                $nextTarget = $target;
                $highestMissingSquares = $missingSquares;           
            }
        }

        print("$nextTarget\n");
      
        $result = readline();

        @resultSplitted = split / /, $result; 
        $x = $resultSplitted[0];
        $y = $resultSplitted[1];

        if(!exists($alreadyPrepared{"$x $y"})){
            $alreadyPrepared{"$x $y"} = 1;
            updateTargets(\%targets, $x, $y);
        }


    }
}

sub updateTargets {
    my $targets = $_[0];
    my $x = $_[1];
    my $y = $_[2];

    for my $target (keys %$targets){
        my @targetSplitted = split / /,$target;
        if (($targetSplitted[0]-1 <= $x && $x <= $targetSplitted[0]+1) && ($targetSplitted[1]-1 <= $y && $y <= $targetSplitted[1]+1 )){
            $$targets{$target} -= 1; 
        }
    }
}


sub findSquareDimentions {
    my $a = $_[0];
    my $smallestDifference = $a;
    my $n;
    my $m;
    for my $i (1 .. int($a/2) - 1){
        if (($a % $i == 0 ) && (abs($i - int($a / $i)) < $smallestDifference)){
            $smallestDifference = abs($i - int($a/$i));
            $n = $i;
            $m = int($a/$i);
        }
    }

    if ($n < 3 || $m < 3){ 
        return findSquareDimentions($a+1);
    }
    return $n, $m;
}

# sub calculateHit{
#     my $targets = $_[0];
#     my $x = $_[1];
#     my $y = $_[2];
#     my $done = 1;

#     for my $target (keys $targets) {
#         if ($$targets{$target} > 0){
#             $done = 0;
#         } 
#     }
#     if ($done){
#         return ("0 0");
#     }

#     my $x_prepared =  int(rand(3)+$x-1);
#     my $y_prepared =  int(rand(3)+$y-1);

#     return ("$x_prepared $y_prepared");
# }