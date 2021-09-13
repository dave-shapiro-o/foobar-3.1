import java.math.BigInteger;

public class Solution {

    static BigInteger X;
    static BigInteger Y;

    public static String solution(String x, String y) {
        X = new BigInteger(x);
        Y = new BigInteger(y);
        BigInteger count = new BigInteger("0");

        if (isImpossible()) {
            return "impossible";
        }
        if (X.equals(BigInteger.ONE)) {
            return Y.subtract(BigInteger.ONE).toString();
        }
        if (Y.equals(BigInteger.ONE)) {
            return X.subtract(BigInteger.ONE).toString();
        }

        BigInteger quotientXY = X.divide(Y);
        BigInteger quotientYX = Y.divide(X);

        // Optimisation: if one number is much larger,
        // increase the count by the quotient of the 2 numbers
        // subtract (from the larger number) the smaller number * the quotient

        if (quotientXY.min(BigInteger.valueOf(10)).equals(BigInteger.valueOf(10))) {
                count = count.add(quotientXY);
                X = X.mod(Y);
        }
        else if (quotientYX.min(BigInteger.valueOf(10)).equals(BigInteger.valueOf(10))) {
                count = count.add(quotientYX);
                Y = Y.mod(X);
        }

        while (true) {
            if (X.equals(BigInteger.ONE) && Y.equals(BigInteger.ONE)) {
                return count.toString();
            }
            if (isImpossible()) {
                return "impossible";
            }
            if (X.max(Y).equals(X)) {
                X = X.subtract(Y);
            } else {
                Y = Y.subtract(X);
            }
            count = count.add(BigInteger.ONE);
        }
    }

    private static boolean isImpossible() {
        return (X.min(BigInteger.ZERO).equals(X) || Y.min(BigInteger.ZERO).equals(Y))
                || X.equals(Y);
    }

    public static void main(String[] args) {
        String answer = Solution.solution("99999999999", "100000000000000000000000000000000000001");
        System.out.println(answer);
    }
}