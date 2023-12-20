package rev.aoc.days.d19;

import java.util.List;
import rev.aoc.AocSolution;
import rev.aoc.AocTest;

public class AplentyPartTwoTest extends AocTest<Long> {
  @Override
  protected Long getExpected() {
    return 167409079868000l;
  }

  @Override
  protected AocSolution<Long> getSolution() {
    return new AplentyPartTwo(List.of("19.1-aplenty-test"));
  }
}
