// Subtraction of ascending and descending order of a number of a specific base until a pattern returns
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

class Lambda
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int b = 10;
        String n = sc.nextLine(); 
        int res = solution(n, b);
        System.out.println(res);
        sc.close();
    }
    
    public static int solution(String n, int b) {
        HashMap <String, Integer> map = new HashMap<>();
        int c =0;
        int x,y;
        int f=1;
        while(f>0){
            int l = n.length();
            char a[] = n.toCharArray();
            char d[] = new char[l];
            Arrays.sort(a);
            for(int i=l-1; i>=0; i--){
                d[i]=a[l-1-i];
            }
            String x_str = String.valueOf(d);
            String y_str = String.valueOf(a);
            x = Integer.parseInt(x_str,b);
            y = Integer.parseInt(y_str,b);

            String result = Integer.toString(x-y,b);
            if(result.length()<l){
                String add="";
                int diff = l-result.length();
                for(int i =0;i<diff; i++){
                    add=add+"0";
                }
            result = add+result;
            }
            if(map.containsKey(result)==true){
                if(map.get(result)==1){
                    c++;
                    map.replace(result,2);
                } else {
                    break;
                }
            } else {
                map.put(result,1);
            }
            n = result;
        }
        return c;
    }
}