import java.util.*;

public class Main {
	
	public static boolean isLegalPosition(int[] givenBoard, int n){
		boolean legal = false;
		BoardSpot[][] board = new BoardSpot[n][n];
		int actualPositions = givenBoard.length;
		
		
		
		Collections.reverse(Arrays.asList(givenBoard)); //reverse board to see if it has 0s at end
		for(int i = 0; i < givenBoard.length; i++) {
			if(givenBoard[i] == 0) {
				actualPositions--;
			} else { //If its no longer equal to 0, then break out;
				break;
			}
		}
		Collections.reverse(Arrays.asList(givenBoard)); //reverse board back to normal
		
		
		//need to -- everything since its a 0 index, and its not fed in as such
		for(int i = 0; i < givenBoard.length; i++) {
			givenBoard[i] = givenBoard[i] - 1;
		}
		
		
		if(givenBoard.length > n) { //Make sure board is big enough to fit everything
			return false; //Otherwise break and just spit out false
		}
		
		for(int i = 0; i < actualPositions; i++) { //For every item in array thats not the last 0s
			if(board[i][givenBoard[i]].isAvailable() && givenBoard[i] >= 0) { //if BoardSpot is !seen by queen and needs to be placed on this row
				
				board[i][givenBoard[i]].placeQueen();
				
				for(int j = 0; j < n; j++) {//Update all spots seen by queen on same row
					if(!board[i][givenBoard[i]].isAvailable()) {
						board[i][j].seen();
					}
				}
				for(int j = 0; j < n; j++) {//Update all spots seen by queen on same column
					if(!board[i][givenBoard[i]].isAvailable()) {
						board[i][i].seen();
					}
				}
				
				int startRow = i, startCol = givenBoard[i];// need to set to bottom left corner of diagonal
				while(startRow < n && startCol < 0) {
					startRow++;
					startCol--;
				}
				while(startRow > 0 && startRow < n && startCol > 0 && startCol < n) { //Update on positive slope diagonal
					if(!board[i][givenBoard[i]].isAvailable()) {
						board[startRow][startCol].seen();
					}
					startRow--;
					startCol++;
				}
				
				startRow = i;// need to set these to top left corner of diagonal to move downward
				startCol = givenBoard[i];
				while(startRow > 0 && startCol > 0) {
					startRow--;
					startCol--;
				}
				while(startRow > 0 && startRow < n && startCol > 0 && startCol < n) {// Update on negative slope diagonal
					if(!board[i][givenBoard[i]].hasQueen) {
						board[startRow][startCol].seen();
					}
					startRow++;
					startCol--;
				}
				
				
				
				if(i == actualPositions) { //Made it through all of the queen placements and didn't have any issues
					return true;
				}
			}
			else
				break;
		}
		
		
		return false;
	}
	
	public static void main(String[] args) {
		
		int[] testBoard = {1,6,8,3,7,0,0,0};
		int n = 8;
		System.out.println(isLegalPosition(testBoard,n)); //should return true
		
	}

}
