package rev.aoc.days.d5;

import java.util.List;
import rev.aoc.AocSolution;
import rev.aoc.AocTest;

public class SeedAlmanacPartOneTest extends AocTest<Long> {
  @Override
  protected Long getExpected() {
    return (long) 35;
  }

  @Override
  protected AocSolution<Long> getSolution() {
    return new SeedAlmanacPartOne(List.of("5.1-seed-almanac-test"));
  }
}
