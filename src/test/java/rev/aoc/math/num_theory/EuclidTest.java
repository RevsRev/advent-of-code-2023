package rev.aoc.math.num_theory;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class EuclidTest
{

    @ParameterizedTest
    @MethodSource("getEuclidTestCases")
    public void testEuclid(Pair<long[], List<long[]>> testCase) {
        long[] input = testCase.getLeft();
        List<long[]> steps = testCase.getRight();
        List<long[]> expectedSteps = Euclid.euclidsAlgorithm(input[0], input[1]);
        for (int i=0; i<steps.size(); i++) {
            Assertions.assertArrayEquals(expectedSteps.get(i), steps.get(i));
        }
    }

    public static List<Pair<long[],List<long[]>>>  getEuclidTestCases() {
        List<Pair<long[],List<long[]>>> testCases = new ArrayList<>();

        long[] input = new long[]{5,3};
        List<long[]> steps = new ArrayList<>();
        steps.add(new long[]{5,3,1,2});
        steps.add(new long[]{3,2,1,1});
        steps.add(new long[]{2,1,2,0});

        testCases.add(Pair.of(input, steps));

        return testCases;
    }

}
