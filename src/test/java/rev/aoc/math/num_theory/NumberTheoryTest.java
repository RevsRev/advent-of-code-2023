package rev.aoc.math.num_theory;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.util.*;

public class NumberTheoryTest
{

    @ParameterizedTest
    @MethodSource("getGcdTestCases")
    public void testGcd(List<BigInteger> testCase) {
        Assertions.assertEquals(NumberTheory.gcd(testCase.get(0), testCase.get(1)), testCase.get(2));
    }

    @ParameterizedTest
    @MethodSource("getDiophantineTestCases")
    public void testDiophantine(List<BigInteger> testCase) {
        BigInteger[] result = NumberTheory.solveDiophantine(testCase.get(0), testCase.get(1), testCase.get(2)).get();
        BigInteger[] expected = testCase.subList(3, 7).toArray(new BigInteger[4]);
        Assertions.assertArrayEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("getChineseRemaindersTestCases")
    public void testChineseRemainders(Pair<Map<BigInteger,BigInteger>,BigInteger[]> testCase) {
        Optional<BigInteger[]> result = NumberTheory.solveChineseRemainders(testCase.getLeft());
        Assertions.assertArrayEquals(testCase.getRight(), result.get());
    }

    public static List<List<BigInteger>> getGcdTestCases()
    {
        List<List<BigInteger>> testCases = new ArrayList<>();
        testCases.add(List.of(BigInteger.valueOf(1),BigInteger.valueOf(1),BigInteger.valueOf(1)));
        testCases.add(List.of(BigInteger.valueOf(1),BigInteger.valueOf(6),BigInteger.valueOf(1)));
        testCases.add(List.of(BigInteger.valueOf(6),BigInteger.valueOf(12),BigInteger.valueOf(6)));
        testCases.add(List.of(BigInteger.valueOf(6),BigInteger.valueOf(9),BigInteger.valueOf(3)));
        testCases.add(List.of(BigInteger.valueOf(20),BigInteger.valueOf(30),BigInteger.valueOf(10)));
        testCases.add(List.of(BigInteger.valueOf(22),BigInteger.valueOf(77),BigInteger.valueOf(11)));
        return testCases;
    }

    public static List<List<BigInteger>> getDiophantineTestCases() {
        List<List<BigInteger>> testCases = new ArrayList<>();
        testCases.add(List.of(BigInteger.valueOf(5),BigInteger.valueOf(3),BigInteger.valueOf(4),BigInteger.valueOf(-4),BigInteger.valueOf(8),BigInteger.valueOf(3),BigInteger.valueOf(-5)));
        testCases.add(List.of(BigInteger.valueOf(3),BigInteger.valueOf(5),BigInteger.valueOf(4),BigInteger.valueOf(8),BigInteger.valueOf(-4),BigInteger.valueOf(-5),BigInteger.valueOf(3)));
        testCases.add(List.of(BigInteger.valueOf(5),BigInteger.valueOf(-3),BigInteger.valueOf(4),BigInteger.valueOf(-4),BigInteger.valueOf(-8),BigInteger.valueOf(3),BigInteger.valueOf(-5)));
        testCases.add(List.of(BigInteger.valueOf(-3),BigInteger.valueOf(5),BigInteger.valueOf(4),BigInteger.valueOf(-8),BigInteger.valueOf(-4),BigInteger.valueOf(-5),BigInteger.valueOf(3)));
        return testCases;
    }

    public static List<Pair<Map<BigInteger,BigInteger>,BigInteger[]>> getChineseRemaindersTestCases() {
        List<Pair<Map<BigInteger,BigInteger>,BigInteger[]>> testCases = new ArrayList<>();

        Map<BigInteger,BigInteger> congruences = new HashMap<>();
        congruences.put(BigInteger.valueOf(5),BigInteger.valueOf(2));
        congruences.put(BigInteger.valueOf(7),BigInteger.valueOf(3));
        congruences.put(BigInteger.valueOf(11),BigInteger.valueOf(10));
        BigInteger[] solution = new BigInteger[2];
        solution[0] = BigInteger.valueOf(87);
        solution[1] = BigInteger.valueOf(385);

        testCases.add(Pair.of(congruences, solution));

        congruences = new HashMap<>();
        solution = new BigInteger[2];
        solution[0] = BigInteger.valueOf(262);
        solution[1] = BigInteger.valueOf(18023);
        congruences.put(BigInteger.valueOf(269), BigInteger.valueOf(262));
        congruences.put(BigInteger.valueOf(67), BigInteger.valueOf(61));
        testCases.add(Pair.of(congruences, solution));

        return testCases;
    }
}
