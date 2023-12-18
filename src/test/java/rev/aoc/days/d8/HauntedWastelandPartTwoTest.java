package rev.aoc.days.d8;

import java.math.BigInteger;
import java.util.List;
import rev.aoc.AocSolution;
import rev.aoc.AocTest;

public class HauntedWastelandPartTwoTest extends AocTest<BigInteger> {
  @Override
  protected BigInteger getExpected() {
    return BigInteger.valueOf(6);
  }

  @Override
  protected AocSolution<BigInteger> getSolution() {
    return new HauntedWastelandPartTwo(List.of("8.2-haunted-wasteland-test"));
  }
}
