import java.math.BigInteger
import java.util.*

fun main(args: Array<String>) {
    val p = generate()
    val q = generate()
    println("p = $p q = $q")
    val n = BigInteger.valueOf(p).multiply(BigInteger.valueOf(q))
    val f = BigInteger.valueOf(p - 1).multiply(BigInteger.valueOf(q - 1))

    val E: Int = getE(f)
    println("Public key E = $E n = $n")
    // System.out.println(gfg.modInverse(BigInteger.valueOf(E), f));
    val d = modInverse(BigInteger.valueOf(E.toLong()), f)
    println("Private key d = $d n = $n")
    println("Input message:")

    val line = readLine()!!
    var tmp = ""
    println("Encrypted:")
    val rsa: MutableList<BigInteger> = ArrayList()
    for (i in line.indices) {
        var text = ""
        text += line[i].toInt()
        while (text.length < 4) {
            text = "0$text"
        }
        tmp += text
        if (i % 3 == 2 || i == line.length - 1) {
            val X = BigInteger.valueOf(tmp.toLong()).modPow(BigInteger.valueOf(E.toLong()), n)
            rsa.add(X)
            println(X)
            tmp = ""
        }
    }

    var result: String = ""
    for (i in rsa.indices) {
        val X = rsa[i]
        val M = X.modPow(d, n)
        var text: String
        text = M.toString()
        while (text.length % 4 != 0) {
            text = "0$text"
        }
        var s = ""
        for (j in text.indices) {
            s += text[j]
            if (j % 4 == 3) {
                result += s.toInt().toChar()
                //print(s.toInt().toChar())
                s = ""
            }
        }
    }

    println("Decyphered: $result")
}

var bitLength = 20
var countTest = 5

fun generate(): Long {
    var flag = false
    var a: Long = 0
    while (!flag) {
        val r = Random(System.currentTimeMillis())
        a = Math.round(
            r.nextDouble() * (Math.pow(2.0, bitLength.toDouble()) - Math.pow(2.0, bitLength - 1.toDouble()))
                    + Math.pow(2.0, bitLength - 1.toDouble())
        )
        if (a % 2 == 0L) {
            a++
        }
        if (check(a)) {
            flag = true
        }
        if (flag) {
            for (i in 0 until countTest) {
                if (!isPrime(a.toBigInteger())) {
                    flag = false
                    break
                }
            }
        }
    }
    return a
}
