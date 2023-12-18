package rev.aoc.days.d4;

import java.util.List;
import rev.aoc.AocSolution;
import rev.aoc.AocTest;

public class AocScratchCardPartOneTest extends AocTest<Long> {
  @Override
  protected Long getExpected() {
    return (long) 13;
  }

  @Override
  protected AocSolution<Long> getSolution() {
    return new AocScratchCardPartOne(List.of("4.1-scratch-card-test"));
  }
}
