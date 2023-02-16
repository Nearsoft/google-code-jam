import java.util.*;
import java.io.*;
import java.math.BigInteger;

class Solution
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int test = input.nextInt();

        for (int z = 1; z <= test; z++)
        {
          BigInteger bigint = input.nextBigInteger();
          int l = input.nextInt();
          BigInteger[] a=new BigInteger[102];

          for(int i=0;i<l;i++)
          {
             a[i]=input.nextBigInteger();
          }

          Set<BigInteger> s = new HashSet<BigInteger>();
          BigInteger y;
          int j=1;
          BigInteger zz=a[0].gcd(a[j]);
          int e=0;

          while(zz.compareTo(a[0])==0)
          {
            zz=a[0].gcd(a[j]);
            j++;
            e=1;
          }

          if(e==1) j--;
          BigInteger[] ans=new BigInteger[103];
          ans[j]=a[0].gcd(a[j]);

          for(int i=j;i<l;i++)
          {
             y=ans[i];
             ans[i+1]=a[i].divide(y);
          }

          for(int i=j;i>0;i--)
          {
             y=ans[i];
             ans[i-1]=a[i-1].divide(y);
          }

          for(int i=0;i<=l;i++) s.add(ans[i]);
          Map<BigInteger,Character> hashMap = new HashMap<>();
          j=0;
          BigInteger[] b=new BigInteger[26];

          for (BigInteger i : s)
          {
               b[j]=i;
               j++;
          }

          Arrays.sort(b);

          for(int i=0;i<26;i++)
          {
            char r=(char)('A'+i);
            hashMap.put(b[i],r);
          }

          String s1="";

          for(int i=0;i<=l;i++)
          {

                s1+=hashMap.get(ans[i]);
          }

          System.out.println("Case #" + z + ": "+s1);
        }
    }
}
