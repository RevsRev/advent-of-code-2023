package rev.aoc.days.d12;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import rev.aoc.AocSolution;
import rev.aoc.days.d12.naive.HotSpringsBruteForce;

public class HotSpringsBruteVsDpTest {
  @Test
  public void testDifference() throws IOException {
    HotSpringsBruteForce brute = new HotSpringsBruteForce(List.of("12.1-hot-springs-brute-vs-dp"));
    HotSpringsPartOne dp = new HotSpringsPartOne(List.of("12.1-hot-springs-brute-vs-dp"));

    Map<String, List<String>> mLines =
        AocSolution.loadResources(List.of("12.1-hot-springs-brute-vs-dp"));
    List<String> lines = mLines.get(mLines.keySet().iterator().next());
    List<Pair<char[], int[]>> input = HotSpringsBruteForce.parse(lines);

    for (int i = 0; i < input.size(); i++) {
      long bruteVal = HotSpringsBruteForce.getNumPossibleArrangements(input.get(i));
      long dpVal = HotSprings.getNumPossibleArrangements(input.get(i));
      Assertions.assertEquals(bruteVal, dpVal, Arrays.toString(input.get(i).getLeft()));
    }
  }

  @Test
  public void testDpSpecificValue() {
    char[] chars =
        new char[] {'.', '#', '?', '.', '?', '?', '#', '?', '?', '?', '?', '?', '#', '#', '.', '#'};
    int[] lengths = new int[] {1, 2, 1, 3, 1};
    long val = HotSprings.getNumPossibleArrangements(Pair.of(chars, lengths));
    Assertions.assertEquals(3l, val);
  }
}
