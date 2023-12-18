package rev.aoc.math.num_theory;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class Factors
{
    public static LinkedHashMap<Long, Long> factorise(long n)
    {
        LinkedHashSet<Long> cachedPrimes = new LinkedHashSet<>();
        long largestCachedPrime = 2;
        LinkedHashMap<Long, Long> factors = new LinkedHashMap<>();

        long reducedN = n;

        Iterator<Long> it = cachedPrimes.iterator();
        while (it.hasNext())
        {
            long prime = it.next();
            while (reducedN % prime == 0)
            {
                if (!factors.containsKey(prime))
                {
                    factors.put(prime, 0l);
                }
                factors.put(prime, factors.get(prime) + 1);
                reducedN = reducedN / prime;
            }
            largestCachedPrime = prime;
        }

        if (largestCachedPrime == 2)
        {
            while (reducedN % 2 == 0)
            {
                if (!factors.containsKey(2l))
                {
                    factors.put(2l, 0l);
                }
                factors.put(2l, factors.get(2l) + 1);
                reducedN = reducedN / 2;
            }
            largestCachedPrime = 3;
        }

        long factorToCheck = largestCachedPrime;
        while (reducedN != 1)
        {
            while (reducedN % factorToCheck == 0)
            {
                if (!factors.containsKey(factorToCheck))
                {
                    factors.put(factorToCheck, 0l);
                }
                factors.put(factorToCheck, factors.get(factorToCheck) + 1);
                reducedN = reducedN / factorToCheck;
            }
            factorToCheck += 2;
        }

        return factors;
    }
}
