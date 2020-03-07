import java.math.BigInteger;
import java.util.HashSet;

class Eratosthenes{
    private HashSet<Integer> sieve = new HashSet<>();
    private HashSet<Integer> del = new HashSet<>();
    private int number;
    Eratosthenes(int number)
    {
        this.number = number;
    }
    void run()
    {
        for (int i = 2; i <= number; i++)
        {
            sieve.add(i);
        }
        for(int a: sieve)
        {
            int k = 2;
            if(!del.contains(a)){
                while (a*k <= number)
                {
                    del.add(a*k);
                    k++;
                }
            }
        }
        for(int a: del)
        {
            sieve.remove(a);
        }
    }
    boolean check(long a)
    {
        for (int b: sieve)
        {
            if (a % b == 0)
            {
                return false;
            }
        }
        return true;
    }
    int getE(BigInteger f)
    {
        int e = -1;
        for (int b: sieve)
        {
            if (!f.mod(BigInteger.valueOf(b)).equals(BigInteger.valueOf(0)))
            {
                e = b;
            }
        }
        return e;
    }
}
