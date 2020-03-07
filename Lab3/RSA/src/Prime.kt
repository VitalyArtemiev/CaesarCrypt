import java.math.BigInteger
import java.math.BigInteger.*
import java.util.*

val THREE = 3.toBigInteger()

val primes = sieveOfEratosthenes(10000)

fun nextPrime(): Int {
    return primes[(0..primes.lastIndex).random()]
}

fun sieveOfEratosthenes(n: Int): List<Int> {
    val prime = BooleanArray(n + 1)
    Arrays.fill(prime, true)
    var p = 2
    while (p * p <= n) {
        if (prime[p]) {
            var i = p * 2
            while (i <= n) {
                prime[i] = false
                i += p
            }
        }
        p++
    }
    val primeNumbers: MutableList<Int> = LinkedList()
    for (i in 2..n) {
        if (prime[i]) {
            primeNumbers.add(i)
        }
    }
    return primeNumbers
}

fun getE(f: BigInteger): Int {
    var e = -1
    for (b in primes) {
        if (f.mod(valueOf(b.toLong())) != valueOf(0)) {
            e = b
        }
    }
    return e
}

fun check(a: Long): Boolean {
    for (b in primes) {
        if (a % b == 0L) {
            return false
        }
    }
    return true
}

fun findPrimefactors(n: BigInteger): HashSet<BigInteger> { // Print the number of 2s that divide n
    val s = HashSet<BigInteger> ()
    var n = n
    while (n % TWO == ZERO) {
        s.add(TWO)
        n /= TWO
    }
    // n must be odd at this point. So we can skip
// one element (Note i = i +2)
    var i = THREE
    while (i <= n*n) {
        // While i divides n, print i and divide n
        while (n % i == ZERO) {
            s.add(i)
            n /= i
        }
        i += TWO
    }
    // This condition is to handle the case when
// n is a prime number greater than 2
    if (n > TWO) {
        s.add(n)
    }

    return s
}

fun isPrime(n: BigInteger): Boolean { // Corner cases
    if (n <= ONE) {
        return false
    }
    if (n <= THREE) {
        return true
    }
    // This is checked so that we can skip
// middle five numbers in below loop
    if (n % TWO == ZERO || n % THREE == ZERO) {
        return false
    }
    var i = 5.toBigInteger()
    while (i * i <= n) {
        if (n % i == ZERO || n % (i + TWO) == ZERO) {
            return false
        }
        i += 6.toBigInteger()
    }
    return true
}

// Function to find smallest primitive root of n
fun findPrimitiveRoot(n: BigInteger): BigInteger {
    // Check if n is prime or not
    if (!isPrime(n)) {
        return -ONE
    }
    // Find value of Euler Totient function of n
    // Since n is a prime number, the value of Euler
    // Totient function is n-1 as there are n-1
    // relatively prime numbers.

    val phi = n - ONE
    // Find prime factors of phi and store in a set
    val s = findPrimefactors(phi)

    // Check for every number from 2 to phi
    var r = TWO
    while (r <= phi) { // Iterate through all prime factors of phi.
        // and check if we found a power with value 1
        var flag = false
        for (a in s) { // Check if r^((phi)/primefactors) mod n
            // is 1 or not
            if (r.modPow(phi / a, n) == BigInteger.ONE) {
                flag = true
                break
            }
        }
        // If there was no power with value 1.
        if (!flag) {
            return r
        }
        r++
    }
    // If no primitive root found
    return -ONE
}

    // Returns modulo inverse of a with
// respect to m using extended Euclid
// Algorithm Assumption: a and m are
// coprimes, i.e., gcd(a, m) = 1
fun modInverse(a: BigInteger, m: BigInteger): BigInteger {
    var a = a
    var m = m
    val m0 = m
    var y = ZERO
    var x = ONE
    if (m == ONE) return ZERO
    while (a.compareTo(ONE) == 1) { // q is quotient
        val q = a.divide(m)
        var t = m
        // m is remainder now, process
// same as Euclid's algo
//m = a % m;
        m = a.mod(m)
        a = t
        t = y
        // Update x and y
        y = x.subtract(q.multiply(y))
        x = t
    }
    // Make x positive
    if (x.compareTo(ZERO) == -1) x = x.add(m0)
    return x
}
