package rev.aoc.days.d19;

import org.apache.commons.lang3.tuple.Pair;
import org.checkerframework.checker.units.qual.A;
import rev.aoc.AocSolution;

import java.util.ArrayList;
import java.util.List;

public class Aplenty extends AocSolution<Long> {
  public Aplenty(Iterable<String> resources) {
    super(resources);
  }

  @Override
  protected Long solveImpl() throws Exception {
    List<String> lines = getOneAndOnlyResourceLines();
    Pair<List<String>,List<String>> workFlowsAndRatings = parse(lines);
    AplentyEngine engine = new AplentyEngine(workFlowsAndRatings.getLeft());
    List<String> ratings = workFlowsAndRatings.getRight();

    long result = 0;
    for (int i=0; i<ratings.size(); i++) {
      int[] rating = AplentyEngine.parseRating(ratings.get(i));
      if (engine.apply(rating)) {
        for (int j=0; j<rating.length;j++) {
          result += rating[j];
        }
      }
    }
    return result;
  }

  private Pair<List<String>,List<String>> parse(List<String> lines)
  {
    List<String> workflows = new ArrayList<>();
    int lineIndex = 0;
    while (!lines.get(lineIndex).isBlank()) {
      workflows.add(lines.get(lineIndex));
      lineIndex++;
    }

    while (lines.get(lineIndex).isBlank()) {
      lineIndex++;
    }

    List<String> ratings = new ArrayList<>();
    for (int i=lineIndex; i<lines.size(); i++) {
      ratings.add(lines.get(i));
    }
    return Pair.of(workflows,ratings);
  }
}
