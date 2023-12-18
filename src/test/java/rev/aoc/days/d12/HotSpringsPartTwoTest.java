package rev.aoc.days.d12;

import java.util.List;
import rev.aoc.AocSolution;
import rev.aoc.AocTest;

public class HotSpringsPartTwoTest extends AocTest<Long> {
  @Override
  protected Long getExpected() {
    return 525152l;
  }

  @Override
  protected AocSolution<Long> getSolution() {
    return new HotSpringsPartTwo(List.of("12.1-hot-springs-test"));
  }
}
