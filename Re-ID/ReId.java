public class ReId {
    public static void main(String args[]){
        System.out.println(Solution(3));
    }
    public static boolean isPrime(int n){
        for(int i=2; i<=Math.sqrt(n); i++){
            if(n%i==0)
                return false;
        }
        return true;
    }
    public static String Solution(int i){
        String s ="";
        for(int j=2; j>0; j++){
            if(isPrime(j)){
                s=s+Integer.toString(j);
                System.out.println(s);
            }
            if(s.length()>i+5)
                break;
        }
        return s.substring(i,i+5);
    }
}