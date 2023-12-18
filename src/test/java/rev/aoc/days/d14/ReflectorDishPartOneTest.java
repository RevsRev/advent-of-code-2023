package rev.aoc.days.d14;

import java.util.List;
import rev.aoc.AocSolution;
import rev.aoc.AocTest;

public class ReflectorDishPartOneTest extends AocTest<Long> {
  @Override
  protected Long getExpected() {
    return 136l;
  }

  @Override
  protected AocSolution<Long> getSolution() {
    return new ReflectorDishPartOne(List.of("14.1-reflector-dish-test"));
  }
}
