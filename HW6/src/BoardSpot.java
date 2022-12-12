
public class BoardSpot {
	
	boolean hasQueen;
	int seenByQueenCount;
	
	public BoardSpot(boolean hasQueen, int seenByQueenCount) {
		this.hasQueen = hasQueen;
		this.seenByQueenCount = seenByQueenCount;
	}
	
	public boolean checkAvailable() {
		if((hasQueen == false) && (seenByQueenCount > 0))
			return true;
		
		return false;
	}
	
	public void removeQueen() {
		hasQueen = false;
		seenByQueenCount--;
	}
	
	public void placeQueen() {
		hasQueen = true;
		seenByQueenCount++;
	}
	
	

}
