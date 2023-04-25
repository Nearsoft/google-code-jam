/**
Name: punchedCard.dart
Last update: 18/04/2023
*/

import 'dart:io';

/// Build a String, which represents the punched card.
///
/// Throws an [ArgumentError] if the value of [cols] or [rows]
/// is less than 1. Returns the punched card.
String buildPunchedCard(int cols, int rows) {
	if (cols < 1 || rows < 1) {
		throw ArgumentError.value("The columns and rows must be greater than 0");
	}

	cols = 2 * cols + 1;
	rows = 2 * rows + 1;
	
    String punchedCard = "";
    bool evenRow = false;
    bool evenCol = false;

    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            /// Aligning top row cells
            if (i == 0 && j == 0 ||
                i == 0 && j == 1 ||
                i == 1 && j == 0 ||
                i == 1 && j == 1) {
                punchedCard += ".";
            } else if (evenRow == true && evenCol == true) {
                punchedCard += "|";
            } else if (evenRow == true && evenCol == false) {
                punchedCard += ".";
            } else if (evenRow == false && evenCol == true) {
                punchedCard += "-";
            } else {
                punchedCard += "+";
            }
            evenCol = !evenCol;
        }

        punchedCard += "\n";
        evenRow = !evenRow;
    }

    return punchedCard;
}

void main() {
    var testCasesS = stdin.readLineSync();
    int testCases = testCasesS != null ? int.parse(testCasesS) : 0;

    for (int i = 0; i < testCases; i++) {
        // Getting the punched card size in the current test case.
        var rowsS = stdin.readLineSync();
        var colsS = stdin.readLineSync();

        int cols = colsS != null ? int.parse(colsS) : 0;
        int rows = rowsS != null ? int.parse(rowsS) : 0;

        String punchedCard = buildPunchedCard(cols, rows);

        stdout.write("Case #${i + 1}:\n${punchedCard}");
    }
}
