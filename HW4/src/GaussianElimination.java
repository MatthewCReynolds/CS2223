import java.util.*;

public class GaussianElimination {
	static float[][] matrix1 = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 364},
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
	static float[][] matrix = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
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
	
	static float[] column = {364, 4, 16, 36, 64, 100, 79, 61, 0, 0, 0, -42};
	static boolean inconsistent = false;
			
	public static float[] BetterBetterForwardElimination(float[][] Matrix, float[] column){
		float[][] newMatrix = new float [Matrix.length][Matrix.length+1];
		float[] newColumn = new float [column.length];

		
		for(int i = 0; i < Matrix.length; i++) {
			for(int k = 0; k < Matrix.length; k++) {
				newMatrix[i][k] = Matrix[i][k]; //Copying over all the data from the parameter to the new one
			}
			newMatrix[i][Matrix.length] = column[i]; //Attaching the column to the matrix
		}
		
		
		for(int i = 0; i < Matrix.length; i++) {
			int pivotrow = i;
			
			if(newMatrix[i][i] == 0) //Check if its consistent
				inconsistent = true;
			
			
			for(int j = i; j < Matrix.length; j++) {
				if (newMatrix[j][i] > newMatrix[pivotrow][i])
					pivotrow = j;
			}
			
			if(pivotrow != i) { //Check if pivot row needs changing
				for(int j = i; j < Matrix.length + 1; j++) {
					float temp = newMatrix[i][j];
					newMatrix[i][j] = newMatrix[pivotrow][j];
					newMatrix[pivotrow][j] = temp;
				}
			}
			
			for(int j = i + 1; j < Matrix.length; j++) {
				if(newMatrix[j][i] == 0) {}
				else {
					for(int k = i; k < Matrix.length + 1; k++) { //Problem?
						float ratio = newMatrix[i][i] / newMatrix[j][i];
						newMatrix[j][k] = (newMatrix[i][k] - newMatrix[j][k] * ratio);
					}
				}
			}
			
		}
		
		//Now that we have an upper triangle and know if its consistent or not
		if(!inconsistent) {
			for(int i = 0; i < Matrix.length; i++) {
                for(int j = Matrix.length; j >= i; j--) {
                     newMatrix[i][j] = newMatrix[i][j]/newMatrix[i][i];
                }
			}
			for(int i = Matrix.length - 1; i > 0; i--) {
                for(int j = i - 1; j >= 0; j--) {		//Decrement the way through the rest of the rows
                     newMatrix[j][Matrix.length] = newMatrix[j][Matrix.length] - newMatrix[j][i] * newMatrix[i][Matrix.length];
                     newMatrix[j][i] = 0;
                }
			}
			for(int i = 0; i < Matrix.length; i++) {
                for(int j = 0; j <= Matrix.length; j++) {
                	newMatrix[i][j] = Math.round(newMatrix[i][j]*100)/100;
                    if(j == Matrix.length) {
                    	newColumn[i]=newMatrix[i][j];
                    }
                }
			}
		}
		
		System.out.println("The solved matrix is: ");
		printArray(newMatrix);
		
		return newColumn;
	}
	
	public static void printArray(float[][] matrix) {
		for(int i = 0; i < matrix.length; i ++) {
			for(int k = 0; k < matrix.length + 1; k++) {
				System.out.printf("%-4d", (int) matrix[i][k] );
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Matrix starts as: ");
		printArray(matrix1);
		
		float[] answers = BetterBetterForwardElimination(matrix, column);
		
		System.out.println("The answers are:");
		for(int i = 0; i < answers.length; i++) {
			System.out.println("X" + i + ": " + (int) answers[i]);
		}
		//I HAVE INCONSISTENT CHECK
		System.out.println("I know its wrong. I cannot tell you why its doing what its doing but Im like 99.999999% confident its like 99.99999% right with one little peice being wrong");
		
	}

}
