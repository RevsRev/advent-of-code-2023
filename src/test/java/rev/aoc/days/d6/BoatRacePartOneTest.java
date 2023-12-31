package rev.aoc.days.d6;

import java.util.List;
import rev.aoc.AocSolution;
import rev.aoc.AocTest;

public class BoatRacePartOneTest extends AocTest<Long> {
  @Override
  protected Long getExpected() {
    return (long) 288;
  }

  @Override
  protected AocSolution<Long> getSolution() {
    return new BoatRacePartOne(List.of("6.1-boat-race-test"));
  }
}
