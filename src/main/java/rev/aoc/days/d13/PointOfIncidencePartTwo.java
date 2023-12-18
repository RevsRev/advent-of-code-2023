package rev.aoc.days.d13;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class PointOfIncidencePartTwo extends PointOfIncidencePartOne {
  public PointOfIncidencePartTwo(Iterable<String> resources) {
    super(resources);
  }

  @Override
  protected int getReflectionIndex(char[][] m) {
    int height = m.length;
    int width = m[0].length;

    Map<Integer, Set<Integer>> reflectionIndices = new HashMap<>();

    for (int i = 0; i < height; i++) {
      char[] row = m[i];
      Set<Integer> newReflectionIndices = getReflectionIndices(m[i]);
      Iterator<Integer> it = newReflectionIndices.iterator();
      while (it.hasNext()) {
        int index = it.next();
        reflectionIndices.computeIfAbsent(index, k -> new HashSet<>()).add(i);
      }
    }

    Set<Integer> smudgeCandidates = getSmudgeCandidates(height, reflectionIndices);

    Iterator<Integer> itSmudgeCandidates = smudgeCandidates.iterator();
    while (itSmudgeCandidates.hasNext()) {
      int reflectIndex = itSmudgeCandidates.next();
      Set<Integer> rowsWithReflection = reflectionIndices.get(reflectIndex);
      int smudgeRow = 0;
      while (smudgeRow < height) {
        if (!rowsWithReflection.contains(smudgeRow)) {
          break;
        }
        smudgeRow++;
      }
      if (canSmudge(reflectIndex, m[smudgeRow])) {
        return reflectIndex;
      }
    }
    return -1;
  }

  private boolean canSmudge(int reflectIndex, char[] m) {
    int asymCount = 0;
    int start = reflectIndex;
    int end = reflectIndex + 1;
    while (start >= 0 && end < m.length) {
      if (m[start] != m[end]) {
        asymCount++;
      }
      start--;
      end++;
    }
    return asymCount == 1;
  }

  private Set<Integer> getSmudgeCandidates(
      int height, Map<Integer, Set<Integer>> reflectionIndices) {
    Set<Integer> smudgeCandidates = new HashSet<>();
    Iterator<Integer> itRefIndex = reflectionIndices.keySet().iterator();
    while (itRefIndex.hasNext()) {
      int refIndex = itRefIndex.next();
      if (reflectionIndices.get(refIndex).size() == height - 1) {
        smudgeCandidates.add(refIndex);
      }
    }
    return smudgeCandidates;
  }
}
