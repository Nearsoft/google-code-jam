#/usr/bin/perl
use strict;
use warnings;
sub TroubleSortVerification{
    my @arraySorted = @_;
    my $length = scalar(@arraySorted);
    for(my $i = 0; $i <= $length -1; $i++){
        if($i == $length-1){
            return "OK";
        }else{
            if($arraySorted[$i] > $arraySorted[$i+1]){
                return $i;
            }
        }
    }
}

my $testCases= readline(STDIN);

for(my $i=1; $i <= $testCases; $i++){
	my $size = readline(STDIN);
	my $arrayToSort = readline(STDIN);
	my @values = split(" ",$arrayToSort);
	my @Odds=();
	my @Evens=();
	for(my $j=0; $j <$size; $j++){
		if($j%2 == 0){
		        push(@Evens, $values[$j]);
		}else{
		        push(@Odds, $values[$j]);
		}
	}
    print "Evens @Evens\n";
    print "Odds @Odds\n";
	#my @EvensArray = sort @Evens;
    my @EvensArray = sort { $a <=> $b } @Evens;
	#my @OddsArray = sort @Odds;
    my @OddsArray = sort { $a <=> $b } @Odds;
    print "Evens ordenados @EvensArray\n";
    print "Odds ordenados @OddsArray\n";
	my @arraySortedC =();
	my $m=0;
	my $n=0;
	for(my $j = 0; $j < $size; $j++){
		if($j%2 == 0){
			push(@arraySortedC, $EvensArray[$m]);
			$m++;
		}else{
		        push(@arraySortedC, $OddsArray[$n]);
		        $n++;
		}
	}
    print "Arreglo completo @arraySortedC\n";
		my @resultTestCase = TroubleSortVerification(@arraySortedC);
		print "Case #$i: @resultTestCase\n";
}
