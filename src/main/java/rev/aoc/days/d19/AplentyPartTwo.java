package rev.aoc.days.d19;

import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public class AplentyPartTwo extends Aplenty {
  public AplentyPartTwo(Iterable<String> resources) {
    super(resources);
  }

  @Override
  protected long solveWithAplentyEngine(
      Pair<List<String>, List<String>> workFlowsAndRatings, List<String> ratings) {
    return -1;
  }
}
