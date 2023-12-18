package rev.aoc.days.d11;

import java.util.List;
import rev.aoc.AocSolution;
import rev.aoc.AocTest;

public class CosmicExpansionPartOneTest extends AocTest<Long> {
  @Override
  protected Long getExpected() {
    return 374l;
  }

  @Override
  protected AocSolution<Long> getSolution() {
    return new CosmicExpansion(List.of("11.1-cosmic-expansion-test"), 2l);
  }
}
