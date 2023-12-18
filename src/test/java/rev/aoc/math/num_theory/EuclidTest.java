package rev.aoc.math.num_theory;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class EuclidTest
{

    @ParameterizedTest
    @MethodSource("getEuclidTestCases")
    public void testEuclid(Pair<BigInteger[], List<BigInteger[]>> testCase)
    {
        BigInteger[] input = testCase.getLeft();
        List<BigInteger[]> steps = testCase.getRight();
        List<BigInteger[]> expectedSteps = Euclid.euclidsAlgorithm(input[0], input[1]);
        for (int i = 0; i < steps.size(); i++)
        {
            Assertions.assertArrayEquals(expectedSteps.get(i), steps.get(i));
        }
    }

    public static List<Pair<BigInteger[], List<BigInteger[]>>> getEuclidTestCases()
    {
        List<Pair<BigInteger[], List<BigInteger[]>>> testCases = new ArrayList<>();

        BigInteger[] input = new BigInteger[]{BigInteger.valueOf(5), BigInteger.valueOf(3)};
        List<BigInteger[]> steps = new ArrayList<>();
        steps.add(new BigInteger[]{BigInteger.valueOf(5), BigInteger.valueOf(3), BigInteger.valueOf(1), BigInteger.valueOf(2)});
        steps.add(new BigInteger[]{BigInteger.valueOf(3), BigInteger.valueOf(2), BigInteger.valueOf(1), BigInteger.valueOf(1)});
        steps.add(new BigInteger[]{BigInteger.valueOf(2), BigInteger.valueOf(1), BigInteger.valueOf(2), BigInteger.valueOf(0)});

        testCases.add(Pair.of(input, steps));

        return testCases;
    }

}
