import java.math.BigInteger;

public class Solution {

    static BigInteger X;
    static BigInteger Y;
    static BigInteger count;

    public static String solution(String x, String y) {
        X = new BigInteger(x);
        Y = new BigInteger(y);
        count = BigInteger.ZERO;
        if (isImpossible()) {
            return "impossible";
        }
        if (isEitherDigitOne()) {
            return X.equals(BigInteger.ONE) ? solveIfXOrYIsOne(Y) : solveIfXOrYIsOne(X);
        }

        BigInteger quotientXY = X.divide(Y);
        BigInteger quotientYX = Y.divide(X);

        // Optimisation: if one number is much larger,
        // increase the count by the quotient of the 2 numbers
        // subtract (from the larger number) the smaller number * the quotient
        // (i.e. let the larger number = the remainder (modulus))

        if (quotientXY.min(BigInteger.valueOf(2)).equals(BigInteger.valueOf(2))) {
            count = count.add(quotientXY);
            X = X.mod(Y);
        }

        else if (quotientYX.min(BigInteger.valueOf(2)).equals(BigInteger.valueOf(2))) {
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

            if (isEitherDigitOne()) {
                return X.equals(BigInteger.ONE) ? solveIfXOrYIsOne(Y) : solveIfXOrYIsOne(X);
            }
        }
    }

    private static String solveIfXOrYIsOne(BigInteger notOne) {
        return notOne.subtract(BigInteger.ONE).add(count).toString();
    }

    private static boolean isEitherDigitOne() {
        return X.equals(BigInteger.ONE) || Y.equals(BigInteger.ONE);
    }

    private static boolean isImpossible() {
        return X.equals(BigInteger.ZERO)
                || Y.equals(BigInteger.ZERO)
                || X.equals(Y)
                || ((X.mod(Y).equals(BigInteger.ZERO)
                    || Y.mod(X).equals(BigInteger.ZERO))
                    && !isEitherDigitOne());
    }

    public static void main(String[] args) {
        String answer = Solution.solution("900000000000000000000000000000000000", "107");
        System.out.println(answer);
    }
}