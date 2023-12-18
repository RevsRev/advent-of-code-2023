package rev.aoc.days.d4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rev.aoc.AocSolution;

public abstract class AocScratchCard extends AocSolution<Long> {

  public AocScratchCard(Iterable<String> resources) {
    super(resources);
  }

  @Override
  protected Long solveImpl() throws Exception {
    List<String> lines = getOneAndOnlyResourceLines();
    Map<Integer, ScratchCard> scratchCards = loadScratchCards(lines);

    return solve(scratchCards);
  }

  protected abstract long solve(Map<Integer, ScratchCard> scratchCards);

  private Map<Integer, ScratchCard> loadScratchCards(List<String> lines) {
    Map<Integer, ScratchCard> retval = new HashMap<>();
    for (int i = 0; i < lines.size(); i++) {
      ScratchCard sc = ScratchCard.fromGameLine(lines.get(i));
      retval.put(sc.getId(), sc);
    }
    return retval;
  }
}
