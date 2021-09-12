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

        BigInteger quotientXY = X.divide(Y);
        BigInteger quotientYX = Y.divide(X);

        // Optimisation: if one number is much larger,
        // increase the count by the quotient of the 2 numbers
        // subtract (from the larger number) the smaller number * the quotient

        if ((quotientXY.min(BigInteger.ONE).equals(BigInteger.ONE))
                && !Y.equals(BigInteger.ONE)) {
            while ((quotientXY.min(BigInteger.ONE).equals(BigInteger.ONE))
                    && (!X.equals(BigInteger.ZERO) && !Y.equals(BigInteger.ZERO))) {
                count = count.add(quotientXY);
                X = X.subtract(Y.multiply(quotientXY));
                if (X.equals(BigInteger.ZERO)) {
                    return count.toString();
                }
                quotientXY = X.divide(Y);
            }
        }
        if ((quotientYX.min(BigInteger.ONE).equals(BigInteger.ONE))
                && !X.equals(BigInteger.ONE)) {
            while ((quotientYX.min(BigInteger.ONE).equals(BigInteger.ONE))
                    && (!X.equals(BigInteger.ZERO) && !Y.equals(BigInteger.ZERO))) {
                count = count.add(quotientYX);
                Y = Y.subtract(X.multiply(quotientYX));
                if (Y.equals(BigInteger.ZERO)) {
                    return count.toString();
                }
                quotientYX = Y.divide(X);
            }
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
        return (X.equals(BigInteger.ZERO) || Y.equals(BigInteger.ZERO))
                || X.equals(Y);
    }

    public static void main(String[] args) {
        String answer = Solution.solution("7", "4");
        System.out.println(answer);
    }
}