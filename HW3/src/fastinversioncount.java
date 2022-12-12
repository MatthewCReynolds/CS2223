
public class fastinversioncount {

	static int inversions = 0;
	
	public static void mergeSort(int [] inputArray, int length) {
		if (length < 2)
			return;
	      
	    int middle = length / 2;
	    int[] left_side = new int[middle];
	    int[] right_side = new int[length-middle];
	    int key = 0;
	      
	    //Dividing into two
	    for(int i = 0; i < length; i++){
	    	if(i < middle) 
	    		left_side[i] = inputArray[i];
	    	else {
	    		right_side[key] = inputArray[i];
	    		key++;
	    	}
	    	inversions++;
	    }
	    // Recursively calling the function to divide the sub-arrays further
	    mergeSort(left_side,middle);
	    mergeSort(right_side, length-middle);
	    
	    //Smushing them back together
	    merge(left_side, right_side, inputArray, middle, length-middle);
	}
	public static void merge(int[] left_side, int[] right_side, int[] inputArray, int left_size, int right_size){
	      
	      int i = 0, l = 0, r = 0;
	      //The while loops check the conditions for merging
	      while((l < left_size) && (r < right_size)) {
	          if(left_side[l] < right_side[r])
	              inputArray[i++] = left_side[l++];
	          else
	              inputArray[i++] = right_side[r++];
	      }
	      
	      if (l < left_size)
	          inputArray[i++] = left_side[l++];
	      if (r < right_size)
	    	  inputArray[i++] = right_side[r++];
	      
	  }
	
	public static void main( String args[] ) {
        int [] array = {170, 100, 8, 210, 44};
        
        System.out.print("Your unsorted array: "); //Printing out unsorted array
        for(int i = 0; i < array.length; i++) {
        	System.out.print(array[i] + " ");
        	
        	if(i == (array.length - 1) ) {
            	System.out.println();
            }
        }
        
        mergeSort(array, array.length);
        
        System.out.print("Your sorted array is: ");
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i]+ " ");
            
            if(i == (array.length - 1) ) {
            	System.out.println("with " + inversions + " inversions.");
            }
        }
    }
	
}
