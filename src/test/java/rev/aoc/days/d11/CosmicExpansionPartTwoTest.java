package rev.aoc.days.d11;

import java.util.List;
import rev.aoc.AocSolution;
import rev.aoc.AocTest;

public class CosmicExpansionPartTwoTest extends AocTest<Long> {
  @Override
  protected Long getExpected() {
    return 8410l;
  }

  @Override
  protected AocSolution<Long> getSolution() {
    return new CosmicExpansion(List.of("11.1-cosmic-expansion-test"), 100l);
  }
}
