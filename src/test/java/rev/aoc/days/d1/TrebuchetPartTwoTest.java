package rev.aoc.days.d1;

import java.util.List;
import rev.aoc.AocSolution;
import rev.aoc.AocTest;

public class TrebuchetPartTwoTest extends AocTest<Long> {

  @Override
  protected Long getExpected() {
    return (long) 281;
  }

  @Override
  protected AocSolution getSolution() {
    return new TrebuchetPartTwo(List.of("1.2-trebuchet-test"));
  }
}
