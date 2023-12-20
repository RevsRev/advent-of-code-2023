package rev.aoc.days.d19;

import java.util.List;
import rev.aoc.AocSolution;
import rev.aoc.AocTest;

public class AplentyPartOneTest extends AocTest<Long> {
  @Override
  protected Long getExpected() {
    return 19114l;
  }

  @Override
  protected AocSolution<Long> getSolution() {
    return new AplentyPartOne(List.of("19.1-aplenty-test"));
  }
}
