package rev.aoc.days.d9;

import java.util.List;
import rev.aoc.AocSolution;
import rev.aoc.AocTest;

public class MirageMaintenancePartOneTest extends AocTest<Long> {
  @Override
  protected Long getExpected() {
    return 114l;
  }

  @Override
  protected AocSolution<Long> getSolution() {
    return new MirageMaintenancePartOne(List.of("9.1-mirage-maintenance-test"));
  }
}
