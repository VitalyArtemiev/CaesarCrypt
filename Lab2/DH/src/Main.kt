import java.math.BigInteger

fun main() {
    // Both the persons will be agreed upon the  
    // public keys G and P  
    val P: BigInteger = 23.toBigInteger(); //  prime number
    println("The value of P : $P");

    //println(findPrimefactors(P))

    val G: BigInteger = findPrimitiveRoot(P); //  primitve root modulo n
    println("The value of G : $G");

    println("Alice chooses private key a")
    var s: String = readLine()!!
    // Alice will choose the private key a  
    val a: BigInteger = BigInteger(s); // a is the chosen private key
    println("The private key a for Alice : $a");
    val x: BigInteger = G.modPow(a, P)// gets the generated key

    println("Bob chooses private key b")
    s = readLine()!!
    // Bob will choose the private key b 
    val b: BigInteger = BigInteger(s); // b is the chosen private key
    println("The private key b for Bob : $b");
    val y: BigInteger = G.modPow(b, P)// gets the generated key

    // Generating the secret key after the exchange 
    // of keys 
    val ka: BigInteger = y.modPow(a, P) // Secret key for Alice
    val kb: BigInteger = x.modPow(b, P) // Secret key for Bob

    println("Secret key for the Alice is : $ka");
    println("Secret Key for the Bob is : $kb");
}