package rev.aoc.days.d19;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;
import rev.aoc.AocSolution;

public abstract class Aplenty extends AocSolution<Long> {
  public Aplenty(Iterable<String> resources) {
    super(resources);
  }

  @Override
  protected Long solveImpl() throws Exception {
    List<String> lines = getOneAndOnlyResourceLines();
    Pair<List<String>, List<String>> workFlowsAndRatings = parse(lines);
    List<String> ratings = workFlowsAndRatings.getRight();

    return solveWithAplentyEngine(workFlowsAndRatings, ratings);
  }

  protected abstract long solveWithAplentyEngine(Pair<List<String>, List<String>> workFlowsAndRatings, List<String> ratings);

  private Pair<List<String>, List<String>> parse(List<String> lines) {
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
    for (int i = lineIndex; i < lines.size(); i++) {
      ratings.add(lines.get(i));
    }
    return Pair.of(workflows, ratings);
  }
}
