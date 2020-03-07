// Iterative Java program to find modular 
// inverse using extended Euclid algorithm 

//import javafx.scene.layout.BackgroundImage;

import java.math.BigInteger;

class GFG
{

    // Returns modulo inverse of a with 
    // respect to m using extended Euclid 
    // Algorithm Assumption: a and m are 
    // coprimes, i.e., gcd(a, m) = 1 
    BigInteger modInverse(BigInteger a, BigInteger m)
    {
        BigInteger m0 = m;
        BigInteger y = BigInteger.ZERO;
        BigInteger x = BigInteger.ONE;

        if (m.equals(BigInteger.ONE))
            return BigInteger.ZERO;

        while (a.compareTo(BigInteger.ONE) == 1)
        {
            // q is quotient 
            BigInteger q = a.divide(m);

            BigInteger t = m;

            // m is remainder now, process 
            // same as Euclid's algo 
            //m = a % m;
            m = a.mod(m);
            a = t;
            t = y;

            // Update x and y 
            y = x.subtract(q.multiply(y));
            x = t;
        }

        // Make x positive 
        if (x.compareTo(BigInteger.ZERO) == -1)
            x = x.add(m0);

        return x;
    }

}

/*This code is contributed by Nikita Tiwari.*/
