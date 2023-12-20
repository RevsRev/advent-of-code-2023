package rev.aoc.days.d19;

import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public class AplentyPartOne extends Aplenty {
  public AplentyPartOne(Iterable<String> resources) {
    super(resources);
  }

  @Override
  protected long solveWithAplentyEngine(
      Pair<List<String>, List<String>> workFlowsAndRatings, List<String> ratings) {
    AplentyEngine engine = new AplentyEngine(workFlowsAndRatings.getLeft());
    long result = 0;
    for (int i = 0; i < ratings.size(); i++) {
      int[] rating = AplentyEngine.parseRating(ratings.get(i));
      if (engine.apply(rating)) {
        for (int j = 0; j < rating.length; j++) {
          result += rating[j];
        }
      }
    }
    return result;
  }
}
