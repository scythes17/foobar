// Postorder tree traversal, find parent of a node for a perfect binary tree(post order)
import java.lang.Math;
class IonFlux
{
    public static void main(String[] args)
    {
        int h=5; //height
        int n=4; //length of array
        int q[]= {4,7,3,14};
        int sol[];
        sol=solution(h,q);
        for(int i=0;i<n;i++)
            System.out.print(sol[i]+",");
    }
    public static int[] solution(int h, int q[])
    {
    	int sol[]=new int[q.length];
        int finroot =(int)(Math.pow(2,h)-1); //root node
        int left, right;
    	for(int i=0; i<q.length; i++){ //for each node to check
            int root =finroot;
    		if(q[i]==root){
    			sol[i]=-1;
    			continue;
            }
            for(int j=0; j<h; j++){ 
                left = (int)(root-(Math.pow(2,h-1-j))); //formula for left node (descending from root)
    		    right = root-1; // for right node descending from root
    		    if(q[i]==left || q[i]==right){ 
                    sol[i]=root;
                    break;
    		    }
    		    else {
    			    if(q[i] < left){ // if node value is less than left it's inside the left subtree
    				    root = left;
    			    } else { // if node value exceeds left it is in right subtree
    				    root = right;
    			    }
                }
            }
    	}
    	return sol;
    }
}