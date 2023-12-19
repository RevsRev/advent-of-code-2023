package rev.aoc.days.d18;

import java.util.List;
import rev.aoc.AocSolution;
import rev.aoc.AocTest;

public class LavaductLagoonTest extends AocTest<Long> {
  @Override
  protected Long getExpected() {
    return 52l;
  }

  @Override
  protected AocSolution<Long> getSolution() {
    return new LavaductLagoon(List.of("18.1-lavaduct-lagoon-test"));
  }
}
