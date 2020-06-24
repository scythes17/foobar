//Highest number from the digits of a number (int array) divisible by 3

import java.util.Arrays;
public class CodedMessages {
    
    public static void main(String args[]){
        int l[] = {3, 1, 4, 1, 5, 9, 9, 0};
        int x = solution(l);
        System.out.println(x);
    }

    public static int add(int l[], int ind1, int ind2){
        int sum=0;
        for(int i=l.length-1; i>=0; i--){
            if(i!=ind1 && i!=ind2){
                sum = sum*10+l[i];
            }
        }
        return sum;
    }
    public static int solution(int l[]){
        Arrays.sort(l);
        int n = l.length;
        int sum=0, ind1=-1, ind2=-1;
        sum = add(l, -1, -1);

        if(sum%3==0){ // if all digits added in desc order is div by 3 then it's the biggest number
            return sum;
        }

        // obviously removing 1 digit is preferable
        if(sum%3==1){ // if remainder is 1 we can either remove 1 digit that gives us remainder 1
            for(int i=0; i<n; i++){
                if(l[i]%3==1){
                    sum = add(l, i, -1);
                    return sum;
                }

                if(l[i]%3==2){ // or 2 digits giving remainder 2
                    if(ind1 == -1){ //store the smallest digit with remainder 2
                        ind1=i;
                    } else if(ind2 == -1){ // store the second smallest digit with remainder 2
                        ind2=i;
                    }
                }
            }
        } else{ // if the remainder is 2
            for(int i=0; i<n; i++){
                if(l[i]%3==2){ // remove 1 digit with remainder 2
                    sum = add(l, i, -1);
                    return sum;
                }

                if(l[i]%3==1){ // or 2 digits with remainder 1
                    if(ind1 == -1){ //smallest digit with rem 1
                        ind1=i;
                    } else if(ind2 == -1){ //second smallest digit with rem 1
                        ind2 = i;
                    }
                }
            }

        }
        /* removing the stored number obviously goes in if the return statement in the
         above loop isn't executed */
        if(ind1!=-1 && ind2!=-1){
            sum = add(l, ind1, ind2);
        } else {
            sum =0;
        }
        return sum;
    }
}