
public class BoardSpot {
	
	boolean hasQueen;
	int seenByQueenCount;
	
	public BoardSpot(boolean hasQueen, int seenByQueenCount) {
		this.hasQueen = hasQueen;
		this.seenByQueenCount = seenByQueenCount;
	}
	
	public boolean isAvailable() {
		if((this.hasQueen == false) && (this.seenByQueenCount == 0))
			return true;
		
		return false;
	}
	
	public void seen() {
		this.seenByQueenCount++;
	}
	
	public void unSee() {
		this.seenByQueenCount--;
	}
	
	public void removeQueen() {
		this.hasQueen = false;
		this.seenByQueenCount--;
	}
	
	public void resetQueenView() {
		this.seenByQueenCount = 1;
	}
	
	public void placeQueen() {
		this.hasQueen = true;
		this.seenByQueenCount++;
	}
	
	

}
