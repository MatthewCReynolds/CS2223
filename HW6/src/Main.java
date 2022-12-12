import java.util.*;

public class Main {
	
	public boolean isLegalPosition(int[] givenBoard, int n){
		boolean legal = false;
		BoardSpot[][] board = new BoardSpot[n][n];
		int actualPositions = givenBoard.length;
		
		
		//Strip out all 0s at the end
		Collections.reverse(Arrays.asList(givenBoard)); //reverse board to see if it has 0s at end
		for(int i = 0; i < givenBoard.length; i++) {
			if(givenBoard[i] == 0) {
				actualPositions--;
			} else { //If its no longer equal to 0, then break out;
				break;
			}
		}
		Collections.reverse(Arrays.asList(givenBoard)); //reverse board back to normal
		
		
		if(givenBoard.length > n) { //Make sure board is big enough to fit everything
			return false;
		}
		
		for(int i = 0; i < actualPositions; i++) { //For every item in array thats not the last 0s
			if(board[i][givenBoard[i]].seenByQueenCount == 0) { //if BoardSpot is !seen by queen
				board[i][givenBoard[i]].placeQueen();
				
				for(int j = 0; j < n; j++) {//Update all spots seen by queen on same row
					if(!board[i][j].hasQueen) {
						board[i][j].seen();
					}
				}
				for(int j = 0; j < n; j++) {//Update all spots seen by queen on same column
					if(!board[i][j].hasQueen) {
						board[j][i].seen();
					}
				}
				for(int j = 0; j < n; j++) { //Update all spots seen on negative slope diagonal
					
				}
				for(int j = 0; j < n; j++) { //Update all spots seen on positive slope diagonal
					
				}
			}
			else
				break;
		}
		
		
		return false;
	}
	
	public static void main(String[] args) {
		
		System.out.println("Print");
		
		
		
		
		
		
	}

}
