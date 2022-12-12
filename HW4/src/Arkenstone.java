import java.util.*;

public class Arkenstone {
	
	static int[][] map = { {96, 33, 44, 98, 75, 68, 99, 84},
							{10, 41, 1, 86, 46, 24, 53, 93},
							{83, 97, 94, 27, 65, 51, 30, 7},
							{56, 70, 47, 64, 22, 88, 67, 12},
							{91, 11, 77, 48, 13, 71, 92, 15},
							{32, 59, 17, 25, 31, 4, 16, 63},
							{79, 5, 14, 23, 78, 37, 40, 74},
							{35, 89, 52, 66, 82, 20, 95, 21} };
	static String route = "";
	static ArrayList<String> allRoute = new ArrayList<>();
	static int runningValue = 0, bestValue = 0, bestValueIndex = 0, routeValue = 0;
			
	public static void findRoute(int[][] input, int rowPos, int colPos){
		route = (route + " " + rowPos + "," + colPos);
		runningValue += input[rowPos][colPos];
		if(!(rowPos == 0)) { // If not top row
			
			if(leftPossible(colPos)) //If left possible, go left
				findRoute(input, rowPos - 1, colPos - 1);
			
			findRoute(input, rowPos - 1, colPos);
			
			if(rightPossible(colPos)) 
				findRoute(input, rowPos - 1, colPos + 1);
			
			runningValue -= input[rowPos][colPos];
			route = route.substring(0, route.length() - 4); //Drop the last coordinates (4 characters) from the route so far
		}
		else {
			route = route + "!" + runningValue; //To find runningValue later in getBestRoute
			allRoute.add(route);
			route = route.substring(0, route.length() - 8); //Drop the 3 digit value, exclamation point, and 4 characters for the coordinates
			runningValue -= input[rowPos][colPos];
		}
		
	}
	public static String getBestRoute(ArrayList<String> allRoute) {
		for(int i = 0; i < allRoute.size(); i++) {
			routeValue = Integer.parseInt(allRoute.get(i).substring(allRoute.get(i).lastIndexOf("!") + 1));
			if (bestValue < routeValue) {
				bestValue = routeValue;
				bestValueIndex = i;
			}
		}
		return allRoute.get(bestValueIndex);
	}
 	public static boolean leftPossible(int colPos) {
		if(colPos > 0)
			return true;
		return false;
	}
	public static boolean rightPossible(int colPos) {
		if(colPos < 7)
			return true;
		return false;
	}
	public static void printRoute(String bestRoute){
		int[] commaLocations = {0, 0, 0, 0, 0, 0, 0, 0};
		String[][] theRoute = { {"O", "O", "O", "O", "O", "O", "O", "O"},
								{"O", "O", "O", "O", "O", "O", "O", "O"},
								{"O", "O", "O", "O", "O", "O", "O", "O"},
								{"O", "O", "O", "O", "O", "O", "O", "O"},
								{"O", "O", "O", "O", "O", "O", "O", "O"},
								{"O", "O", "O", "O", "O", "O", "O", "O"},
								{"O", "O", "O", "O", "O", "O", "O", "O"},
								{"O", "O", "O", "O", "O", "O", "O", "O"} };
		
		int commaLocIterate = 0; //Finding where every Comma is
		for(int i = 0; i < bestRoute.length(); i++) {
			if(bestRoute.charAt(i) == ',') {
				commaLocations[commaLocIterate] = i;
				commaLocIterate++;
			}
		}
		
		Collections.reverse(Arrays.asList(commaLocations)); // Reverse the locations
		
		for(int i = 0; i < 8; i++) { //Placing the X's
			int columnNumber = Integer.parseInt(bestRoute.substring(commaLocations[i] + 1, commaLocations[i] + 2));
			if (i == 7)
				theRoute[i][columnNumber] = "A";
			else if(i == 0)
				theRoute[i][columnNumber] = "S";
			else
				theRoute[i][columnNumber] = "X";
			
		}
		
		for(int i = 0; i < 4; i++) {
			String[] temp = theRoute[i];
			theRoute[i] = theRoute[7 - i];
			theRoute[7-i] = temp;
		}
		
		System.out.println(Arrays.deepToString(theRoute).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
	}
	
	public static void main(String[] args) {
		for(int i = 0; i < map[0].length; i++) {
			findRoute(map, 7, i);
			runningValue = 0;
		}
		String bestRoute = getBestRoute(allRoute);
		System.out.println("He started at: " + bestRoute.substring(1,4) + " (0 indexed)");
		System.out.println("A is the Arkenstone, X is his path, S is his start.");
		System.out.println("");
		
		printRoute(bestRoute);
		
		System.out.println("");
		System.out.println("He collected: " + bestValue + " gems/coins.");
		System.out.println("There were: " + allRoute.size() + " routes");
		
		
	}
}
