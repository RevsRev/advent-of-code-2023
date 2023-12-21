package rev.aoc.days.d19;

import java.util.List;

public class AplentyPartTwo extends Aplenty {
  public AplentyPartTwo(Iterable<String> resources) {
    super(resources);
  }

  @Override
  protected long solveWithAplentyEngine(List<String> rules, List<String> ratings) {
    BetterAplentyEngine engine = new BetterAplentyEngine(rules);
    List<Integer[][]> ranges = engine.apply(BetterAplentyEngine.getInputRange());

    long result = 0;
    for (int i = 0; i < ranges.size(); i++) {
      Integer[][] acceptingRange = ranges.get(i);
      result += getPermutations(acceptingRange);
    }
    return result;
  }

  private long getPermutations(Integer[][] acceptingRange) {
    long result = 1;
    for (int i = 0; i < acceptingRange.length; i++) {
      long sum = 0;
      for (int j = 0; j < acceptingRange[i].length; j += 2) {
        sum += acceptingRange[i][j + 1] - acceptingRange[i][j];
      }
      result *= sum;
    }
    return result;
  }
}
