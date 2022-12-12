import java.util.*;

public class graycodesarefun {
	
	public static ArrayList<String> BRGC(int n) {
		ArrayList<String> L = new ArrayList<>();
		
		if (n == 1) {
			L.add("0");
			L.add("1");
		} else {
			//Make L1 by calling BGRC(n-1)
			ArrayList<String> L1 = BRGC(n-1);
			
			
			//Make L2 by reversing L1
			ArrayList<String> L2 = new ArrayList<>();
			for(int i = (L1.size() - 1); i >= 0; i--) {
				L2.add(L1.get(i));
			}
			
			//Add 0 to every String of L1
			for(int i = 0; i < (L1.size()); i++) {
				L1.set(i, "0" + L1.get(i));
			}
			
			//Add 1 to every String of L2
			for(int i = 0; i < (L2.size()); i++) {
				L2.set(i, "1" + L2.get(i));
			}
			
			//Append L2 to L1
			L1.addAll(L2);
			 
			//Add all L1 back to L
			L.addAll(L1);
			
		}
		
		return L;
	}
	public static ArrayList<String> BRGCaction(ArrayList<String> input){
		ArrayList<String> names = new ArrayList<String>();
		names.add("");
		
		//For every name in input
			//For every "number" in the name in input
				//Check if each spot is != to the same spot in the prior one
					//If different, check which spot it is, then return the according name
		for(int i = 1; i < input.size(); i++) {
			for(int r = 0; r < input.get(i).length(); r++) {
				if((Integer.valueOf(input.get(i).charAt(r))) != (Integer.valueOf(input.get(i - 1).charAt(r)))) {
					switch (r) {
						case(0): names.add("Dave"); break;
						case(1): names.add("Chris"); break;
						case(2): names.add("Bob"); break;
						case(3): names.add("Alice"); break;
						case(4): names.add("Zed"); break; //For bonus bit
					}
				}
			}
		}
		
		return names;
	}
	public static ArrayList<String> BRGCnames(ArrayList<String> input){
		ArrayList<String> names = new ArrayList<String>();
		
		//I could probably do this dynamically and make this take longer to code.. however I want to be done with this
		for(int i = 0; i < input.size(); i++) {
			switch(input.get(i)) {
			case("0000"): names.add(""); break;
			case("0001"): names.add("Alice"); break;
			case("0011"): names.add("Alice & Bob"); break;
			case("0010"): names.add("Bob"); break;
			case("0110"): names.add("Bob & Charlie"); break;
			case("0111"): names.add("Alice & Bob & Charlie"); break;
			case("0101"): names.add("Alice & Charlie"); break;
			case("0100"): names.add("Charlie"); break;
			case("1100"): names.add("Charlie & Dave"); break;
			case("1101"): names.add("Alice & Charlie & Dave"); break;
			case("1111"): names.add("Alice & Bob & Charlie & Dave"); break;
			case("1110"): names.add("Bob & Charlie & Dave"); break;
			case("1010"): names.add("Bob & Dave"); break;
			case("1011"): names.add("Alice & Bob & Dave"); break;
			case("1001"): names.add("Alice & Dave"); break;
			case("1000"): names.add("Dave"); break;
			}
		}
		
		return names;
	}
	public static ArrayList<Integer> index() {
		ArrayList<Integer> numbers = new ArrayList<>();
		
		for(int i = 0; i < 16; i++) {
			numbers.add(i);
		}
		
		return numbers;
	}
	
	
	public static void main(String[] args) {
		ArrayList<Integer> index = index();
		ArrayList<String> numbers = BRGC(4);
		ArrayList<String> names = BRGCnames(numbers);
		ArrayList<String> actions = BRGCaction(numbers);

		System.out.println("Index  |  Gray  |          Child(ren)          | Action");
		System.out.println("--------------------------------------------------------");
		for(int i = 0; i < 16; i++) {
			System.out.printf("%-6d | %-6s | %-28s | %-7s %n", index.get(i), numbers.get(i), names.get(i), actions.get(i));
		}
		
	}

}
