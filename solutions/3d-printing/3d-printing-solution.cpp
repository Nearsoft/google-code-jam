#include <algorithm>
#include <iostream>
#include <sstream>
#include <stdio.h>
#include <vector>
using namespace std;

int main() {
  int num;
  cin >> num;
  int x = 1;
  int unidadesMaximas = 1000000;

  while (num > 0) {
    vector<int> cyan;
    vector<int> magenta;
    vector<int> yellow;
    vector<int> black;

    for (int i = 0; i < 3; i++) {
      
      int numbers[4];
      scanf("%i %i %i %i",&numbers[0],&numbers[1],&numbers[2],&numbers[3]);
      
      cyan.push_back(numbers[0]);
      magenta.push_back(numbers[1]);
      yellow.push_back(numbers[2]);
      black.push_back(numbers[3]);
    }

    int minCyan = *min_element(cyan.begin(), cyan.end());
    int minMagenta = *min_element(magenta.begin(), magenta.end());
    int minYellow = *min_element(yellow.begin(), yellow.end());
    int minBlack = *min_element(black.begin(), black.end());
    int sumMins = minCyan + minMagenta + minYellow + minBlack;

    if (sumMins == unidadesMaximas) {
      cout << "Case #" << x << ": " << minCyan << " " << minMagenta << " "
           << minYellow << " " << minBlack << '\n';

    } else if (sumMins < unidadesMaximas) {
      cout << "Case #" << x << ": IMPOSSIBLE" << '\n';

    } else {
      int minimos[] = {minCyan, minMagenta, minYellow, minBlack};
      
      for (int i = 0; i < 4; i++) {
        sumMins = 0;

        for (int j = i + 1; j < 4; j++) {
          sumMins += minimos[j];
        }

        if (sumMins >= unidadesMaximas) {
          minimos[i] = 0;
        }

        else {
          minimos[i] = unidadesMaximas - sumMins;
          break;
        }
      }
      cout << "Case #" << x << ": " << minimos[0] << " " << minimos[1] << " "
           << minimos[2] << " " << minimos[3] << '\n';
    }
    x++;
    num--;
  }
}