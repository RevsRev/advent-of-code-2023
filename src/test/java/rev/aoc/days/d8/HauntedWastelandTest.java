package rev.aoc.days.d8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HauntedWastelandTest {
  @Test
  public void atomizeTest() {
    Assertions.assertArrayEquals(
        new Character[] {'L', 'R'}, HauntedWasteland.atomize(new Character[] {'L', 'R', 'L', 'R'}));
    Assertions.assertArrayEquals(
        new Character[] {'L', 'R', 'L', 'L'},
        HauntedWasteland.atomize(new Character[] {'L', 'R', 'L', 'L'}));
  }
}
