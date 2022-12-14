
public class Board {
	
	public Board(int size) {
		BoardSpot[][] Board = new BoardSpot[size][size];
	}
	
	void seeRow(int row, int[] givenBoard) {
		for(int j = 0; j < row; j++) {//Update all spots seen by queen on same row
			if(!this.Board[row][givenBoard[row]].hasQueen) { //dont update the spot that has the queen on it
				this.Board[row][j].seen();
			}
		}
	}
	
	void seeColumn(int column, int[] givenBoard) {
		for(int j = 0; j < givenBoard.length; j++) {//Update all spots seen by queen on same column
			if(!this.Board[column][givenBoard[column]].hasQueen) { //Dont update spot with queen on it
				this.Board[column][j].seen();
			}
		}
	}
	
	void seeNegativeDiagonal(int row, int[] givenBoard) {
		int startRow = row, startCol = givenBoard[row];// need to set to bottom left corner of diagonal
		
		while(startRow < givenBoard.length && startCol < 0) {
			startRow++;
			startCol--;
		}
		while(startRow > 0 && startRow < givenBoard.length && startCol > 0 && startCol < givenBoard.length) { //Update on positive slope diagonal
			if(!this.Board[row][givenBoard[row]].hasQueen) {
				this.Board[startRow][startCol].seen();
			}
			startRow--;
			startCol++;
		}
	}
	
	void seePostiveDiagonal(int row, int[] givenBoard) {
		int startRow = row;// need to set these to top left corner of diagonal to move downward
		int startCol = givenBoard[row];
		while(startRow > 0 && startCol > 0) {
			startRow--;
			startCol--;
		}
		while(startRow > 0 && startRow < givenBoard.length && startCol > 0 && startCol < givenBoard.length) {// Update on negative slope diagonal
			if(!this.Board[startRow][givenBoard[startRow]].hasQueen) { //Dont update if this is the queen slot
				this.Board[startRow][startCol].seen();
			}
			startRow++;
			startCol--;
		}
	}

}
