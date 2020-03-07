import java.math.BigInteger
import java.util.*
import kotlin.math.abs

fun main(args: Array<String>) {
    println("Поле безопасности:")
    //Регистрация
    BigInteger.valueOf(abs(Random(System.currentTimeMillis()).nextInt()).toLong())
        .nextProbablePrime()
    val q =
        BigInteger.valueOf(abs(Random(System.currentTimeMillis()).nextInt()) + 100.toLong())
            .nextProbablePrime()
    val N = q.multiply(BigInteger.TWO).add(BigInteger.ONE)
    val g = 2
    val k = (N.toString() + g.toString()).hashCode()
    println("q = $q, N = $N, k = $k")
    val pass = "1234"
    val salt = "salt"
    var x = (salt + pass).hashCode()
    val v = BigInteger.valueOf(g.toLong()).modPow(BigInteger.valueOf(x.toLong()), N)
    println("Регистрация:\n v = $v, s = $salt, x = $x")
    
    val login = "Alex"
    println("На клиенте: \n login = $login, password = $pass")
    println("Клиент -> Сервер:\n s = $salt, verifier = $v, username = $login")
    //Авторизация
    val a =
        BigInteger.valueOf(Random(System.currentTimeMillis()).nextInt().toLong())
    val A = BigInteger.valueOf(g.toLong()).modPow(a, N)
    println("Пользователь:")
    println("a = $a, A = $A")
    println("Пользователь -> Сервер: А = $A")
    //Сервер
    println("Сервер:")
    if (A != BigInteger.ZERO) {
        println("A!=0")
    } else {
        System.err.println("Ошибка: A = 0")
    }
    //int B = (k*v + (int)Math.pow(g, new Random(System.currentTimeMillis()).nextInt())%N)%N;
    val b =
        BigInteger.valueOf(Random(System.currentTimeMillis()).nextInt().toLong())
    var B = BigInteger.valueOf(k.toLong()).multiply(v).add(BigInteger.valueOf(g.toLong()).modPow(b, N))
    println("b = $b, B = $B")
    println("Сервер -> Пользователь: B = $B")
    B = B.mod(N)
    //Отправляет клиенту s b
//Клиент
    println("Клиент:")
    println("B!=0")
    if (B != BigInteger.ZERO) {
        println("Ок")
    } else {
        System.err.println("Ошибка")
    }

    val u = (A.toString() + B.toString()).hashCode()
    println("Клиент и сервер вычисляют u. u = $u")
    if (u == 0) {
        System.err.println("u=0")
    }
    //клиент
    x = (salt + pass).hashCode()
    //int S = (int)(Math.pow(B - k*((Math.pow(g, x)%N)), A+u*x))%N;
    val tmp = a.add(BigInteger.valueOf(u.toLong()).multiply(BigInteger.valueOf(x.toLong())))
    var S = B.subtract(
        BigInteger.valueOf(k.toLong()).multiply(
            BigInteger.valueOf(g.toLong()).modPow(
                BigInteger.valueOf(x.toLong()), N
            )
        )
    )

    S = S.modPow(tmp, N)
    val K = S.hashCode()
    println("Клиент:\nКлюч: $K")

    var S1 = A.multiply(v.modPow(BigInteger.valueOf(u.toLong()), N))
    S1 = S1.modPow(b, N)
    val K1 = S1.hashCode()
    println("Сервер:\nКлюч: $K1")

    val M =
        (Integer.toString(N.hashCode() xor Integer.toString(g).hashCode()) + Integer.toString(
            login.hashCode()
        ) + salt + A.toString() + B.toString() + Integer.toString(K)).hashCode()
    val M1 =
        (Integer.toString(N.hashCode() xor Integer.toString(g).hashCode()) + Integer.toString(
            login.hashCode()
        ) + salt + A.toString() + B.toString() + Integer.toString(K1)).hashCode()

    if (M == M1) {
        println("Клиент подтвержден.")
    } else {
        System.err.println("Ошибка")
    }

    val R1 = (A.toString() + Integer.toString(M1) + Integer.toString(K1)).hashCode()
    val R = (A.toString() + Integer.toString(M) + Integer.toString(K)).hashCode()

    if (R == R1) {
        println("Сервер подтвержден.")
    } else {
        System.err.println("Ошибка")
    }
}