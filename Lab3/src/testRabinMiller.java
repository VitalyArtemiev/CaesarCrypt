/*import java.math.BigInteger;
import java.util.Random;

public class testRabinMiller {
    private long p;
    testRabinMiller(long p)
    {
        this.p = p;
    }
    boolean run()
    {
        long b = 0;
        long tmp = p-1;
        while(tmp%2 == 0)
        {
            b++;
            tmp = tmp/2;
        }
        int m = Integer.parseInt(Long.toString(Math.round((p-1)/Math.pow(2, b))));
   //     System.out.println(m + " " + b);
        long a = Math.round(new Random(System.currentTimeMillis()).nextFloat()*p);
   //     System.out.println(a);
        long j = 0;
        BigInteger z = BigInteger.valueOf(a).pow(m).mod(BigInteger.valueOf(p));
     //   System.out.println(z);
        if (z.longValue() == p-1 ||  z.intValue() == 1)
        {
            return true;
        }
        while (true){
            if (z.longValue() == p-1 )
            {
                return true;
            }
            if (j == b && z.longValue() != p - 1)
            {
                return false;
            }
            if(j > 0 && z.intValue() == 1)
            {
                return false;
            }
            j++;
            z =  z.pow(2).mod(BigInteger.valueOf(p));
          //  System.out.println(z);
        }
    }
}*/

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

public class testRabinMiller {
    private long p;
    testRabinMiller(long p)
    {
        this.p = p;
    }
    boolean run()
    {
        long b = 0;
        long tmp = p-1;
        while(tmp%2 == 0)
        {
            b++;
            tmp = tmp/2;
        }
        long m = Math.round((p-1)/Math.pow(2, b));
        //     System.out.println(m + " " + b);
        long a = Math.round(new Random(System.currentTimeMillis()).nextFloat()*p);
        //     System.out.println(a);
        long j = 0;
    //    BigInteger z = BigInteger.valueOf(a).pow(m).mod(BigInteger.valueOf(p));
        BigInteger z = BigInteger.valueOf(a).modPow(BigInteger.valueOf(m), BigInteger.valueOf(p));
        //   System.out.println(z);
        if (z.longValue() == p-1 ||  z.intValue() == 1)
        {
            return true;
        }
        while (true){
            if (z.longValue() == p-1 )
            {
                return true;
            }
            if (j == b && z.longValue() != p - 1)
            {
                return false;
            }
            if(j > 0 && z.intValue() == 1)
            {
                return false;
            }
            j++;
            z =  z.pow(2).mod(BigInteger.valueOf(p));
            //  System.out.println(z);
        }
    }
}
