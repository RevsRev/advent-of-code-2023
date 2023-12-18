package rev.aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public abstract class AocTest<R> {
  protected abstract R getExpected();

  protected abstract AocSolution<R> getSolution();

  @Test
  public void test() {
    try {
      R actual = getSolution().solve();
      Assertions.assertEquals(getExpected(), actual);
    } catch (Exception e) {
      Assertions.fail(e);
    }
  }
}
