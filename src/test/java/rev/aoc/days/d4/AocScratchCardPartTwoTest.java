package rev.aoc.days.d4;

import java.util.List;
import rev.aoc.AocSolution;
import rev.aoc.AocTest;

public class AocScratchCardPartTwoTest extends AocTest<Long> {
  @Override
  protected Long getExpected() {
    return (long) 30;
  }

  @Override
  protected AocSolution<Long> getSolution() {
    return new AocScratchCardPartTwo(List.of("4.2-scratch-card-test"));
  }
}
