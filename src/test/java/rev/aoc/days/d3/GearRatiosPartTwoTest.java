package rev.aoc.days.d3;

import java.util.List;
import rev.aoc.AocSolution;
import rev.aoc.AocTest;

public class GearRatiosPartTwoTest extends AocTest<Long> {
  @Override
  protected Long getExpected() {
    return (long) 467835;
  }

  @Override
  protected AocSolution<Long> getSolution() {
    return new GearRatiosPartTwo(List.of("3.2-gear-ratios-test"));
  }
}
