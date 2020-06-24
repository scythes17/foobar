public class Fraction1{
    private int numerator, denominator;
    // multiply the decimal by 10 for all values behind the decimal point,
    // denominator will be the number of 10s numerator is multiplied with
    public Fraction1(double d) {
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

    public Fraction1(int num, int denom) {
        this.numerator = num;
        this.denominator = denom;
    }

    public String toString() {
        return String.valueOf(numerator) + "/" + String.valueOf(denominator);
    }

    public static int gcd(int num, int denom) {
        if (denom == 0) 
        return num; 
      return gcd(denom, num % denom);  
    }

    /*public static Fraction1 simplify(int x,int y){
        int g = gcd(x,y);
        return Fraction1((int)(x/g), (int)(y/g));
    }*/
    public static void main(String[] args) {
        Fraction1 r = new Fraction1(0.21428572);
        System.out.println(r);
    }
}