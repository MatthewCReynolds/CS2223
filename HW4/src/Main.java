public class Main {
     static boolean inconsistent = false;
     
     public static void main(String args[]) {
           float[][] arr1 = {{3, 4, 5},                                     
                             {2, 2, 1},
                             {2, 2, 1}
                          };
           float[] coef1 = {0, 0, 0};
          
           float[][] arr2 =    {{1,  1,  1,  1,  1,  1,  1,  1,  1},
                                {1,  1,  1,  1,  1, -1, -1, -1, -1},
                                {1, -1,  1, -1,  1, -1,  1, -1,  1},
                                {1,  1,  0,  0,  0,  0,  0,  0,  0},
                                {0,  0,  1,  1,  0,  0,  0,  0,  0},
                                {0,  0,  0,  0,  1,  1,  0,  0,  0},
                                {0,  0,  0,  0,  0,  0,  0,  1,  1},
                                {9, -8,  7, -6,  5, -4,  3, -2,  1},
                                {1,  1, -1,  1,  1,  -1, 1,  1, -1}};
           float[][] matrix1 = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 364},
                   {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                   {0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 16},
                   {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 36},
                   {0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 64},
                   {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 100},
                   {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 79},
                   {0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 61},
                   {0, 0, 0, 0, 0, 4, -3, 0, 0, 0, 0, 0 , 0},
                   {0, 0, 0, 3, -2, 0, 0, 0, 0, 0, 0, 0, 0},
                   {0, 0, 0, 1, 0, 0, 0, 0, 1, -1, 0, 0, 0},
                   {1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, -42}};
           
            float[][] matrix = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                   {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                   {0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                   {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                   {0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                   {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
                   {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                   {0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                   {0, 0, 0, 0, 0, 4, -3, 0, 0, 0, 0, 0},
                   {0, 0, 0, 3, -2, 0, 0, 0, 0, 0, 0, 0},
                   {0, 0, 0, 1, 0, 0, 0, 0, 1, -1, 0, 0},
                   {1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1}};

            float[] column = {364, 4, 16, 36, 64, 100, 79, 61, 0, 0, 0, -42};
          
           
           float[] coef2 = {122, -88, 32, 3, 7, 18, 76, 41, 0};
           float[] solutions = GJElimination(matrix, column);
           System.out.println("Given the augmented matrix which represents a system of equations:");
           printArr(matrix);
           if(!inconsistent) {
                System.out.println("The solutions to the given augemented matrix of linear equations are:");
                for(int i = 0; i< solutions.length; i++) {
                      System.out.println("X" + i + " = " + solutions[i]);
                }
           } else {
                System.out.println("System is inconsistent");
           }
     }
    
     public static float[] GJElimination(float[][] coef, float[] b) {
           int n = coef.length;
           float[][] arr = new float[n][n+1];
           float result[] = new float[b.length];
           
           for(int i = 0; i< n; i++) {  // iterate through the rows
                for(int j = 0; j<n;j++) {
                      arr[i][j] = (float)coef[i][j];
                }
                arr[i][n] = b[i];
           } // arr is now setup as an augmented matrix
           
           for(int i = 0; i < n; i++) {  // iterate through the columns
                int pivotR = i;
                for(int j = i; j<n; j++) { // iterate through the rows below the current pivot
                      if(arr[j][i] > arr[pivotR][i]) {
                           pivotR = j;
                      }                    
                }
                if(pivotR!=i) {                  //if the pivot row needs to be swapped
                      for(int j = i; j< n+1; j++) { //iterate through the next columns and swap
                           float temp = arr[i][j];
                           arr[i][j]=arr[pivotR][j];
                           arr[pivotR][j]=temp;
                      }
                }
                
                for(int j = i+1; j<n;j++) { // iterate through the rows
                      if(arr[j][i] == 0) {  // if the row has a 0 in the column under test, skip the row
                      } else {
                           float ratio = arr[i][i]/arr[j][i];
                           for(int k = i; k<n+1; k++) {                           //iterate through columns
                                 arr[j][k] = (arr[i][k] - arr[j][k] * ratio);
                           }
                      }
                }
                if(arr[i][i]==0) {
                      inconsistent = true;
                }
           }
           
           if(!inconsistent) {                                                                                                                           // This is the inconsistent Check
                for(int i = 0; i < n; i++) {                     //flatten the rows so the first number in each row is a 1
                      for(int j = n; j>=i; j--) {
                           arr[i][j] = arr[i][j]/arr[i][i];
                      }
                }
                for(int i = n-1; i>0; i--) {          //iterate through the diagonal slots, starting at bottom
                      for(int j = i-1; j>=0; j--) {         //iterate through the rows, beginning above the row under test
                           arr[j][n] = arr[j][n] - arr[j][i]*arr[i][n];
                           arr[j][i] = 0;
                      }
                }
                for(int i = 0; i<n;i++) {
                      for(int j = 0; j<=n; j++) {
                           arr[i][j]= Math.round(arr[i][j]*100)/100;              // Round to 2 decimal Places
                           if(j==n) {
                                 result[i]=arr[i][j];
                           }
                      }
                }
           }
           return result;
     }
    
     public static void printArr(float[][] arr) {
           for(int i = 0; i<arr.length; i++) {
                for(int j = 0; j<arr[0].length;j++) {
                      System.out.print("|" + arr[i][j]);
                    if((int)(Math.abs(arr[i][j]/10))==0) {
                           System.out.print("  ");
                      } else if(Math.abs(arr[i][j]/10)<10) {
                           System.out.print(" ");
                      }
                      if(arr[i][j]>=0)  System.out.print(" "); //Deal with the negative
                }
                System.out.println("|");
           }
     }
}