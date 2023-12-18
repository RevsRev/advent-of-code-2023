package rev.aoc.days.d6;

import java.util.List;
import rev.aoc.AocSolution;
import rev.aoc.AocTest;

public class BoatRacePartTwoTest extends AocTest<Long> {
  @Override
  protected Long getExpected() {
    return (long) 71503;
  }

  @Override
  protected AocSolution<Long> getSolution() {
    return new BoatRacePartTwo(List.of("6.2-boat-race-test"));
  }
}
