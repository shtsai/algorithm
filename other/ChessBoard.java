
public class ChessBoard {
	static char[][] board;
	public static void main(String[] args) {
		board = buildBoard(8, 8);
		board[3][3] = '*';		// add obstacles
		board[4][1] = '*';		
		printBoard(board);
		
		System.out.println();
		System.out.println("Can Reach 1:");
		System.out.println("0,0 -> 7,7 = " + canReach(0,0,7,7));
		System.out.println("0,0 -> 7,6 = " + canReach(0,0,7,6));
		System.out.println("2,3 -> 4,1 = " + canReach(2,3,4,1));
		System.out.println("7,0 -> 0,7 = " + canReach(7,0,0,7));
		
		System.out.println();
		System.out.println("Can Reach 2:");
		System.out.println("0,0 -> 7,7 = " + canReach2(0,0,7,7));
		System.out.println("0,0 -> 7,6 = " + canReach2(0,0,7,6));
		System.out.println("2,3 -> 4,1 = " + canReach2(2,3,4,1));
		System.out.println("7,0 -> 0,7 = " + canReach2(7,0,0,7));
	}

	public static char[][] buildBoard (int rows, int cols) {
		char[][] board = new char[rows][cols];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if ((i + j) % 2 == 0) {
					board[i][j] = 'O';
				} else {
					board[i][j] = 'X';
				}
			}
		}
		return board;
	}
	
	public static void printBoard(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	/* Return true if point (i, j) can reach (k, l) by moving diagonally. */
	public static boolean canReach(int i, int j, int k, int l) {
		if (((i + j) % 2) != ((k + l) % 2)) return false;
		int dy = Math.abs(i - k);
		int dx = Math.abs(j - l);
		return dy == dx;
	}
	
	public static boolean canReach2(int i, int j, int k, int l) {
		if (i == k && j == l) return true;
		int dy = i - k > 0 ? -1 : 1;
		int dx = j - l > 0 ? -1 : 1;
		
		while (i >= 0 && i < board.length && j >= 0 && j < board[0].length) {
			if (board[i][j] == '*') {	// '*' represents an obstacles
				return false;
			} else if (i == k && j == l) {
				return true;	// reach the other point
			} else {
				i += dy;
				j += dx;
			}
		}
		return false;
	}
}
