package rev.aoc.math.num_theory;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class NumberTheory
{
    /**
     * Computes the gcd of longs a&b. If the input is invalid, then -1 is returned.
     */
    public static long gcd(long a, long b) {
        List<long[]> euclidSteps = Euclid.euclidsAlgorithm(a,b);
        return gcd(euclidSteps);
    }

    private static long gcd(List<long[]> euclidSteps) {
        if (euclidSteps.size() == 0) {
            return -1;
        }
        return euclidSteps.get(euclidSteps.size()-1)[1];
    }

    /**
     * Solves linear diophantine equations of the form a*x + b*y = c
     *
     * If the equation has no solution, an empty optional is returned.
     *
     * Else, the long[] contained in the optional is of the form [X,Y,xInc,yInc],
     * where (X,Y) is the principal solution to the equation, and xInc, yInc are the increments for the general
     * solution of the form:
     *
     *  x = X + k*xInc
     *  y = Y + k*yInc
     *
     *  For k an integer.
     */
    public static Optional<long[]> solveDiophantine(long a, long b, long c) {
        if (Math.abs(a) < Math.abs(b)) {
            return solveDiophantine(b,a,c);
        }

        boolean aNeg = a<0;
        boolean bNeg = b<0;
        Optional<long[]> result = solveDiophantinePositive(Math.abs(a), Math.abs(b), c);

        if (result.isEmpty() || (!aNeg && !bNeg)) {
            return result;
        }

        long[] solution = result.get();
        if (aNeg) {
            solution[0] = -solution[0];
        }
        if (bNeg) {
            solution[1] = -solution[1];
        }
        return Optional.of(solution);
    }

    /**
     * Solves diophantine equation with a,b both positive
     */
    private static Optional<long[]> solveDiophantinePositive(long a, long b, long c) {
        if (a<b) {
            return solveDiophantinePositive(b,a,c);
        }

        List<long[]> euclidSteps = Euclid.euclidsAlgorithm(a,b);
        long gcdab = gcd(euclidSteps);
        if (c % gcdab != 0) {
            return Optional.empty();
        }

        long factor = c/gcdab;

        //case a = kb for some k, gcd(a,b) = b
        if (euclidSteps.size() == 1) {
            long x = 0;
            long y = a/b * factor;
            long xInc = factor;
            long yInc = a/b * factor;
            return Optional.of(new long[]{a,b,xInc,yInc});
        }

        long[] steps = euclidSteps.get(euclidSteps.size()-2);

        // a_{k-1} = b_{k} - q_{k-2}a_{k}
        // b_{k-1} = a_{k}

        // Initially, a_{n} = -q_{n-1}, b_n = 1
        long ak = -steps[2];
        long bk = 1;

        for (int i=euclidSteps.size()-3; i>=0; i--) {
            long[] step = euclidSteps.get(i);
            long akk = ak;
            ak = bk - step[2]*ak;
            bk = akk;
        }

        //principal solution is final values ak & bk : d = ak*a + bk*b
        long x = bk * factor;
        long y = ak * factor;
        long xInc = b/gcdab;
        long yInc = -a/gcdab;

        return Optional.of(new long[]{x,y,xInc,yInc});
    }

    /**
     * Solves for x satisfying
     *
     *  x = a1 (mod b1)
     *  x = a2 (mod b2)
     *  ...
     *  x = aN (mod bN)
     *
     * @param congruences - Map of mod value to remainder (i.e. b1 --> a1, b2 --> a2, ..., bN --> aN)
     * @return
     */
    public static Optional<long[]> solveChineseRemainders(Map<Long,Long> congruences) {
        //TODO - Implement
        return null;
    }
}
