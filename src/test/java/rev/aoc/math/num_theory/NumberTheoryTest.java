package rev.aoc.math.num_theory;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;

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

    @ParameterizedTest
    @MethodSource("getChineseRemaindersTestCases")
    public void testChineseRemainders(Pair<Map<Long,Long>,long[]> testCase) {
        Optional<long[]> result = NumberTheory.solveChineseRemainders(testCase.getLeft());
        Assertions.assertArrayEquals(testCase.getRight(), result.get());
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
        testCases.add(new long[]{3,5,4,8,-4,-5,3});
        testCases.add(new long[]{5,-3,4,-4,-8,3,-5});
        testCases.add(new long[]{-3,5,4,-8,-4,-5,3});
        return testCases;
    }

    public static List<Pair<Map<Long,Long>,long[]>> getChineseRemaindersTestCases() {
        List<Pair<Map<Long,Long>,long[]>> testCases = new ArrayList<>();

        Map<Long,Long> congruences = new HashMap<>();
        congruences.put(5l,2l);
        congruences.put(7l,3l);
        congruences.put(11l,10l);
        long[] solution = new long[2];
        solution[0] = 87;
        solution[1] = 385;

        testCases.add(Pair.of(congruences, solution));

        return testCases;
    }
}
