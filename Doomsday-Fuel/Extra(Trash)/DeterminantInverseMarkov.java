public class DeterminantInverseMarkov   //Doomsday Fuel
{
    static int N;
    public static void main(String[] args)
    {
        int arr[][]={{0}};
        /*{{0, 7, 0, 17, 0, 1, 0, 5, 0, 2},
        {0, 0, 29, 0, 28, 0, 3, 0, 16, 0},
        {0, 3, 0, 0, 0, 1, 0, 0, 0, 0},
        {48, 0, 3, 0, 0, 0, 17, 0, 0, 0},
        {0, 6, 0, 0, 0, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};*/
        int arr2[]=solution(arr);
        for(int i:arr2)
                System.out.print(i+" ");
    }
    public static int[] solution(int m[][])
    {
        if(m.length<2){
            int ar[] = {1,1};
            return ar;
        }
        int res[][]=new int[m.length][m.length];
        int c=interchange(m,res);
        int c1,c2;

        int arr[][]=new int[m.length-c][m.length-c];    //To store Q
        c1=0;
        for(int i=c;i<m.length;i++)
        {
            c2=0;
            for(int j=c;j<m.length;j++)
            {
                arr[c1][c2]=res[i][j];
                c2++;
            }
            c1++;
        }
        int s;c1=0;
        Fraction Q[][]=new Fraction[m.length-c][m.length-c];      //To store I-Q
        for(int i=c;i<res.length;i++)
        {
            c2 = c;
            s = 0;
            for (int j = 0; j < res.length; j++)
                s += res[i][j];
           for(int k=0;k<Q.length;k++)
               {
                   Q[c1][k]=new Fraction();
                   if(c1==k)
                       Q[c1][k].sub(1,1,res[i][c2++],s);
                   else
                       Q[c1][k].sub(0,1,res[i][c2++],s);
               }
            c1++;
        }
        N=Q.length;

        Fraction [][]inv = new Fraction[N][N];    //To store F
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
                inv[i][j] = new Fraction();
        }
        inverse(Q, inv);

        Fraction R[][]=new Fraction[m.length-c][c];

        c1=0;
        for(int i=c;i<m.length;i++)
        {
            c2=0;
            s = 0;
            for (int k = 0; k < res.length; k++)
                s += res[i][k];
            for(int j=0;j<c;j++)
            {
                R[c1][c2] = new Fraction();
                R[c1][c2].num=res[i][j];
                if(res[i][j]!=0)
                    R[c1][c2].den=s;
                else
                    R[c1][c2].den=1;
                c2++;
            }
            c1++;
        }

        Fraction result[][]=new Fraction[inv.length][R[0].length];
        for(int i=0;i<inv.length;i++)
        {
            for(int j=0;j<R[0].length;j++)
                result[i][j] = new Fraction();
        }


        multiply(inv,R,result);

        int denominators[]=new int[result[0].length];
        for(int i=0;i<denominators.length;i++)
            denominators[i]=(int)result[0][i].den;

        int lcm_of_denominators=lcm(denominators);

        int ar[]=new int[result[0].length+1];

        for(int i=0;i<denominators.length;i++)
            ar[i]=(int)result[0][i].num*(lcm_of_denominators/(int)result[0][i].den);

        ar[denominators.length]=lcm_of_denominators;

        return ar;
    }

    public static int interchange(int arr[][],int res[][])
    {
        int i,j,f,c=0,arr2[][]=new int[arr.length][arr.length],a[]=new int[arr.length];
        for(i=0;i<arr.length;i++)
        {
            f=0;
            for(j=0;j<arr[0].length;j++)
            {
                if(arr[i][j]!=0)
                    f = 1;
            }
            if(f==0)
            {c++;a[i]=1;}
            else
                a[i]=0;
        }
        int c1=0,t=c;
        for(i=0;i<a.length;i++)
        {
            if(a[i]==0)
            {
                for(j=0;j<a.length;j++)
                    arr2[t][j]=arr[i][j];
                t++;
            }
            else
            {
                for (j = 0; j < a.length; j++)
                    arr2[c1][j] = arr[i][j];
                c1++;
            }
        }
        c1=0;t=c;
        for(i=0;i<a.length;i++)
        {
            if(a[i]==0)
            {
                for(j=0;j<a.length;j++)
                    res[j][t]=arr2[j][i];
                t++;
            }
            else
            {
                for(j=0;j<a.length;j++)
                    res[j][c1]=arr2[j][i];
                c1++;
            }
        }
        return c;
    }

    static void getCofactor(Fraction A[][], Fraction temp[][], int p, int q, int n)
    {
        int i = 0, j = 0;
        for (int row = 0; row < n; row++)
        {
            for (int col = 0; col < n; col++)
            {
                if (row != p && col != q)
                {
                    temp[i][j].num = A[row][col].num;
                    temp[i][j].den = A[row][col].den;
                    j++;
                    if (j == n - 1)
                    {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }
    static Fraction determinant(Fraction A[][], int n)
    {
        Fraction D=new Fraction();
        D.num=0;
        D.den=1;
        if (n == 1)
            return A[0][0];

        Fraction [][]temp = new Fraction[N][N];
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
                temp[i][j] = new Fraction();
        }

        Fraction [][]temp2 = new Fraction[N][N];
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
                temp2[i][j] = new Fraction();
        }

        int sign = 1;
        for (int f = 0; f < n; f++)
        {
            getCofactor(A, temp, 0, f, n);
            temp2[0][f].mul(A[0][f].num,A[0][f].den,sign,1);
            Fraction det = determinant(temp, n-1);
            temp2[0][f].mul(temp2[0][f].num,temp2[0][f].den,det.num,det.den);
            D.add(D.num,D.den,temp2[0][f].num,temp2[0][f].den);
            sign = -sign;
        }
        return D;
    }
    static void adjoint(Fraction A[][], Fraction [][]adj)
    {
        if (N == 1)
        {
            adj[0][0].num = 1;
            adj[0][0].den = 1;
            return;
        }
        int sign = 1;
        Fraction [][]temp = new Fraction[N][N];
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
                temp[i][j] = new Fraction();
        }
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                getCofactor(A, temp, i, j, N);
                sign = ((i + j) % 2 == 0)? 1: -1;
                adj[j][i].mul(determinant(temp, N-1).num,determinant(temp, N-1).den,sign,1);
            }
        }
    }
    static void inverse(Fraction A[][], Fraction [][]inverse)
    {

        Fraction det = new Fraction();
        det.num=determinant(A, N).num;
        det.den=determinant(A, N).den;
        Fraction [][]adj = new Fraction[N][N];
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
                adj[i][j] = new Fraction();
        }
        adjoint(A, adj);

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                inverse[i][j].mul(adj[i][j].num,adj[i][j].den,det.den,det.num);
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                inverse[i][j].mul(adj[i][j].num,adj[i][j].den,det.den,det.num);

    }

    public static int lcm(int[] element_array)
    {
        int lcm_of_array_elements = 1;
        int divisor = 2;

        while (true) {
            int counter = 0;
            boolean divisible = false;

            for (int i = 0; i < element_array.length; i++) {

                if (element_array[i] == 0) {
                    return 0;
                }
                else if (element_array[i] < 0) {
                    element_array[i] = element_array[i] * (-1);
                }
                if (element_array[i] == 1) {
                    counter++;
                }
                if (element_array[i] % divisor == 0) {
                    divisible = true;
                    element_array[i] = element_array[i] / divisor;
                }
            }

            if (divisible) {
                lcm_of_array_elements = lcm_of_array_elements * divisor;
            }
            else {
                divisor++;
            }
            if (counter == element_array.length) {
                return lcm_of_array_elements;
            }
        }
    }


    public static void multiply(Fraction mat1[][], Fraction mat2[][], Fraction result[][])
    {
        int i, j, k;
        Fraction temp=new Fraction();
        for (i = 0; i < mat1.length; i++)
        {
            for (j = 0; j <mat2[0].length; j++)
            {
                result[i][j].num = 0;
                result[i][j].den = 1;
                for (k = 0; k < mat2.length; k++)
                {

                    temp.mul(mat1[i][k].num,mat1[i][k].den,mat2[k][j].num, mat2[k][j].den);
                    result[i][j].add(result[i][j].num,result[i][j].den,temp.num,temp.den);
                }
            }
        }
    }

}

class Fraction
{
    long num,den;
    Fraction()
    {
        num=0;
        den=1;
    }
    public void sub(long a, long b, long c, long d)
    {
        if(c==0)
        {
            num=a;
            den=b;
        }
        else if(a==0)
        {
            num=-c;
            den=d;
        }
        else
        {
            long hcf = gcd(b, d);
            den = (b * d) / hcf;
            num = (a * (den / b)) - (c * (den) / d);
        }
        long hcf2 = gcd(Math.abs(num), Math.abs(den));
        num /= hcf2;
        den /= hcf2;
        if(num<0 && den<0)
        {
            num=Math.abs(num);
            den=Math.abs(den);
        }
    }

    public void mul(long a,long b, long c, long d)
    {
        num=a*c;
        den=b*d;
        long hcf=gcd(Math.abs(num), Math.abs(den));
        num/=hcf;
        den/=hcf;
        if(num<0 && den<0)
        {
            num=Math.abs(num);
            den=Math.abs(den);
        }
    }

    public void add(long a, long b, long c, long d)
    {
        if(c==0)
        {
            num=a;
            den=b;
        }
        else if(a==0)
        {
            num=c;
            den=d;
        }
        else
        {
            long hcf = gcd(b, d);
            den = (b * d) / hcf;
            num = (a * (den / b)) + (c * (den) / d);
        }
        long hcf2 = gcd(Math.abs(num), Math.abs(den));
        num /= hcf2;
        den /= hcf2;
        if(num<0 && den<0)
        {
            num=Math.abs(num);
            den=Math.abs(den);
        }
    }

    public long gcd(long a, long b)
    {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }
}