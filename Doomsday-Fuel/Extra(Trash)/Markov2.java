// CHECK DIARY FOR ABSORBING MARKOV CHAIN
import java.util.ArrayList;
public class Markov2 {
    public static int solution(int[][] m) {
        // Your code here
        ArrayList<Integer> absorb= new ArrayList<Integer>(); //absorbing elements
        ArrayList<Integer> non = new ArrayList<Integer>(); // non absorbing elements
        int order=m.length;
        int new_mat[][]= new int[order][order];
        float sum_row[] = new float[order];
        for(int i=0; i<order; i++){
            int sum =0;
            for(int j=0; j<order; j++){
                sum = sum+m[i][j];
            }
            if(sum==0){ // if sum is 0(or 1 depends on question) it's absorbing
                absorb.add(i);
            } else {
                non.add(i);
            }
        }
        // bring absorbing elements to the front
        for(int i=0; i<absorb.size(); i++){
            int x=absorb.get(i);
            for(int j=0; j<absorb.size(); j++){
                int y=absorb.get(j);
                new_mat[i][j]=m[x][y];
            }
            for(int k=0; k<non.size(); k++){
                int z=non.get(k);
                new_mat[i][absorb.size()+k]=m[x][z];
            }
        }
        // add non absorbing elements to the back of it
        for(int i=0; i<non.size(); i++){
            int x=non.get(i);
            for(int j=0; j<absorb.size(); j++){
                int y=absorb.get(j);
                new_mat[absorb.size()+i][j]=m[x][y];
            }
            for(int k=0; k<non.size(); k++){
                int z=non.get(k);
                new_mat[absorb.size()+i][absorb.size()+k]=m[x][z];
            }
        }

        //print
        for(int i=0; i<order; i++){
            for(int j=0; j<order; j++){
                System.out.print(new_mat[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("Original form: ");
        for(int i=0; i<order; i++){
            for(int j=0; j<order; j++){
                System.out.print(m[i][j]+" ");
            }
            System.out.println();
        }
        //print end
        // form r (lower left corner)
        float r[][]= new float[non.size()][absorb.size()];
        for(int i=0; i<non.size(); i++){
            for(int j=0; j<absorb.size(); j++){
                r[i][j]=new_mat[absorb.size()+i][j];
                sum_row[i]=sum_row[i]+r[i][j];
            }
        }
        // form q (lower right corner)
        float q[][]= new float[non.size()][non.size()];
        for(int i=0; i<non.size(); i++){
            for(int j=0; j<non.size(); j++){
                q[i][j]=new_mat[absorb.size()+i][absorb.size()+j];
                sum_row[i]=sum_row[i]+q[i][j];
            }
        }
        System.out.println("SUMSSSSSS");
        for(int i =0; i<non.size(); i++){
            System.out.println("sum["+i+"] = "+sum_row[i]);
        }
        //Print
        System.out.println("MATRIX R= ");
        for(int i=0; i<non.size(); i++){
            for(int j=0; j<absorb.size(); j++){
                r[i][j]=r[i][j]/sum_row[i];
                System.out.print(r[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("MATRIX Q= ");
        for(int i=0; i<non.size(); i++){
            for(int j=0; j<non.size(); j++){
                q[i][j]=q[i][j]/sum_row[i];
                System.out.print(q[i][j]+"\t");
            }
            System.out.println();
        }
        // find length of q;
        int order2=q.length;
        int iden[][] = new int[order2][order2];
        for (int i = 0; i < order2; i++) {
             iden[i][i]=1;
        }

        float f[][]= new float[order2][order2];
        for(int i=0; i<order2; i++){
            for(int j=0; j<order2; j++){
                f[i][j]=iden[i][j]-q[i][j];
            }
        }
        System.out.println("I-Q = ");
        for(int i=0; i<order2; i++){
            for(int j=0; j<order2; j++){
                System.out.print(f[i][j]+"\t");
            }
            System.out.println();
        }  
        f = InverseOfMatrix(f);
        System.out.println("INVERSE = ");
        for(int i=0; i<order2; i++){
            for(int j=0; j<order2; j++){
                System.out.print(f[i][j]+"\t");
            }
            System.out.println();
        } 
        System.out.println("FR = ");
        float fr[][]= matMultiplication(f, r);
        for(int i=0; i<f.length; i++){
            for(int j=0; j<r[0].length; j++){
                System.out.print(fr[i][j]+"\t");
            }
            System.out.println();
        } 
        // Print end
        return order;
    }
    
    public static float[][] InverseOfMatrix(float mat[][]) {
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

    public static float[][] matMultiplication(float[][] mat1, float[][] mat2){
        float res[][] = new float[mat1.length][mat2[0].length];
        for (int i = 0; i < mat1.length; i++) 
        { 
            for (int j = 0; j < mat2[0].length; j++) 
            { 
                res[i][j] = 0;
                for (int k = 0; k < mat1[0].length; k++) 
                    res[i][j] += mat1[i][k]  
                                * mat2[k][j]; 
            }
        }
        return res;
    }
    public static int gcd(int num, int denom) {
        if (denom == 0) 
        return num; 
      return gcd(denom, num % denom);  
    }

    public static Fraction simplify(int x,int y){
        int g = gcd(x,y);
        Fraction frac = new Fraction((int)(x/g), (int)(y/g));
        return frac;
    }

    public static void main(String args[]){
        //int m[][] = {{0, 2, 1, 0, 0}, {0, 0, 0, 3, 4}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
        int m[][] = {{0, 1, 0, 0, 0, 1}, {4, 0, 0, 3, 2, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}};
        System.out.println(solution(m));
    }
}
    
class Fraction{
    private int numerator, denominator;

    public Fraction(double d) {
        String s = String.valueOf(d);
        int digitsDec = s.length() - 1 - s.indexOf('.');
        int denom = 1;
        for (int i = 0; i < digitsDec; i++) {
            d *= 10;    
            denom *= 10;
        }

        int num = (int) Math.round(d);
        int g = gcd(num, denom);
        this.numerator = num / g;
        this.denominator = denom /g;
    }
    
    public Fraction(int num, int denom) {
        this.numerator = num;
        this.denominator = denom;
    }

    public String toString() {
        return String.valueOf(numerator) + "/" + String.valueOf(denominator);
    }

    public int gcd(int num, int denom) {
        if (denom == 0)
        return num; 
      return gcd(denom, num % denom);  
    }
}