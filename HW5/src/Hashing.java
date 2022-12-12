import java.io.*;
import java.util.*;

public class Hashing {
	
	static Hashtable<String, Integer> Table = new Hashtable<String, Integer>(293); //Swap these
	static ArrayList<String> List = new ArrayList<>();
	static ArrayList<String> noDupeList = new ArrayList<>();
	static String file = "";
	
	static void readFile() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("/Users/matthewreynolds/Desktop/Reynolds_Matthew_HW5_B22/EdgarAllanPoeBellsB2022groomed.txt"));
		StringBuilder stringBuilder = new StringBuilder();
		
		String line = null;
		String ls = System.getProperty("line.separator");
		
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append(ls);
		}
		// delete the last new line separator
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		reader.close();

		file = stringBuilder.toString();
	}
	
	static String clean(String input) {
		
		input = input.replaceAll("[^a-zA-Z0-9]", " ");
		input = input.replace("!", " ");
		input = input.replace(",", " ");
		input = input.replace(".", " ");
		input = input.replace(";", " ");
		input = input.replace(":", " ");
		
		input = input.trim().replaceAll(" +", " "); //Get rid of duplicate spaces and replace them with only 1
		
		return input;
		
	}
	
	static void breakFile(String file) {
		List.clear();
		noDupeList.clear();//redundant
		
		for(String word : file.split(" ")) { //Breaks every "String" into a List entry
		    List.add(word);
		}
		
		for (String word : List) { //Makes a new arraylist without duplicates, worth noting is I don't deal with capitalization differences, i.e. Rolls vs rolls
            if (!noDupeList.contains(word)) {
            	noDupeList.add(word);
            }
        }
        
	}
	
	static int hash(String word) {
		int h = 0;
		for (int i = 0; i < word.length() - 1; i++) {
			h = ((h * 123 + ((int) (word.charAt(i)))) % 293);
		}
		
		return h;
	}

	static void addToTable() {
		for(int i = 0; i < noDupeList.size(); i++) {
			if((Table.containsKey(noDupeList.get(i)) == false)) {
				Table.put(noDupeList.get(i), hash(noDupeList.get(i)));
			}
		}
	}
	
	static void printTable() {
		System.out.println("Table:");
		System.out.println("|Index|    Hashed Word    | Hash |");
		System.out.println("----------------------------------");
		for(int i = 0; i < Table.size(); i++) { //Print out the table
			System.out.printf("| %-3d | %-17s | %-4d | %n", i, noDupeList.get(i), hash(noDupeList.get(i)));
		}
	}
	
	static void nonEmpty() {
		//Finding non-empty
		System.out.println("The number of non-empty addresses are: " + Table.size());
		System.out.println("The load factor is: " + ((double) Table.size())/(293.0));
	}
	
	static void longestEmpty() { 
		
		int longest = 0;
		int locationStart = 0;
		int locationEnd = 0;
		ArrayList<Integer> longestEmpty = new ArrayList<Integer>(293);
		ArrayList<Integer> zeroIndexs = new ArrayList<Integer>();
		
		for(int i = 0; i < 293; i++) { //Fill table out with random data
			longestEmpty.add(1);
		}
		for(int i = 0; i < noDupeList.size(); i++) { //Set the index at the Hash's to 0
			longestEmpty.set(hash(noDupeList.get(i)), 0);
		}
		
		//Go through and find all 0's, add to index
		for(int i = 0; i < 293; i++) {
			if(longestEmpty.get(i) != 0) {
				zeroIndexs.add(i);
			}
		}
		
		//For every 0, while the next index = 0 of longestEmpty is 0
		//++ the local longest 0 check, check if its greater than longest, if so set to longest.
		//Also set the locationStart and on the last one set it as locationEnd
		for(int i = 0; i < zeroIndexs.size(); i = zeroIndexs.get(i + 1)) { //start from first zero, then jump to the next one...
			int localStart = i, localLongest = 0, localFinish = 0, increment = i;
			while(longestEmpty.get(increment+1) == 0) { //keep checking the next one
				localLongest++; increment++;
				localFinish = zeroIndexs.get(i) + localLongest; //from where it started + where its length to get finish
				
				if(localLongest > longest) { //Update values
					longest = localLongest;
					locationStart = localStart;
					locationEnd = localFinish;
				}
				if(i == 293) { //Break out
					break;
				}
				
			}
		}
		
		//Need to check wrap around
		
		
		System.out.println("The longest gap is: " + (locationEnd - locationStart) + ". It is located from " + locationStart + " to " + locationEnd);
		
	}

	static void longestCluster() { //Basically the same as the prior one
		int longest = 0;
		int locationStart = 0;
		int locationEnd = 0;
		ArrayList<Integer> longestFilled = new ArrayList<Integer>(293);
		ArrayList<Integer> oneIndex = new ArrayList<Integer>();
		
		for(int i = 0; i < 293; i++) { //Fill table out with random data
			longestFilled.add(0);
		}
		for(int i = 0; i < noDupeList.size(); i++) { //Set the index at the Hash's to 1
			longestFilled.set(hash(noDupeList.get(i)), 1);
		}
		
		//Go through and find all 1's, add to index
		for(int i = 0; i < 293; i++) {
			if(longestFilled.get(i) != 0) {
				oneIndex.add(i);
			}
		}
		
		//For every 0, while the next index = 0 of longestEmpty is 0
		//++ the local longest 0 check, check if its greater than longest, if so set to longest.
		//Also set the locationStart and on the last one set it as locationEnd
		for(int i = 0; i < oneIndex.size(); i = oneIndex.get(i + 1)) { //start from first zero, then jump to the next one...
			int localStart = i, localLongest = 0, localFinish = 0, increment = i;
			while(longestFilled.get(increment+1) == 1) { //keep checking the next one
				localLongest++; increment++;
				localFinish = oneIndex.get(i) + localLongest; //from where it started + where its length to get finish
				
				if(localLongest > longest) { //Update values
					longest = localLongest;
					locationStart = localStart;
					locationEnd = localFinish;
				}
				if(i == 293) { //Break out
					break;
				}
				
			}
		}
		
		//Need to check wrap around
		
		
		System.out.println("The longest cluster is: " + (locationEnd - locationStart) + ". It is located from " + locationStart + " to " + locationEnd);
	}
	
	static void commonHash() {
		//Most Common Hash Value
		Hashtable<Integer, Integer> common = new Hashtable<>(293); //{hash, count}
		
		for(int i = 0; i < noDupeList.size(); i++) { //Fill HashTable with all 0s
			common.put(hash(noDupeList.get(i)), 0);
		}
		
		for(int i = 0; i < noDupeList.size(); i++) { //Fill out table
			common.put(hash(noDupeList.get(i)), common.get(hash(noDupeList.get(i))) + 1);
		}
		
		int hashCount = 0;
		int mostCommon = 0;
		for(int i = 0; i < noDupeList.size(); i++) { //Find most common
			if(common.get(hash(noDupeList.get(i))) > hashCount) {
				hashCount = common.get(hash(noDupeList.get(i)));
				mostCommon = hash(noDupeList.get(i));
			}
		}
		
		int numWords = 0; //Find number of words that equal that
		for(int i = 0; i < noDupeList.size(); i++) {
			if(hash(noDupeList.get(i)) == mostCommon)
				numWords++;
		}
		
		System.out.println("The most common occuring hash value is: " + mostCommon);
		System.out.println("The number of words that have that hash are: " + numWords);
	}
	
	static void farthest() {
		int currentLargest = 0;
		String currentWord = "";
		
		//for every word in noDupeList, find its distance to its hash going up & down from its index. Which ever is smallest is its value
		for(int i = 0; i < noDupeList.size(); i++) {
			int wordHash = hash(noDupeList.get(i));
			int bottomDistance = 0, topDistance = 0, topLocation = i, bottomLocation = i, currentDistance;
			
			while(bottomLocation != wordHash) { //Go down
				bottomDistance++;
				if(bottomLocation == 0) {
					bottomLocation = 294;
				}
				bottomLocation--;
			}
			while(topLocation != wordHash) { //Go up from noDupeList index, if it hits the max, set it to bottom and look through there
				topDistance++;
				if(topLocation == 293) {
					topLocation = -1;
				}
				topLocation++;
			}
			
			if(topDistance > bottomDistance) { //See which distance is smallest
				currentDistance = bottomDistance;
			} else {
				currentDistance = topDistance;
			}
			
			if (currentDistance > currentLargest) { //See if this words distance is farthest, if so make it new max
				currentLargest = currentDistance;
				currentWord = noDupeList.get(i);
			}
			
		}
		
		System.out.println("The farthest word from it's hash is: " + currentWord + ". Which is " + currentLargest + " away.");
	}
	
	
	public static void main(String[] args) throws IOException {
		
		readFile(); //Reads given file
		
		file = clean(file); //Remove all bad characters
		
		breakFile(file); //Break file into String ArrayList of each word
		
		addToTable(); //Add all words (no duplicates) to hashTable
		
		printTable(); //Print out hashTable cleanly
		
		nonEmpty(); //Print out nonempty addresses
		
		longestEmpty(); //Print out the longest empty area value and location
		
		longestCluster(); //Print out the longest non-empty area value and location
		
		commonHash(); //Print out most common hash

		farthest();//Largest distance from hash
	}

}
