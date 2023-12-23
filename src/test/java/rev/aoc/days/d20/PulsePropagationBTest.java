package rev.aoc.days.d20;

import java.util.List;
import rev.aoc.AocSolution;
import rev.aoc.AocTest;

public class PulsePropagationBTest extends AocTest<Long> {
  @Override
  protected Long getExpected() {
    return 11687500l;
  }

  @Override
  protected AocSolution<Long> getSolution() {
    return new PulsePropagation(List.of("20.1.B-pulse-propagation-test"));
  }
}
