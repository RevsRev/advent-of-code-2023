package rev.aoc.math.num_theory;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class Euclid
{
    /**
     * Returns the steps of Euclid's algorithm as a list of arrays of the form [a,b,q,r]
     *
     * If the input is invalid, then an empty result is returned.
     */
    public static List<long[]> euclidsAlgorithm(long a, long b) {
        if (b>a){
            return euclidsAlgorithm(b,a);
        }

        if (a<=0 || b<=0) {
            return new ArrayList<>();
        }

        List<long[]> retval = new ArrayList<>();
        do {
            long r = a%b;
            long q = a/b;
            retval.add(new long[]{a,b,q,r});
            a = b;
            b = r;
        } while (b != 0);
        return retval;
    }

}
