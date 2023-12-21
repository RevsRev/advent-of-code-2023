package rev.aoc.util;

import java.lang.reflect.Array;
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
  public static <T extends Comparable<? super T>> T[] unionRanges(
      T[] first, T[] second, Comparator<? super T> comparator, Class<T> clazz) {
    if (first.length > second.length) {
      return unionRanges(second, first, comparator, clazz);
    }

    if (first.length == 0) {
      return second;
    }
    if (second.length == 0) {
      return first;
    }

    T[] result = (T[]) Array.newInstance(clazz, first.length + second.length);
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
        while (insertionPoint < second.length - 2
            && comparator.compare(end, second[insertionPoint]) >= 0) {
          end = second[insertionPoint + 2];
          insertionPoint += 2;
        }
      } else { // we are after a start and before an end
        start = second[insertionPoint - 1];
        while (insertionPoint < second.length - 1
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

    return trim(result, comparator, resultIndex, clazz);
  }

  /**
   * Intersects ranges of arrays
   *
   * @param first An array of elements of type T, with the format
   *     [r1Start,r1End,r2Start,r2End,...rNStart,rNEnd] Where rI is a range, start is inclusive and
   *     end is exclusive, and the elements are already sorted in order. Note that the ranges should
   *     be non overlapping (rIEnd = rI+1Start is okay, but should be avoided).
   * @param second A second array in the same format as the first
   * @param comparator A comparator for comparing type T
   * @return An intersected range, formed by overlapping ranges from first[] and second[]
   * @param <T> Type of element being sorted
   */
  public static <T extends Comparable<? super T>> T[] intersectRanges(
      T[] first, T[] second, Comparator<? super T> comparator, Class<T> clazz) {
    if (first.length > second.length) {
      return intersectRanges(second, first, comparator, clazz);
    }

    if (first.length == 0 || second.length == 0) {
      return (T[]) Array.newInstance(clazz, 0);
    }

    T[] result = (T[]) Array.newInstance(clazz, first.length + second.length);
    ;
    int resultIndex = 0;

    for (int i = 0; i < first.length; i += 2) {
      T start = first[i];
      T end = first[i + 1];
      int startPoint = Arrays.binarySearch(second, start, comparator);

      if (startPoint < 0) {
        startPoint = -startPoint - 1;
      }

      if (startPoint % 2 == 0) { // we are after an end and before a start
        // get all parts of second contained within this region
        while (startPoint < second.length - 1
            && comparator.compare(second[startPoint + 1], end) <= 0) {
          result[resultIndex++] = second[startPoint];
          result[resultIndex++] = second[startPoint + 1];
          startPoint += 2;
        }
        // add on the next part of second if it overlaps with this region
        if (startPoint < second.length - 1 && comparator.compare(second[startPoint], end) < 0) {
          result[resultIndex++] = second[startPoint];
          result[resultIndex++] = end;
        }
      } else { // we are after a start and before an end
        // get the part of second that overlaps with this region
        if (comparator.compare(end, second[startPoint]) <= 0) {
          result[resultIndex++] = start;
          result[resultIndex++] = end;
          startPoint += 2;
        } else {
          result[resultIndex++] = start;
          result[resultIndex++] = second[startPoint];
          startPoint += 2;
        }
        // get all parts of second contained within this region
        while (startPoint < second.length
            && comparator.compare(second[startPoint - 1], end) <= 0
            && comparator.compare(second[startPoint], end) <= 0) {
          result[resultIndex++] = second[startPoint - 1];
          result[resultIndex++] = second[startPoint];
          startPoint += 2;
        }
        // add on the overlapping end
        if (startPoint < second.length && comparator.compare(second[startPoint - 1], end) < 0) {
          result[resultIndex++] = second[startPoint - 1];
          result[resultIndex++] = end;
        }
      }
    }
    return trim(result, comparator, resultIndex, clazz);
  }

  /**
   * Returns the compliment of data in the range {start, end}
   *
   * @param data
   * @param start
   * @param end
   * @return
   * @param <T>
   */
  public static <T extends Comparable<? super T>> T[] compliment(
      T[] data, Comparator<? super T> comparator, T start, T end, Class<T> clazz) {
    if (data.length == 0) {
      T[] result = (T[]) Array.newInstance(clazz, 2);
      result[0] = start;
      result[1] = end;
      return result;
    }

    T[] result = (T[]) Array.newInstance(clazz, data.length + 2);
    int resultIndex = 0;

    int i = 1;
    while (i < data.length) {
      if (i < data.length && comparator.compare(data[i], start) < 0) {
        i += 2;
        continue;
      }

      T endElement = data[i];
      if (resultIndex == 0 && comparator.compare(data[i - 1], start) > 0) {
        result[resultIndex++] = start;
        result[resultIndex++] = data[i - 1];
      }

      if (i < data.length - 1 && comparator.compare(data[i + 1], end) < 0) {
        result[resultIndex++] = data[i];
        result[resultIndex++] = data[i + 1];
      } else if (comparator.compare(data[i], end) < 0) {
        result[resultIndex++] = data[i];
        result[resultIndex++] = end;
      }
      i += 2;
    }
    return trim(result, comparator, resultIndex, clazz);
  }

  private static <T extends Comparable<? super T>> T[] trim(
      T[] toTrim, Comparator<? super T> comparator, int toIndex, Class<T> clazz) {
    T[] result = (T[]) Array.newInstance(clazz, toIndex);
    ;
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
