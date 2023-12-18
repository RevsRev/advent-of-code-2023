package rev.aoc.days.d5;

import java.util.List;
import rev.aoc.AocSolution;
import rev.aoc.AocTest;

public class SeedAlmanacPartTwoTest extends AocTest<Long> {
  @Override
  protected Long getExpected() {
    return (long) 46;
  }

  @Override
  protected AocSolution<Long> getSolution() {
    return new SeedAlmanacPartTwo(List.of("5.2-seed-almanac-test"));
  }
}
