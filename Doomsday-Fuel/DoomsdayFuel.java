// CHECK DIARY FOR ABSORBING MARKOV CHAIN
import java.util.ArrayList;
public class DoomsdayFuel {
    
    static ArrayList<Integer> absorb= new ArrayList<Integer>(); //absorbing elements
    static ArrayList<Integer> non = new ArrayList<Integer>(); // non absorbing elements
    static int sum_row[];

    public static long gcd(long num, long denom) {
        if (denom == 0) 
            return num; 
      return gcd(Math.abs(denom), Math.abs(num % denom));
    }

    public static long lcm(long x,long y){
        return (long)(x*y/gcd(x,y));
    }

    public static int[][] newMat(int[][] m){
        int order=m.length;
        int new_mat[][]= new int[order][order];
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
        return new_mat;
    }


    public static int[][] getR(int[][] m){
        int nsize=non.size();
        int asize=absorb.size();
        int r[][]= new int[nsize][asize];
        for(int i=0; i<nsize; i++){
            for(int j=0; j<asize; j++){
                r[i][j]=m[asize+i][j];
                sum_row[i]=sum_row[i]+r[i][j];
            }
        }
        //for it to work we need to take out probabilities (by dividing from sum of each row)
        return r;
    }

    public static int[][] getQ(int[][] m){
        int nsize=non.size();
        int asize=absorb.size();
        int q[][]= new int[nsize][nsize];
        for(int i=0; i<nsize; i++){
            for(int j=0; j<nsize; j++){
                q[i][j]=m[asize+i][asize+j];
                sum_row[i]=sum_row[i]+q[i][j];
            }
        }
        return q;
    }

    public static Fraction[][] getRfrac(int[][] r){
        Fraction rfrac[][]= new Fraction[non.size()][absorb.size()];
        for(int i=0; i<r.length; i++){
            for(int j=0; j<r[0].length; j++){
                rfrac[i][j]=new Fraction(r[i][j], sum_row[i]);
            }
        }
        return rfrac;
    }

    public static Fraction[][] getQfrac(int[][] q){
        Fraction qfrac[][]= new Fraction[non.size()][non.size()];
        for(int i=0; i<q.length; i++){
            for(int j=0; j<q[0].length; j++){
                qfrac[i][j]=new Fraction(q[i][j], sum_row[i]);
            }
        }
        return qfrac;
    }

    public static Fraction[][] getF(Fraction [][]q){
        int order=q.length;
        Fraction iden[][] = new Fraction[order][order];
        //identity matrix
        for (int i = 0; i<order; i++) {
            for(int j=0; j<order; j++){
                if(i==j){
                    iden[i][j]=new Fraction(1,1);
                } else {
                    iden[i][j]=new Fraction(0,1);
                }
            }
        }
        // Subtract by identity matrix
        Fraction f[][]= new Fraction[order][order];
        for(int i=0; i<order; i++){
            for(int j=0; j<order; j++){
                f[i][j]=iden[i][j].subtract(q[i][j]);
            }
        }
        f = InverseOfMatrix(f);
        return f;
    }

    public static Fraction[][] InverseOfMatrix(Fraction mat[][]) {
        int order =mat.length;
        Fraction matrix[][]= new Fraction[order][order*2];
        Fraction temp;
        Fraction inv[][]= new Fraction[order][order];
        /* Create the augmented matrix by adding identity matrix of same order  
        at the end of original matrix. */
        for (int i = 0; i < order; i++) { 
            for (int j = 0; j < 2 * order; j++) { 
                if(j<order)
                    matrix[i][j]=mat[i][j];
                else if (j == (i + order))
                    matrix[i][j] = new Fraction(1,1);
                else
                    matrix[i][j]= new Fraction(0,1);
            } 
        }
        System.out.println("AUGMENTED MATRIX= ");
        for (int i = 0; i < order; i++) { 
            for (int j = 0; j < 2 * order; j++) { 
                System.out.print(matrix[i][j]+"\t");
            } 
            System.out.println();
        }
        // Interchange the row of matrix, starting from last row
        for (int i = order - 1; i > 0; i--) { 
            System.out.println("Decimal of "+matrix[i - 1][0].getNumerator()+"/"+matrix[i - 1][0].getDenominator()+" = "+matrix[i - 1][0].toDecimal());
            System.out.println("Decimal of "+matrix[i][0].getNumerator()+"/"+matrix[i][0].getDenominator()+" = "+matrix[i][0].toDecimal());
            double x = matrix[i - 1][0].toDecimal();
            double y = matrix[i][0].toDecimal();
            System.out.println("x= "+x);
            System.out.println("y= "+y);
            if (matrix[i - 1][0].toDecimal() < matrix[i][0].toDecimal()){
                for (int j = 0; j < 2 * order; j++) {  
                    temp = matrix[i][j]; 
                    matrix[i][j] = matrix[i - 1][j]; 
                    matrix[i - 1][j] = temp; 
                }
            } 
        }
        System.out.println("ROW COLUMNNNNN MATRIX= ");
        for (int i = 0; i < order; i++) { 
            for (int j = 0; j < 2 * order; j++) { 
                System.out.print(matrix[i][j]+"\t");
            } 
            System.out.println();
        }
        // Replace a row by sum of itself and a constant multiple of another row of the matrix 
        for (int i = 0; i < order; i++) { 
            for (int j = 0; j < order; j++) {
                if (j != i) { 
                    temp = matrix[j][i].divide(matrix[i][i]);
                    for (int k = 0; k < 2 * order; k++) {
                        matrix[j][k] = matrix[j][k].subtract(matrix[i][k].multiply(temp)); 
                    } 
                } 
            }
        }
        System.out.println("MATRIX AFTER SUMMING= ");
        for (int i = 0; i < order; i++) { 
            for (int j = 0; j < 2 * order; j++) { 
                System.out.print(matrix[i][j]+"\t");
            } 
            System.out.println();
        }
        // Multiply each row by a nonzero integer, divide row element by the diagonal element 
        for (int i = 0; i < order; i++) { 
            temp = matrix[i][i]; 
            for (int j = 0; j < 2 * order; j++) { 
                matrix[i][j] = matrix[i][j].divide(temp);
                if(j>=order){
                    inv[i][j-order]=matrix[i][j];
                }
            } 
        }
        return inv;
    }

    public static Fraction[][] matMultiplication(Fraction[][] mat1, Fraction[][] mat2){
        Fraction res[][] = new Fraction[mat1.length][mat2[0].length];
        for (int i = 0; i < mat1.length; i++) 
        { 
            for (int j = 0; j < mat2[0].length; j++) 
            { 
                res[i][j] = new Fraction(0,1);
                for (int k = 0; k < mat1[0].length; k++)
                    res[i][j]= res[i][j].add(mat1[i][k].multiply(mat2[k][j]));
            }
        }
        return res;
    }

    public static int[] solution(int[][] m) {
        // Print end
        if(m.length<2){
            int ar[] = {1,1};
            return ar;
        }
        sum_row = new int[m.length];
        int new_mat[][] = newMat(m);
        int r1[][]= getR(new_mat); // integer before division with sum_row
        int q1[][]= getQ(new_mat); // integer before division with sum_row
        Fraction r[][] = getRfrac(r1);
        Fraction q[][] = getQfrac(q1);
        System.out.println("MATRIX R FRACTION: ");
        for(int i=0; i<r.length; i++){
            for(int j=0; j<r[0].length; j++){
                System.out.print(r[i][j].toString()+"\t");
            }
            System.out.println();
        }
        System.out.println("MATRIX Q FRACTION: ");
        for(int i=0; i<q.length; i++){
            for(int j=0; j<q[0].length; j++){
                System.out.print(q[i][j].toString()+"\t");
            }
            System.out.println();
        }
        System.out.println("MATRIX F FRACTION: ");
        Fraction f[][] = getF(q);
        for(int i=0; i<f.length; i++){
            for(int j=0; j<f[0].length; j++){
                System.out.print(f[i][j].toString()+"\t");
            }
            System.out.println();
        }
        System.out.println("MATRIX MULTIPLICATION FxR = ");
        Fraction fr[][]= matMultiplication(f, r);
        for(int i=0; i<f.length; i++){
            for(int j=0; j<r[0].length; j++){
                System.out.print(fr[i][j]+"\t");
            }
            System.out.println();
        }

        Fraction row[] = new Fraction[fr[0].length];
        row = fr[0];
        long l = 1;
        int res[] = new int[fr[0].length+1];
        int c;
        for(int i=0; i<fr[0].length; i++){
            Fraction item = row[i];
            l = (long)lcm(l,item.getDenominator());
        }
        for(c=0; c<row.length; c++){
            Fraction item = row[c];
            res[c] = (int)(item.getNumerator()*l/item.getDenominator());
        }
        res[c]=(int)l;
        return res;
    }
    
    public static void main(String args[]){
        //int m[][] = {{0, 2, 1, 0, 0}, {0, 0, 0, 3, 4}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
        //int m[][] = {{0, 1, 0, 0, 0, 1}, {4, 0, 0, 3, 2, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}};
        //int m[][] ={{1, 2, 3, 0, 0, 0},{4, 5, 6, 0, 0, 0},{7, 8, 9, 1, 0, 0},{0, 0, 0, 0, 1, 2},{0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0}};
        //int m[][] = {{0}};
        int m[][] = {{0, 0, 12, 0, 15, 0, 0, 0, 1, 8},{0, 0, 60, 0, 0, 7, 13, 0, 0, 0},{0, 15, 0, 8, 7, 0, 0, 1, 9, 0},{23, 0, 0, 0, 0, 1, 0, 0, 0, 0},{37, 35, 0, 0, 0, 0, 3, 21, 0, 0},{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        //above one is giving wrong answer
        /*int m[][] = {{0, 7, 0, 17, 0, 1, 0, 5, 0, 2},
            {0, 0, 29, 0, 28, 0, 3, 0, 16, 0},
            {0, 3, 0, 0, 0, 1, 0, 0, 0, 0},
            {48, 0, 3, 0, 0, 0, 17, 0, 0, 0},
            {0, 6, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};*/
        /*int m[][]= {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};*/
        /*int m[][]={{1, 1, 1, 0, 1, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 1, 1, 1, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 1, 0, 1, 1, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 1, 0, 1, 0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};*/
        /*int m[][] = {{0, 86, 61, 189, 0, 18, 12, 33, 66, 39},
            {0, 0, 2, 0, 0, 1, 0, 0, 0, 0},
            {15, 187, 0, 0, 18, 23, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};*/
        /*int m[][] = {{0, 0, 0, 0, 3, 5, 0, 0, 0, 2},
            {0, 0, 4, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 4, 4, 0, 0, 0, 1, 1},
            {13, 0, 0, 0, 0, 0, 2, 0, 0, 0},
            {0, 1, 8, 7, 0, 0, 0, 1, 3, 0},
            {1, 7, 0, 0, 0, 0, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};*/
        int res[]=solution(m);
        for(int i=0; i<res.length; i++){
            System.out.print(res[i]+"\t");
        }
        System.out.println();
    }
}


class Fraction{
    private long numerator, denominator;

    public Fraction(){
        this.numerator=0;
        this.denominator=1;
        reduce();
    }
    // multiply the decimal by 10 for all values behind the decimal point,
    // denominator will be the number of 10s numerator is multiplied with.
    public Fraction(double d) {
        String s = String.valueOf(d);
        int digitsDec = s.length() - 1 - s.indexOf('.');
        long denom = 1;
        for (int i = 0; i < digitsDec; i++) {
            d *= 10;    
            denom *= 10;
        }

        long num = (long) Math.round(d);
        this.numerator = num;
        this.denominator = denom;
        reduce();
    }
    
    public Fraction(long num, long denom) {
        this.numerator = num;
        this.denominator = denom;
        reduce();
    }

    public String toString() {
        return String.valueOf(numerator) + "/" + String.valueOf(denominator);
    }

    public long gcd(long num, long denom) {
        if (denom == 0)
            return num;
        return gcd(Math.abs(denom), Math.abs(num % denom));  
    }

    public long getNumerator(){
        return this.numerator;
    }

    public long getDenominator(){
        return this.denominator;
    }
    // integer division works like Math.floor()
    public double toDecimal(){
        //debug after adding (double)/(double)
        return numerator/denominator;
    }

    public Fraction add(Fraction a) {
        long num =(numerator * a.denominator) + (a.numerator * denominator);
        long den = denominator * a.denominator;
        Fraction res = new Fraction(num, den);
        return res;
    }

    public Fraction subtract(Fraction a) {
        long num = (numerator * a.denominator)-(a.numerator * denominator);
	    long den = denominator * a.denominator;
	    Fraction res = new Fraction(num, den);
	    return res;
    }

    public Fraction multiply(Fraction a) {
        long num = numerator * a.numerator;
        long den = denominator * a.denominator;
        Fraction res = new Fraction(num, den);
        return res;
    }

    public Fraction divide(Fraction a) {
        long num = numerator * a.denominator;
        long den = denominator * a.numerator;
        Fraction res = new Fraction(num, den);
        return res;
        }

    /*public void reduce() {
        boolean neg = (numerator < 0) != (denominator < 0);
        //get gcd
        numerator = Math.abs(numerator);
        denominator = Math.abs(denominator);
        long g = gcd(numerator, denominator);
        //get reduced numerator and denominator
        numerator /= g;
        denominator /= g;
        //adjust for negative
        if (neg) {
            numerator *= -1;
        }
        long g = gcd(numerator,denominator);
        numerator = (numerator/g);
        denominator = (denominator/g);
    }*/
    public void reduce() {
        boolean neg = (numerator < 0) != (denominator < 0);
        numerator = Math.abs(numerator);
        denominator = Math.abs(denominator);
        long g = gcd(numerator, denominator);
    
        numerator /= g;
        denominator /= g;
        if (neg) {
            numerator *= -1;
        }
    }
}