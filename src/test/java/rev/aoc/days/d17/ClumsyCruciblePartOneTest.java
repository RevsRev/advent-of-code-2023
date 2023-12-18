package rev.aoc.days.d17;

import java.util.List;
import rev.aoc.AocSolution;
import rev.aoc.AocTest;

public class ClumsyCruciblePartOneTest extends AocTest<Long> {
  @Override
  protected Long getExpected() {
    return 102l;
  }

  @Override
  protected AocSolution<Long> getSolution() {
    return new ClumsyCruciblePartOne(List.of("17.1-clumsy-crucible-test"));
  }
}
