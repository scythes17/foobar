// Lucky Triples
public class AccessCodes {
    public static void main(String args[]){
        int l[]={1,2,3,4,5,6,8,16};
        // int l[]={1,1,1};
        System.out.println(solution(l));
    }
    public static int solution(int[] l) {
        // Your code here
        int len=l.length;
        int arr[]= new int[len]; //to store no of elements that already divide arr[i]
        /* if l[j]%l[i] store +1 in arr[j] (which means arr[j] was divisible by some other element)
        everytime you add +1 to arr[j] it means there's one more element to add to the count if
        any number is divisible by it. Example:
        say {1,2,3,4,5,6,8,16} when 16 is divisible by 8 it means it makes {1,8,16};{2,8,16};{4,8,16}
        triplets so arr[8] which reflects 3(1,2,8; 1,4,8; 2,4,8) adds 3 to the count*/
        int count =0;
        for(int i=0; i<len; i++){
            for(int j=i+1; j<len; j++){
                if(l[j]%l[i]==0){
                    arr[j]=arr[j]+1;
                    count = count+arr[i]; 
                    System.out.println("arr[i]="+arr[i]+" i="+l[i]+" j="+l[j]+" count="+count); 
                }
            }
        }
        /* for(int i=0; i<len; i++){
            for(int j=i+1; j<len; j++){
                if(l[j]%l[i]==0){
                    for(int k=j+1; k<len; k++){
                        if(l[k]%l[j]==0)
                            count++;
                    }
                }
            }
        } */
        return count;
    }
    
}