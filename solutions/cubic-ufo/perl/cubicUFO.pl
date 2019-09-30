#/usr/bin/perl

use strict;
use warnings;
use Math::Trig;
use Math::Complex;
use feature qw/say/;

#open the file


my $numTest = readline(STDIN);
my @areaValues = (); 


for (my $i = 0; $i < $numTest; $i++) {
      my $area = readline(STDIN);
      push(@areaValues,$area);
} 

for (my $i = 0; $i < scalar(@areaValues); $i++) {

    #the value of the area start at line 2
    my $area = scalar($areaValues[$i]);

    my $beta = asin($area/ (2*sin(deg2rad(60)) ));
    my $radianes = deg2rad(35.2643896);
    my $theta = $beta - $radianes; #(asin(1/sqrt(3))

    my @rotationX = (
    [ (0.5*sin(deg2rad(45)) ),  (0.5*sin(deg2rad(45)) * sin($theta) ), (-0.5*sin(deg2rad(45))*cos($theta))], #X
    [0,0.5*cos($theta),0.5*sin($theta)],                                                            #Y
    [0.5*sin(deg2rad(45)),-0.5*sin(deg2rad(45)) * sin($theta) ,0.5*sin(deg2rad(45))*cos($theta)],); #Z
    my $case = $i + 1;
    print "Case #$case:\n";
    for (my $i = 0; $i < 3; $i++) {
        for (my $j = 0; $j < 3; $j++) {
            print "\ $rotationX[$i][$j]";
        }
    print "\n";
    }   
    
} 



#The program will be executed number of lines -1





