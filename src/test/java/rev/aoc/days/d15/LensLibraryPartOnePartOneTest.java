package rev.aoc.days.d15;

import java.util.List;
import rev.aoc.AocSolution;
import rev.aoc.AocTest;

public class LensLibraryPartOnePartOneTest extends AocTest<Long> {
  @Override
  protected Long getExpected() {
    return 1320l;
  }

  @Override
  protected AocSolution<Long> getSolution() {
    return new LensLibraryPartOne(List.of("15.1-lens-library-test"));
  }
}
