
public class easyinversioncount {
	
	static int inversions = 0;
	
	public static int[] sort(int input[]) {
        for (int i = 1; i < input.length; i++) {
            int key = input[i], prior = i - 1;
 
            while((prior >= 0) && (input[prior] > key)) { //Just keeps swapping until it's right
                input[prior + 1] = input[prior];
                prior = prior - 1;
                inversions++;
            }
            
            input[prior + 1] = key;
        }
        
        return input;
    }
    
    public static void main(String args[]) {
        int arrayInput[] = {170, 100, 8, 210, 44};
        
        //print out unsorted array
        System.out.print("The unsorted array is: " );
        for (int i = 0; i < arrayInput.length; i++) {
            System.out.print(arrayInput[i] + " ");
            
            if (i == (arrayInput.length - 1) ) //make a new line for next time
            	System.out.println();
        }
        
        //sort the array
        int[] sorted = sort(arrayInput);
 
        //print out sorted array 
        System.out.print("The sorted array is:   ");
        for (int i = 0; i < sorted.length; i++) {
            System.out.print(sorted[i] + " ");
            
            if (i == sorted.length - 1) //make a new line for next time
            	System.out.println("with " + inversions + " inversions.");
            	
        }
 
    }
 

}
