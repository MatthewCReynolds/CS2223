
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
	
	static int[] nextLegalPosition(int[] board, int n) {
		boolean isBoardLegal= isLegalPosition(board, n);

		if (isBoardLegal == true){ //Legal
			if (board[n-1] != 0){ //full
				int[] nextBoard = board;
				do {
					nextBoard = successor(nextBoard, n);
				} while (!isLegalPosition(nextBoard, n));
				board = nextBoard;
			}
			else { //partial, legal
				int r = findEmpty(board, n);
				board[r] = 1;
				nextLegalPosition(board, n);
			}
		}

		else if (isBoardLegal == false){ // partial, illegal
			int[] nextBoard = board;
			do {
				nextBoard = successor(nextBoard, n);
			} while (!isLegalPosition(nextBoard, n));
			board = nextBoard;
		}
		
		
		return board;
	}

	static void printBoard(int[] board, int n) {
		String list = "";
		
		for(int i = 0; i < n; i++) {
			list += board[i] + ",";
		}
		
			
		System.out.println(list.substring(0, list.lastIndexOf(",")));
	}
	
	static void firstSolution() {
		for(int i = 4; i < 41; i++){
			
			int[] b = new int[i];
			int[] board = b;
			board[0] = 1;

			do {
				board = nextLegalPosition(board, i);
			} while (board[i-1]==0);
			
			
			printBoard(board, i);
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
		
		
		
	}

}
