package rev.aoc.days.d20;

import java.util.List;
import rev.aoc.AocSolution;
import rev.aoc.AocTest;

public class PulsePropagationATest extends AocTest<Long> {
  @Override
  protected Long getExpected() {
    return 32000000l;
  }

  @Override
  protected AocSolution<Long> getSolution() {
    return new PulsePropagation(List.of("20.1.A-pulse-propagation-test"));
  }
}
