import java.util.*;


public class Djikstra {
	
	public static void printPath(int[][] array, int startNodeNum, int endNodeNum) {
		int sum = array[1][endNodeNum];
		int temp = endNodeNum;
		String route = "";
		ArrayList<Integer> routeBackwards = new ArrayList<>();
		
		routeBackwards.add(endNodeNum);
		
		while(temp != startNodeNum) {
			temp = array[0][temp];
			routeBackwards.add(temp);
		}
		
		Collections.reverse(routeBackwards);
		
		for(int i = 0; i < routeBackwards.size(); i++) {
			switch(routeBackwards.get(i)) {
				case(0): route = route + "A-"; break;
				case(1): route = route + "J-"; break;
				case(2): route = route + "M-"; break;
				case(3): route = route + "R-"; break;
				case(4): route = route + "K-"; break;
				case(5): route = route + "S-"; break;
				case(6): route = route + "I-"; break;
				case(7): route = route + "N-"; break;
				case(8): route = route + "T-"; break;
				case(9): route = route + "D-"; break;
			}
		}
		
		route = route.substring(0, route.lastIndexOf("-"));
		
		System.out.println("The route from start to end is: " + route);
		System.out.println("The sum is: " + sum);
	}
	
	private static int[][] findPath(int[][] array, int startNum){
		int[][] newArray = new int [2][array.length];
		ArrayList<Integer> queue = new ArrayList<>();
		ArrayList<Integer> seenList = new ArrayList<>();
		
		queue.add(0);
		
		for(int i = 0; i < array.length; i++) {
			newArray[0][i] = -1;
			newArray[1][i] = Integer.MAX_VALUE;
			queue.add(i);
		}
		
		newArray[0][startNum] = startNum;
		newArray[1][startNum] = 0;
		
		for(int i = 0; i< queue.size(); i++) {
			if(queue.get(i) == startNum)
				queue.remove(i);
		}
		
		queue.add(0, startNum);
		
		for(int i = 0; i < array.length - 1; i++) {
			int removed = queue.remove(0);
			seenList.add(removed);
			
			for(int j = 0; j < array.length; j++) {
				int edge = array[j][removed];
				
				if(!(edge == 0)) {
					if ((edge + newArray[1][removed]) < newArray[1][j]) {
						newArray[1][j] = edge + newArray[1][removed];
						newArray[0][j] = removed;
						
						for(int r = 0; r < queue.size(); r++) {
							if(queue.get(r) == j)
								queue.remove(r);
						}
						
						for(int r = 0; r < queue.size(); r++) {
							if(newArray[1][queue.get(r)] > newArray[1][j]) {
								queue.add(r, j);
								r = queue.size();
							}
						}
					}
				}
			}
		}
		
		
		return newArray;
	}
	
	public static void main (String[] args) {
		int[][] matrix = {  {0, 53, 10, 12, 0, 0, 0, 0, 0, 0},
							{53, 0, 33, 0, 2, 0, 101, 0, 0, 0},
							{10, 33, 0, 9, 30, 18, 0, 0, 0, 0},
							{12, 0, 9, 0, 0, 17, 0, 0, 6, 0},
							{0, 2, 30, 0, 0, 14, 123, 122, 0, 0},
							{0, 0, 18, 17, 14, 0, 0, 137, 7, 0},
							{0, 101, 0, 0, 123, 0, 0, 8, 0, 71},
							{0, 0, 0, 0, 122, 137, 8, 0, 145, 66},
							{0, 0, 0, 6, 0, 7, 0, 145, 0, 212},
							{0, 0, 0, 0, 0, 0, 71, 66, 212, 0} };
		
		
		Scanner input = new Scanner(System.in);
		System.out.println("What is your starting node letter?");
		String start = input.nextLine();
		System.out.println("What is your end node letter?");
		String end = input.nextLine();
		int startNum = 0, endNum = 0;
		
		switch(start) { //Convert given start letter to corresponding number
			case("A"): startNum = 0; break;
			case("J"): startNum = 1; break;
			case("M"): startNum = 2; break;
			case("R"): startNum = 3; break;
			case("K"): startNum = 4; break;
			case("S"): startNum = 5; break;
			case("I"): startNum = 6; break;
			case("N"): startNum = 7; break;
			case("T"): startNum = 8; break;
			case("D"): startNum = 9; break;
		}
		switch(end) { //Convert given end letter to corresponding number
			case("A"): endNum = 0; break;
			case("J"): endNum = 1; break;
			case("M"): endNum = 2; break;
			case("R"): endNum = 3; break;
			case("K"): endNum = 4; break;
			case("S"): endNum = 5; break;
			case("I"): endNum = 6; break;
			case("N"): endNum = 7; break;
			case("T"): endNum = 8; break;
			case("D"): endNum = 9; break;
		}
		
		printPath(findPath(matrix, startNum), startNum, endNum);
	}
	
}