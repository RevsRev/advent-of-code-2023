package rev.aoc.days.d9;

import java.util.List;
import rev.aoc.AocSolution;
import rev.aoc.AocTest;

public class MirageMaintenancePartTwoTest extends AocTest<Long> {
  @Override
  protected Long getExpected() {
    return 2l;
  }

  @Override
  protected AocSolution<Long> getSolution() {
    return new MirageMaintenancePartTwo(List.of("9.2-mirage-maintenance-test"));
  }
}
