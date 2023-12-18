package rev.aoc.days.d2;

import java.util.List;
import rev.aoc.AocSolution;
import rev.aoc.AocTest;

public class CubeConundrumPartTwoTest extends AocTest<Long> {
  @Override
  protected Long getExpected() {
    return (long) 2286;
  }

  @Override
  protected AocSolution<Long> getSolution() {
    return new CubeConundrumPartTwo(List.of("2.2-cube-conundrum-test"));
  }
}
