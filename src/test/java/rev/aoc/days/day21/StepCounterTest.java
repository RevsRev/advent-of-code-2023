package rev.aoc.days.day21;

import java.util.List;
import rev.aoc.AocSolution;
import rev.aoc.AocTest;

public class StepCounterTest extends AocTest<Long> {
  @Override
  protected Long getExpected() {
    return 16l;
  }

  @Override
  protected AocSolution<Long> getSolution() {
    return new StepCounter(List.of("21.1-step-counter-test"), 6);
  }
}
