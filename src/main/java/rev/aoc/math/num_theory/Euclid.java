package rev.aoc.math.num_theory;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Euclid
{
    /**
     * Returns the steps of Euclid's algorithm as a list of arrays of the form [a,b,q,r]
     *
     * If the input is invalid, then an empty result is returned.
     */
    public static List<BigInteger[]> euclidsAlgorithm(BigInteger a, BigInteger b) {
        if (b.compareTo(a) == 1){
            return euclidsAlgorithm(b,a);
        }

        if (a.compareTo(BigInteger.ZERO) <=0 || b.compareTo(BigInteger.ZERO)<=0) {
            return new ArrayList<>();
        }

        List<BigInteger[]> retval = new ArrayList<>();
        do {
            BigInteger r = a.mod(b);
            BigInteger q = a.divide(b);
            retval.add(new BigInteger[]{a,b,q,r});
            a = b;
            b = r;
        } while (b.compareTo(BigInteger.ZERO) != 0);
        return retval;
    }

}
