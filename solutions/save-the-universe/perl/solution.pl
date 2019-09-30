use strict;
use warnings;

my $ln = 0;

while (<>) {
  if ($ln == 0) {
    if ($_ < 1 || $_ > 100) {
      print 'Number of cases excedes limits';
      exit;
    }
  } else {
    my $solution = solve_case($_);
    print "Case #$ln: $solution\n";
  }
  $ln++;
}

sub solve_case {
  my @spl = split(' ', $_);
  my $damage = int($spl[0]);
  my $program = $spl[1];
  my $length = length($program);

  if ($_ !~ /^\d+ [CS]+$/) {
    print "Alien's program has an incorrect format";
    exit;
  }

  if ($damage < 1 || $damage > 10**9) {
    print "Shield's defence too high";
    exit;
  }

  if ($length < 2 || $length > 30) {
    print "Alien's program has an invalid length";
    exit;
  }

  my @program = split(//, $program);
  my $hacks = 0;

  do {
    my $damage_calc = damage_calc(\@program);
    my @tmp_program = @program;

    if ($damage_calc <= $damage) {
      return $hacks;
    } else {
      hack(\@program);
      $hacks++;
    }

    return 'IMPOSSIBLE' if eq_arrays(\@program, \@tmp_program);
  } while(1);
}

sub damage_calc {
  my $total_damage = 0;
  my $damage = 1;
  my @program_array = @{$_[0]};

  for (my $i = 0; $i < scalar(@program_array); $i++) {
    if ($program_array[$i] eq 'S') {
      $total_damage += $damage;
    } else {
      $damage *= 2;
    }
  }

  return $total_damage;
}

sub hack {
  my $program_array = $_[0];

  for (my $i = scalar(@$program_array) - 1; $i > 0; $i--) {
    if (@$program_array[$i] eq 'S' && @$program_array[$i-1] eq 'C') {
      (@$program_array[$i], @$program_array[$i-1]) = (@$program_array[$i-1], @$program_array[$i]);
      last;
    }
  }
}

sub eq_arrays {
  my ($x, $y) = @_;
  my $lenght = scalar(@$x);

  for (my $i = 0; $i < $lenght; $i++) {
    return 0 if (@$x[$i] ne @$y[$i]);
  }

  return 1;
}
