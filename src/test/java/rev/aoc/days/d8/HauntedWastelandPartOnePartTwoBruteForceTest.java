package rev.aoc.days.d8;

import java.util.List;
import rev.aoc.AocSolution;
import rev.aoc.AocTest;

public class HauntedWastelandPartOnePartTwoBruteForceTest extends AocTest<Long> {
  @Override
  protected Long getExpected() {
    return (long) 6;
  }

  @Override
  protected AocSolution<Long> getSolution() {
    return new HauntedWastelandPartTwoBruteForce(List.of("8.1-haunted-wasteland-test"));
  }
}
