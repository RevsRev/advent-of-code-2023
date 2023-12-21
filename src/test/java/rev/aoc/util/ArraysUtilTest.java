package rev.aoc.util;

import java.util.Comparator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArraysUtilTest {

  @Test
  public void testUnionRanges() {
    testUnionRanges(
        new Integer[] {1, 2, 3, 4, 5, 6},
        new Integer[] {1, 2, 5, 6},
        new Integer[] {3, 4},
        Comparator.naturalOrder(),
        Integer.class);
    testUnionRanges(
        new Integer[] {1, 6},
        new Integer[] {1, 3, 4, 6},
        new Integer[] {3, 4},
        Comparator.naturalOrder(),
        Integer.class);
    testUnionRanges(
        new Integer[] {1, 7, 8, 10},
        new Integer[] {1, 5, 8, 10},
        new Integer[] {3, 7},
        Comparator.naturalOrder(),
        Integer.class);
    testUnionRanges(
        new Integer[] {1, 5, 8, 12},
        new Integer[] {1, 5, 8, 10},
        new Integer[] {10, 12},
        Comparator.naturalOrder(),
        Integer.class);
  }

  private <T extends Comparable<? super T>> void testUnionRanges(
      T[] expected, T[] a, T[] b, Comparator<T> comparator, Class<T> clazz) {
    Assertions.assertArrayEquals(expected, ArraysUtil.unionRanges(a, b, comparator, clazz));
  }

  @Test
  public void testIntersectRanges() {
    testIntersectRanges(
        new Integer[] {2, 4},
        new Integer[] {1, 4},
        new Integer[] {2, 6},
        Comparator.naturalOrder(),
        Integer.class);
    testIntersectRanges(
        new Integer[] {2, 3, 7, 9},
        new Integer[] {2, 9},
        new Integer[] {1, 3, 7, 10},
        Comparator.naturalOrder(),
        Integer.class);
    testIntersectRanges(
        new Integer[] {2, 3, 7, 9, 15, 17, 19, 23},
        new Integer[] {2, 9, 15, 17, 19, 28},
        new Integer[] {1, 3, 7, 23},
        Comparator.naturalOrder(),
        Integer.class);
  }

  private <T extends Comparable<? super T>> void testIntersectRanges(
      T[] expected, T[] a, T[] b, Comparator<T> comparator, Class<T> clazz) {
    Assertions.assertArrayEquals(expected, ArraysUtil.intersectRanges(a, b, comparator, clazz));
  }

  @Test
  public void testCompliment() {
    testCompliment(
        new Integer[] {0, 2, 7, 10},
        new Integer[] {2, 7},
        Comparator.naturalOrder(),
        0,
        10,
        Integer.class);
    testCompliment(
        new Integer[] {0, 2}, new Integer[] {2, 7}, Comparator.naturalOrder(), 0, 5, Integer.class);
    testCompliment(
        new Integer[] {0, 2, 7, 13, 25, 27, 28, 30},
        new Integer[] {2, 7, 13, 25, 27, 28},
        Comparator.naturalOrder(),
        0,
        30,
        Integer.class);
  }

  private <T extends Comparable<? super T>> void testCompliment(
      T[] expected, T[] data, Comparator<T> comparator, T start, T end, Class<T> clazz) {
    Assertions.assertArrayEquals(
        expected, ArraysUtil.compliment(data, comparator, start, end, clazz));
  }
}
