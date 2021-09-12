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

        if ((quotientXY.min(BigInteger.ONE).equals(BigInteger.ONE))
                && !Y.equals(BigInteger.ONE)) {
            while ((quotientXY.min(BigInteger.ONE).equals(BigInteger.ONE))
                    && (!X.equals(BigInteger.ZERO) && !Y.equals(BigInteger.ZERO))) {
                count = count.add(quotientXY);
                X = X.subtract(Y.multiply(quotientXY));
                if (isImpossible()) {
                    return "impossible";
                }
                quotientXY = X.divide(Y);
            }
        }
        else if ((quotientYX.min(BigInteger.ONE).equals(BigInteger.ONE))
                && !X.equals(BigInteger.ONE)) {
            while ((quotientYX.min(BigInteger.ONE).equals(BigInteger.ONE))
                    && (!X.equals(BigInteger.ZERO) && !Y.equals(BigInteger.ZERO))) {
                count = count.add(quotientYX);
                Y = Y.subtract(X.multiply(quotientYX));
                if (isImpossible()) {
                    return "impossible";
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
        String answer = Solution.solution("2", "999");
        System.out.println(answer);
    }
}