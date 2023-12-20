package rev.aoc.util;

import java.util.Arrays;
import java.util.Comparator;

public class ArraysUtil {

  /**
   * Merges ranges of arrays
   *
   * @param first An array of elements of type T, with the format
   *     [r1Start,r1End,r2Start,r2End,...rNStart,rNEnd] Where rI is a range, start is inclusive and
   *     end is exclusive, and the elements are already sorted in order. Note that the ranges should
   *     be non overlapping (rIEnd = rI+1Start is okay, but should be avoided).
   * @param second A second array in the same format as the first
   * @param comparator A comparator for comparing type T
   * @return A combined range, in the same format as the inputs.
   * @param <T> Type of element being sorted
   */
  public static <T> T[] unionRanges(T[] first, T[] second, Comparator<T> comparator) {
    if (first.length > second.length) {
      return unionRanges(second, first, comparator);
    }

    if (first.length == 0) {
      return second;
    }
    if (second.length == 0) {
      return first;
    }

    T[] result = (T[]) new Object[first.length + second.length];
    int resultIndex = 0;

    for (int i = 1; i < second.length; i += 2) {
      if (comparator.compare(second[i], first[0]) < 0) {
        result[resultIndex++] = second[i - 1];
        result[resultIndex++] = second[i];
      }
    }

    for (int i = 0; i < first.length; i += 2) {
      T start = first[i];
      T end = first[i + 1];
      int insertionPoint = Arrays.binarySearch(second, start, comparator);
      if (insertionPoint < 0) {
        insertionPoint = -insertionPoint - 1;
      }

      if (insertionPoint % 2 == 0) { // we are after an end and before a start
        while (insertionPoint <= second.length - 2
            && comparator.compare(end, second[insertionPoint]) >= 0) {
          end = second[insertionPoint];
          insertionPoint += 2;
        }
      } else { // we are after a start and before an end
        start = second[insertionPoint - 1];
        while (insertionPoint <= second.length - 2
            && comparator.compare(end, second[insertionPoint + 1]) >= 0) {
          end = second[insertionPoint + 1];
          insertionPoint += 2;
        }
      }
      result[resultIndex++] = start;
      result[resultIndex++] = end;
      while (i < first.length - 1 && comparator.compare(first[i], end) < 0) {
        i += 2;
      }
    }

    for (int i = 0; i < second.length; i += 2) {
      if (comparator.compare(second[i], first[first.length - 1]) >= 0) {
        result[resultIndex++] = second[i];
        result[resultIndex++] = second[i + 1];
      }
    }

    return trim(result, comparator, resultIndex);
  }

  private static <T> T[] trim(T[] toTrim, Comparator<T> comparator, int toIndex) {
    T[] result = (T[]) new Object[toIndex];
    int index = 0;
    for (int i = 0; i < toIndex - 1; i += 2) {
      T start = toTrim[i];
      T end = toTrim[i + 1];
      while (i < toIndex - 3 && comparator.compare(end, toTrim[i + 2]) == 0) {
        i += 2;
        end = toTrim[i + 1];
      }
      result[index++] = start;
      result[index++] = end;
    }
    return Arrays.copyOfRange(result, 0, index);
  }
}
