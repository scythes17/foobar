class Fraction2
{
    int num=0,den=1;
    public void sub(int a, int b, int c, int d)
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
            int hcf = gcd(b, d);
            den = (b * d) / hcf;
            num = (a * (den / b)) - (c * (den) / d);
        }
        int hcf2 = gcd(Math.abs(num), Math.abs(den));
        num /= hcf2;
        den /= hcf2;
        System.out.println(num+"  "+den);
        System.out.println();
    }

    public void mul(int a,int b, int c, int d)
    {
        num=a*c;
        den=b*d;
        int hcf=gcd(Math.abs(num), Math.abs(den));
        num/=hcf;
        den/=hcf;
    }

    public void add(int a, int b, int c, int d)
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
                int hcf = gcd(b, d);
                den = (b * d) / hcf;
                num = (a * (den / b)) + (c * (den) / d);
            }
        int hcf2 = gcd(Math.abs(num), Math.abs(den));
        num /= hcf2;
        den /= hcf2;
    }

    public int gcd(int a, int b)
    {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }
}