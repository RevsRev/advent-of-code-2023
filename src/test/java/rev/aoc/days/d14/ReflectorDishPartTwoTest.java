package rev.aoc.days.d14;

import java.util.List;
import rev.aoc.AocSolution;
import rev.aoc.AocTest;

public class ReflectorDishPartTwoTest extends AocTest<Long> {
  @Override
  protected Long getExpected() {
    return 64l;
  }

  @Override
  protected AocSolution<Long> getSolution() {
    return new ReflectorDishPartTwo(List.of("14.1-reflector-dish-test"));
  }
}
