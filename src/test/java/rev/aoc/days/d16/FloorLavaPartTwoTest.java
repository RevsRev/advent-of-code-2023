package rev.aoc.days.d16;

import java.util.List;
import rev.aoc.AocSolution;
import rev.aoc.AocTest;

public class FloorLavaPartTwoTest extends AocTest<Long> {
  @Override
  protected Long getExpected() {
    return 51l;
  }

  @Override
  protected AocSolution<Long> getSolution() {
    return new FloorLavaPartTwo(List.of("16.1-floor-lava-test"));
  }
}
