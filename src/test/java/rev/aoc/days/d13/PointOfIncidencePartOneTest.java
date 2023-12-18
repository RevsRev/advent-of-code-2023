package rev.aoc.days.d13;

import java.util.List;
import rev.aoc.AocSolution;
import rev.aoc.AocTest;

public class PointOfIncidencePartOneTest extends AocTest<Long> {
  @Override
  protected Long getExpected() {
    return 405l;
  }

  @Override
  protected AocSolution<Long> getSolution() {
    return new PointOfIncidencePartOne(List.of("13.1-point-of-incidence-test"));
  }
}
