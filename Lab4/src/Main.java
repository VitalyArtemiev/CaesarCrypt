import java.math.BigInteger;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        System.out.println("Поле безопасности:");
        //Регистрация
        BigInteger.valueOf(new Random(System.currentTimeMillis()).nextInt()).nextProbablePrime();
        BigInteger q = BigInteger.valueOf(new Random(System.currentTimeMillis()).nextInt()+100).nextProbablePrime();
        BigInteger N = q.multiply(BigInteger.TWO).add(BigInteger.ONE);
        int g = 2;
        int k = (N.toString()+Integer.toString(g)).hashCode();
        System.out.printf("q = %d, N = %d, k = %d\n", q, N, k);
        String p = "1234";
        String s = "salt";
        int x =  (s+p).hashCode();
        BigInteger v = BigInteger.valueOf(g).modPow(BigInteger.valueOf(x), N);
        System.out.printf("Регистрация:\n v = %d, s = %s, x = %d\n", v, s, x);
        String l = "Alex";
        System.out.printf("Клиент -> Сервер:\n s = %s, v = %d, username = %s\n", s, v, l);
        //Авторизация
        BigInteger a = BigInteger.valueOf(new Random(System.currentTimeMillis()).nextInt());
        BigInteger A = BigInteger.valueOf(g).modPow(a, N);
        System.out.println("Пользователь:");
        System.out.printf("a = %d, A = %d\n", a, A);
        System.out.println("Пользователь -> Сервер: А = " + A);
        //Сервер
        System.out.println("Сервер:");
        System.out.println("A!=0");
        if (!A.equals(BigInteger.ZERO) )
        {
            System.out.println("Ок");
        }
        else
        {
            System.err.println("Ошибка");
        }
        //int B = (k*v + (int)Math.pow(g, new Random(System.currentTimeMillis()).nextInt())%N)%N;
        BigInteger b = BigInteger.valueOf(new Random(System.currentTimeMillis()).nextInt());
        BigInteger B = BigInteger.valueOf(k).multiply(v).add(BigInteger.valueOf(g).modPow(b, N));
        System.out.printf("b = %d, B = %d\n", b, B);
        System.out.println("Сервер -> Пользователь: B = " + B);
        B = B.mod(N);
        //Отправляет клиенту s b

        //Клиент
        System.out.println("Клиент:");
        System.out.println("B!=0");
        if (!B.equals(BigInteger.ZERO))
        {
            System.out.println("Ок");
        }
        else
        {
            System.err.println("Ошибка");
        }

        int u = (A.toString()+B.toString()).hashCode();
        System.out.println("Клиент и сервер вычисляют u. u = " + u);
        if (u == 0)
        {
            System.err.println("u=0");
        }

        //клиент
        x = (s+p).hashCode();
        //int S = (int)(Math.pow(B - k*((Math.pow(g, x)%N)), A+u*x))%N;
        BigInteger tmp = a.add(BigInteger.valueOf(u).multiply(BigInteger.valueOf(x)));
        BigInteger S = B.subtract(BigInteger.valueOf(k).multiply(BigInteger.valueOf(g).modPow(BigInteger.valueOf(x), N)));
        S = S.modPow(tmp, N);
        int K = S.hashCode();
        System.out.printf("Клиент:\nКлюч: %d\n", K);

        BigInteger S1 = A.multiply(v.modPow(BigInteger.valueOf(u), N));
        S1 = S1.modPow(b, N);
        int K1 = S1.hashCode();
        System.out.printf("Сервер:\nКлюч: %d\n", K1);

        int M = (Integer.toString(N.hashCode()^Integer.toString(g).hashCode())+Integer.toString(l.hashCode())+s+A.toString()+B.toString()+Integer.toString(K)).hashCode();
        int M1 = (Integer.toString(N.hashCode()^Integer.toString(g).hashCode())+Integer.toString(l.hashCode())+s+A.toString()+B.toString()+Integer.toString(K1)).hashCode();
        if (M == M1)
        {
            System.out.println("Клиент подтвержден.");
        }
        else
        {
            System.err.println("Ошибка");
        }
        int R1 = (A.toString()+Integer.toString(M1)+Integer.toString(K1)).hashCode();
        int R  = (A.toString()+Integer.toString(M)+Integer.toString(K)).hashCode();;
        if (R == R1)
        {
            System.out.println("Сервер подтвержден.");
        }
        else
        {
            System.err.println("Ошибка");
        }
    }


}
