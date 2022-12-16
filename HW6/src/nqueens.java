
public class nqueens {
	
	static boolean isLegalPosition(int[] board, int n) {
		for (int i = 0; i < n; i++){ //For every row
			if (board[i] != 0){
				for (int j = 0; j < n; j++){
					if ((i != j) && (board[j] != 0)){
						if (board[i] == board[j]) //If it has the same column, return false
							return false;
						else if (((j-i)+board[i]) == board[j]) //If it is along a diagonal
							return false;
						else if ((board[i]-(j-i)) == board[j] ) //If two diagonals match, return false
								return false;
					}
				}
			}
		}
		
		
		return true; //If it makes it through no problem, return true
	}
	
	static int findEmpty(int[] board, int n){
		int rowCount = 0;
		
		for (int i = 0; i < n; i++){ //Loop through board
			if (board[i] != 0) //If there's a queen, ++ countQueens
				rowCount++;
		}
		
		
		return rowCount;
	}
	
	/*
	static int[] successor(int[] board, int n) { //Thanks for suggestion
		int lastQueenRow = findEmpty(board, n) - 1;

		if (board[n-1] != 0)
			lastQueenRow = n-1;

		if (board[lastQueenRow] < n) //move to right by one, not at the end
			board[lastQueenRow] = board[lastQueenRow] + 1;
		else {
			board[lastQueenRow] = 0; // remove this queen because reached dead-end
			lastQueenRow--;
			while (board[lastQueenRow] == n){ // back tracking, find the first queen that can be to the right by one
				board[lastQueenRow] = 0;
				lastQueenRow--;
			}
			board[lastQueenRow] = board[lastQueenRow]+1;
		}
		
		
		return board;
	}
	*/
	
	
	public static int[] nextLegalPosition(int[] board, int n) {
		int[] newBoard = board.clone();
		int i = findEmpty(board, n);
		boolean isFullBoard = false;
		
		if (i == n)
			isFullBoard = true;
		
		if (isLegalPosition(newBoard, n)) {
			if (isFullBoard)
				return backTrack(newBoard, n);
			else {
				newBoard[i] = newBoard[i] + 1;
				if (isLegalPosition(newBoard, n)) {
					return newBoard;
				}
				return nextLegalPosition(newBoard, n);
			}
		} else {
			newBoard[i-1] = newBoard[i-1] + 1;
			
			if (newBoard[i-1] > n) {
				newBoard[i-1] = 0;
				return backTrack(newBoard, n);
			}
			
			if (isLegalPosition(newBoard, n))
				return newBoard;
			else
				return nextLegalPosition(newBoard, n);
		}
	}
	
	public static int[] backTrack(int[] board, int n) {
		int[] newBoard = board.clone();
		int i = findEmpty(board, n);
		
		if (i == 0)
			return null;
		
		newBoard[i-1] = newBoard[i-1] + 1;
		if (newBoard[i-1] > n) {
			newBoard[i-1] = 0;
			return backTrack(newBoard, n);
		}
		
		if (isLegalPosition(newBoard, n))
			return newBoard;
		else
			return backTrack(newBoard, n);
	}

	static void printBoard(int[] board, int n) {
		String list = "";
		
		for(int i = 0; i < n; i++) {
			list += board[i] + ",";
		}
		
			
		System.out.println(list.substring(0, list.lastIndexOf(",")));
	}
	
	static void firstSolution() {
		for(int i = 4; i < 21; i++){
			
			int[] b = new int[i];
			int[] board = b;
			board[0] = 1;

			do {
				board = nextLegalPosition(board, i);
			} while (board[i-1]==0);
			
			
			printBoard(board, i);
		}
	}
	
	static void numSolution() {
		for (int i = 4; i <= 20; i ++) {
			int[] board = new int[i];
			int solutions = 0;
			
			board = nextLegalPosition(board, i);
			while (board != null) {
				if (findEmpty(board, i) == i)
					solutions++;
				board = nextLegalPosition(board, i);
			}
			
			System.out.println("There are " + solutions + " solutions for " + i + " ns");
		}
	}
	
	public static void main(String[] args) {
		
		int[] testBoard = {1,6,8,3,7,4,2,5};
		int n = 8;
		System.out.println("First provided example for isLegalPos : " + isLegalPosition(testBoard, n));
		
		int[] testBoard2 = {1,6,8,3,7,0,0,0};
		n = 8;
		System.out.println("Second provided example for isLegalPos : " + isLegalPosition(testBoard2,n));
		
		int[] testBoard3 = {1,6,8,3,5,0,0,0};
		n = 8;
		System.out.println("Third provided example for isLegalPos : " + isLegalPosition(testBoard3,n));
		
		
		System.out.println("The first solutions are: ");
		firstSolution();
		
		System.out.println("All solutions as follows: ");
		numSolution();
		
		
		
	}

}
