package rev.aoc.days.d19;

import java.util.List;

public class AplentyPartOne extends Aplenty {
  public AplentyPartOne(Iterable<String> resources) {
    super(resources);
  }

  @Override
  protected long solveWithAplentyEngine(List<String> rules, List<String> ratings) {
    AplentyEngine engine = new AplentyEngine(rules);
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
