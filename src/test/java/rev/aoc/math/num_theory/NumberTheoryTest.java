package rev.aoc.math.num_theory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class NumberTheoryTest
{

    @ParameterizedTest
    @MethodSource("getGcdTestCases")
    public void testGcd(long[] testCase) {
        Assertions.assertEquals(NumberTheory.gcd(testCase[0], testCase[1]), testCase[2]);
    }

    @ParameterizedTest
    @MethodSource("getDiophantineTestCases")
    public void testDiophantine(long[] testCase) {
        long[] result = NumberTheory.solveDiophantine(testCase[0], testCase[1], testCase[2]).get();
        long[] expected = Arrays.copyOfRange(testCase, 3, 7);
        Assertions.assertArrayEquals(expected, result);
    }

    public static List<long[]> getGcdTestCases()
    {
        List<long[]> testCases = new ArrayList<>();
        testCases.add(new long[]{1,1,1});
        testCases.add(new long[]{1,6,1});
        testCases.add(new long[]{6,12,6});
        testCases.add(new long[]{6,9,3});
        testCases.add(new long[]{20,30,10});
        testCases.add(new long[]{22,77,11});
        return testCases;
    }

    public static List<long[]> getDiophantineTestCases() {
        List<long[]> testCases = new ArrayList<>();
        testCases.add(new long[]{5,3,4,-4,8,3,-5});
        return testCases;
    }
}
