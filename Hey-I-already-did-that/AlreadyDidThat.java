// Subtraction of ascending and descending order of a number of a specific base until a pattern returns
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

class AlreadyDidThat
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int b = 3;
        String n = sc.nextLine(); 
        int res = solution(n, b);
        System.out.println(res);
        sc.close();
    }
    //verify (add preceding zeroes) if length of new minion id doesn't match new id
    public static String verify(String s, int l){
        if(s.length()<l){
            String add="";
            int diff = l-s.length();
            for(int i =0;i<diff; i++){
                add=add+"0";
            }
            s = add+s;
        }
        return s;
    }

    public static int solution(String n, int b) {
        HashMap <String, Integer> map = new HashMap<>();
        int c =0;
        int x,y;
        int k=1;
        while(k>0){
            int l = n.length();
            char a[] = n.toCharArray();
            char d[] = new char[l];
            Arrays.sort(a);
            for(int i=0; i<l; i++){
                d[l-1-i]=a[i];
            }
            String x_str = String.valueOf(d);
            String y_str = String.valueOf(a);
            x = Integer.parseInt(x_str,b);
            y = Integer.parseInt(y_str,b);

            String result = Integer.toString(x-y,b);
            //verify if length is ok
            result = verify(result, l);
            //check if pattern of minion ids repeats
            if(map.containsKey(result)==true){ // if minion id was encountered before
                if(map.get(result)==1){ // if val is 1 then make it 2
                    c++; // increase the count of the number of ids repeating
                    map.replace(result,2); // make the val 2 so that function breaks if id repeats a third time
                } else {
                    break;
                }
            } else { // if id wasn't encountered, add it
                map.put(result,1);
            }
            n = result; // the new number will be the result of base subtraction
        }
        return c;
    }
}