
import java.awt.Point;
import java.util.Scanner;

public class Solution {
	static Scanner input;
	public static void main(String[] args) {
		input = new Scanner(System.in);
		int TestCases = input.nextInt();
		for(int t=1;t<=TestCases;t++) {
			int desiredArea = input.nextInt();
			int columns = Math.max(3,(desiredArea+2)/3);
			int[] arrColumnSpaceUsed = new int[columns+2];
			int pointerUnfinishedColumn = 1;
			int area = 0;
			
			boolean[][] seen = new boolean[4][columns+4];
			while(area < columns*3) {
				while(arrColumnSpaceUsed[pointerUnfinishedColumn] == 3)
					pointerUnfinishedColumn++;
				int nextColumn = pointerUnfinishedColumn+1;
				nextColumn = Math.min(nextColumn, columns-1);
				Point res = prepare(2, nextColumn);
				if(res.x == 0 && res.y == 0)
					break;
				if(!seen[res.x][res.y]) {
					arrColumnSpaceUsed[res.y]++;
					seen[res.x][res.y] = true;
					area++;
				}
			}
	 }
	}
	static Point prepare(int i, int j) {
		System.out.println(i + " " + j);
		return new Point(input.nextInt(), input.nextInt());
	}
}