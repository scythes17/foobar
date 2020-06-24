public class inversemat {
    public static void main(String args[]){
        int mat[][] = { { 0, -3, -2 }, { 1, -4, -2 }, { -3, 4, 1 }}; 
        float res[][] = InverseOfMatrix(mat);
        int order = mat.length;
        for(int i=0; i<order; i++){
            for(int j=0; j<order; j++){
                System.out.print(res[i][j]+"\t");
            }
            System.out.println();
        }  
    }

    static void simplify(int den3, int num3)  
    {  
        int common_factor = gcd(num3,den3);  
        den3 = den3/common_factor;  
        num3 = num3/common_factor; 
        System.out.println(num3+"/"+den3); 
    } 
    public static int gcd(int a, int b)  
    {  
        if (a == 0)  
            return b;  
        return gcd(b%a, a);  
    } 

    public static float[][] InverseOfMatrix(int mat[][]) {
        int order =mat.length;
        System.out.println(order);
        float matrix[][]= new float[order][order*2];
        float temp;
        float inv[][]= new float[order][order];
        /* Create the augmented matrix by adding identity matrix of same order  
        at the end of original matrix. */
        for (int i = 0; i < order; i++) { 
            for (int j = 0; j < 2 * order; j++) { 
                if(j<order){
                    matrix[i][j]=mat[i][j];
                }
                if (j == (i + order)) 
                    matrix[i][j] = 1;
            } 
        }  
        // Interchange the row of matrix, starting from last row
        for (int i = order - 1; i > 0; i--) { 
            if (matrix[i - 1][0] < matrix[i][0]){ 
                for (int j = 0; j < 2 * order; j++) {  
                    temp = matrix[i][j]; 
                    matrix[i][j] = matrix[i - 1][j]; 
                    matrix[i - 1][j] = temp; 
                }
            } 
        }
        // Replace a row by sum of itself and a constant multiple of another row of the matrix 
        for (int i = 0; i < order; i++) { 
            for (int j = 0; j < order; j++) {
                if (j != i) { 
                    temp = matrix[j][i] / matrix[i][i]; 
                    for (int k = 0; k < 2 * order; k++) { 
                        matrix[j][k] -= matrix[i][k] * temp; 
                    } 
                } 
            } 
        }
  
        // Multiply each row by a nonzero integer. 
        // Divide row element by the diagonal element 
        for (int i = 0; i < order; i++) { 
            temp = matrix[i][i]; 
            for (int j = 0; j < 2 * order; j++) { 
                matrix[i][j] = matrix[i][j] / temp;
                if(j>=order){
                    inv[i][j-order]=matrix[i][j];
                }
            } 
        }

  
        return inv;
    } 
}