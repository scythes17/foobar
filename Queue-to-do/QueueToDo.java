public class QueueToDo {
    
    public static void main(String[] args)
    {
        int start = 17;
        int length = 4;
        int res = solution(start, length);
        System.out.println(res);
    }

    public static int xor(int n){
        int rem = n%4;
        if(rem==0)
            return n;
        else if(rem==1)
            return 1;
        else if(rem==2)
            return n+1;
        else
            return 0;
    }

    public static int solution(int start, int length) {
        int checksum=0, count=0;
        int l = length*length;
        int c=0;
        /*check problem statement to understand start+length-count 
        (number of elements keep decreasing) as we go down the matrix according to problem */
        while(c<l){
            count ++;
            //xor existing checksum to xor of a to b(Starting and end points of matrix)
            checksum^=xor(start-1)^xor(start+length-count); // xor(a-1)^xor(b) for xor(a to b)
            start = start+length;
            c=c+length;
        }
        return checksum;
    }
}