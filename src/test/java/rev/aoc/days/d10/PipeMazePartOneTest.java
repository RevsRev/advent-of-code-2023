package rev.aoc.days.d10;

import java.util.List;
import rev.aoc.AocSolution;
import rev.aoc.AocTest;

public class PipeMazePartOneTest extends AocTest<Long> {
  @Override
  protected Long getExpected() {
    return 8l;
  }

  @Override
  protected AocSolution<Long> getSolution() {
    return new PipeMazePartOne(List.of("10.1-pipe-maze-test"));
  }
}
