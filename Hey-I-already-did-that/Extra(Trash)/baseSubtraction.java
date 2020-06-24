/* package whatever; // don't place package name! */
// Subtraction of ascending and descending order of a number of a specific base until a pattern returns
import java.util.*;

/* Name of the class has to be "Main" only if the class is public. */
class baseSubtraction
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner sc = new Scanner(System.in);
		int b = 3;
		String n = sc.nextLine();
		int x,y;
        int l = n.length();
        char a[] = n.toCharArray();
        char d[] = new char[l];
        //creating ascending array
        Arrays.sort(a);
        //creating descending array
        for(int i=0; i<l; i++){
            d[l-1-i]=a[i];
        }
        //convert char array into string
        String x_str = String.valueOf(d);
        String y_str = String.valueOf(a);
        //base subtraction needs you to convert it into int of the specified base first
        x = Integer.parseInt(x_str,b);
        y = Integer.parseInt(y_str,b);
        String result = Integer.toString(x-y,b);
        System.out.println(result);
        //add preceding 0s to maintain digits length
        if(result.length()<n.length()){
            String add="";
            int diff = l-result.length();
            for(int i =0;i<diff; i++){
                add=add+"0";
            }
            System.out.println("add= "+add);
            result = add+result;
        }
        System.out.println(result);
        sc.close();
	}
}