import java.util.HashMap;
import java.util.Map;
public class Efficiency {
    public static void main(String args[]){
        int ar1[]= {14, 27, 1, 4, 2, 50, 3, 1};
        int ar2[]= {2, 4, -4, 3, 1, 1, 14, 27, 50};
        int res = solution(ar1, ar2);
        System.out.println(res);
    }
    public static int solution(int ar1[], int ar2[]){
        int res = 0;
        HashMap <Integer, Integer> map = new HashMap<>();
        // initialise all values in ar1 to 1
        for(int i=0; i<ar1.length; i++){
            if(!map.containsKey(ar1[i])){
                map.put(ar1[i],1);
            }
        }
        // if val of ar2 exists in map change it to 0
        for(int i=0; i<ar2.length; i++){
            if(map.containsKey(ar2[i])){
                map.replace(ar2[i], 0);
            } else { //and if it doesn't, then add it with value 1
                map.put(ar2[i], 1);
            }
        }
        for(Map.Entry<Integer,Integer> mapElement: map.entrySet()){
            int key = (int) mapElement.getKey();
            int val = (int) mapElement.getValue();
            if(val!=0){ // the extra val will have it's value as 1
                res = key;
            }
        }
        return res;
    }
}