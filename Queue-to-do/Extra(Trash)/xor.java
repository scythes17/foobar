// xor a matrix of numbers (check foobar for proper description)
public class xor {
    
    public static void main(String[] args)
    {
        int start = 17;
        int length = 4;
        int res = solution(start, length);
        System.out.println(res);
    }

    public static int solution(int start, int length) {
        int checksum=start, count=0;
        start++;
        int l = length*length;
        // xor each number to checksum (except the excluded ones according to problem statement)
        for(int i=1; i<l; i++){
            if(i>1 && i%length==0){
                count ++;
            }
            if(i>=(((count+1)*length)-count)){
                start++;
                continue;
            }
            checksum = checksum^start;
            start++;
        }
        return checksum;
    }
}