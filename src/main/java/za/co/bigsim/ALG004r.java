package za.co.bigsim;

public class ALG004r {
	public static void main(String[] args) {
		ALG004r a = new ALG004r();
		a.printMagicSquare(3, 0);
		System.out.println("\n");
		a.printMagicSquare(3, 1);
		System.out.println("\n");
		System.out.println("\n");
		a.printMagicSquare(3, 2);
		System.out.println("\n");
		a.printMagicSquare(3, 3);
		System.out.println("\n");
		a.printMagicSquare(3, 4);
		System.out.println("\n");
		a.printMagicSquare(3, 5);
	

	}
	
	private void printMagicSquare(int num, int rotation) {
		 
	        if (num % 2 == 0) throw new RuntimeException("n must be odd");

	        int[][] magic = new int[num][num];

	        int row = num-1;
	        int col = num/2;
	        magic[row][col] = 1;

	        for (int i = 2; i <= num*num; i++) {
	            if (magic[(row + 1) % num][(col + 1) % num] == 0) {
	                row = (row + 1) % num;
	                col = (col + 1) % num;
	            }
	            else {
	                row = (row - 1 + num) % num;
	                // don't change col
	            }
	            magic[row][col] = i;
	        }

	        if (rotation == 0 || rotation ==1) {
	        	for (int i = 0; i < num; i++) {
		            for (int j = 0; j < num; j++) {
		                if (magic[i][j] < 10)  System.out.print(" ");  // for alignment
		                if (magic[i][j] < 100) System.out.print(" ");  // for alignment
		                System.out.print(magic[i][j] + " ");
		            }
		            System.out.println();
		        }
	        }else {
	        	
	        	 for (int k=1; k < rotation; k++) {
	        		 magic = rotateClockWise(magic);
	        	 }
	        		for (int j = 0; j < num; j++) {
	 	        	for (int i = 0; i < num; i++) {
	 	        		  if (magic[i][j] < 10)  System.out.print(" ");  // for alignment
			                if (magic[i][j] < 100) System.out.print(" ");  // for alignment
			                System.out.print(magic[i][j] + " ");
	 	        	}
	 	        	 System.out.println();
	        	 }
	        
	        	
	        }
	        
	}
	   
	   public int[][] rotateClockWise(int[][] matrix) {
		   int size = matrix.length;
		   int[][] ret = new int[size][size];

		   for (int i = 0; i < size; ++i) 
		    for (int j = 0; j < size; ++j) 
		     ret[i][j] = matrix[j][size-i-1];

		   return ret;
		  }
}
