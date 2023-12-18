package rev.aoc.days.d12;

import org.apache.commons.lang3.tuple.Pair;

public class HotSpringsPartTwo extends HotSprings {
  public HotSpringsPartTwo(Iterable<String> resources) {
    super(resources);
  }

  @Override
  protected Pair<char[], int[]> toSchematic(char[] rowSchematic, int[] brokenCounts) {
    int copies = 5;
    char[] unfoldedRowSchematic = new char[rowSchematic.length * copies + copies - 1];
    int[] unfoldedBrokenCounts = new int[brokenCounts.length * copies];

    for (int i = 0; i < copies; i++) {
      for (int j = 0; j < rowSchematic.length; j++) {
        unfoldedRowSchematic[i * rowSchematic.length + j + i] = rowSchematic[j];
      }
      if (i > 0) {
        unfoldedRowSchematic[i * rowSchematic.length + i - 1] = '?';
      }

      for (int j = 0; j < brokenCounts.length; j++) {
        unfoldedBrokenCounts[i * brokenCounts.length + j] = brokenCounts[j];
      }
    }

    Pair<char[], int[]> springsAndBrokenCounts =
        Pair.of(unfoldedRowSchematic, unfoldedBrokenCounts);
    return springsAndBrokenCounts;
  }
}
