import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    static Eratosthenes e = new Eratosthenes(2000);
    static int b = 20;
    static int countTest = 5;
    public static void main(String[] args) {
        System.out.println();
        e.run();
        long p = generate();
        long q = generate();
        System.out.println(p + " " + q);
        BigInteger n = BigInteger.valueOf(p).multiply(BigInteger.valueOf(q));
        BigInteger f = BigInteger.valueOf(p-1).multiply(BigInteger.valueOf(q-1));
        e = new Eratosthenes(10000);
        e.run();
        int E = e.getE(f);
        System.out.println(E + " " +n);
        GFG gfg = new GFG();
       // System.out.println(gfg.modInverse(BigInteger.valueOf(E), f));
        BigInteger d = gfg.modInverse(BigInteger.valueOf(E), f);
        System.out.println(d + " " + n);
        String line = "Hello";
        String tmp = "";
        System.out.println("Закодированное число.");
        List<BigInteger> rsa = new ArrayList<>();
        for (int i = 0; i < line.length(); i++)
        {
            String text = "";
            text = text + (int)line.charAt(i);
            while (text.length() < 4)
            {
            text = 0 + text;
            }
            tmp = tmp + text;
            if (i%3 == 2 || i == line.length()-1)
            {
                BigInteger X = BigInteger.valueOf(Long.parseLong(tmp)).modPow(BigInteger.valueOf(E), n);
                rsa.add(X);
                System.out.println(X);
                tmp = "";
            }
        }
        for (int i = 0; i < rsa.size(); i++)
        {
            BigInteger X = rsa.get(i);
            BigInteger M = X.modPow(d, n);
            String text;
            text = M.toString();
            while (text.length()%4 != 0)
            {
                text = "0" + text;
            }
            String s = "";
            for (int j = 0; j < text.length(); j++)
            {
                s = s + text.charAt(j);
                if (j%4 == 3)
                {
                    System.out.print((char)Integer.parseInt(s));
                    s = "";
                }
            }
        }

    }
    static long generate()
    {
        boolean flag = false;
        long a =0;
        while (!flag){
            Random r = new Random(System.currentTimeMillis());
            a = Math.round(r.nextDouble() * (Math.pow(2, b) - Math.pow(2, b-1)) + Math.pow(2, b-1));
            if (a%2 == 0)
            {
                a++;
            }
            if (e.check(a))
            {
                flag = true;
            }
            if (flag)
            {
                for (int i = 0; i < countTest; i++)
                {
                    if (!new testRabinMiller(a).run())
                    {
                        flag = false;
                        break;
                    }
                }
            }

        }
        return a;
    }
}
