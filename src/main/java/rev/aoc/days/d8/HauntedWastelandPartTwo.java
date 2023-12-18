package rev.aoc.days.d8;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.lang3.tuple.Pair;
import rev.aoc.AocSolution;
import rev.aoc.math.num_theory.Factors;
import rev.aoc.math.num_theory.NumberTheory;

public class HauntedWastelandPartTwo extends AocSolution<BigInteger> {
  public HauntedWastelandPartTwo(Iterable<String> resources) {
    super(resources);
  }

  @Override
  protected BigInteger solveImpl() throws Exception {
    List<String> lines = getOneAndOnlyResourceLines();
    Pair<char[], Graph> stepsAndGraph = HauntedWasteland.parse(lines);
    char[] steps = stepsAndGraph.getLeft();
    Graph g = stepsAndGraph.getRight();
    List<GraphNode> starts = g.getAllMatching(s -> s.endsWith("A"));

    List<Pair<GraphNode, Pair<Long, Long>>> cycles = new ArrayList<>();
    for (int i = 0; i < starts.size(); i++) {
      cycles.add(g.extractCycle(steps, starts.get(i)));
    }

    // pick the cycle that starts at the highest value, all other cycles are then considered to
    // start at this value.
    Pair<GraphNode, Pair<Long, Long>> highestStartingCycle = cycles.get(0);
    for (int i = 1; i < cycles.size(); i++) {
      Pair<GraphNode, Pair<Long, Long>> cycle = cycles.get(i);
      if (cycle.getRight().getLeft() > highestStartingCycle.getRight().getLeft()) {
        highestStartingCycle = cycle;
      }
    }

    long cycleStart = highestStartingCycle.getRight().getLeft();
    List<Pair<GraphNode, Pair<Long, Long>>> cyclesWithSameStart = new ArrayList<>();
    for (int i = 0; i < cycles.size(); i++) {
      Pair<GraphNode, Pair<Long, Long>> cycle = cycles.get(i);
      long oldCycleStart = cycle.getRight().getLeft();
      GraphNode node = cycle.getLeft();
      for (long j = oldCycleStart; j < cycleStart; j++) {
        if (steps[(int) j % steps.length] == 'L') {
          node = node.getLeft();
        } else {
          node = node.getRight();
        }
      }
      Pair<Long, Long> cycleInfo = Pair.of(cycleStart, cycle.getRight().getRight());
      cyclesWithSameStart.add(Pair.of(node, cycleInfo));
    }

    cycles = cyclesWithSameStart;
    Map<Long, Long> cycleLengthAndZOffset = new HashMap<>();
    // For each cycle, determine the offset of the end element
    for (int i = 0; i < cycles.size(); i++) {
      GraphNode start = cycles.get(i).getLeft();
      GraphNode node = start;
      long cycleLength = cycles.get(i).getRight().getRight();
      for (long j = 0; j < cycleLength; j++) {
        if (node.getName().endsWith("Z")) {
          cycleLengthAndZOffset.put(cycleLength, j);
          break;
        }
        if (steps[(int) (cycleStart + j) % steps.length] == 'L') {
          node = node.getLeft();
        } else {
          node = node.getRight();
        }
      }
    }

    Iterator<Long> it = cycleLengthAndZOffset.keySet().iterator();
    Map<BigInteger, BigInteger> chineseRemainderTheoremValues = new HashMap<>();
    while (it.hasNext()) {
      long len = it.next();
      long zValue = cycleLengthAndZOffset.get(len);
      Map<Long, Long> factors = Factors.factorise(len);

      Iterator<Long> factorsIt = factors.keySet().iterator();
      while (factorsIt.hasNext()) {
        long prime = factorsIt.next();
        long pow = factors.get(prime);
        long primeToPow = 1;
        for (int i = 0; i < pow; i++) {
          primeToPow *= prime;
          if (chineseRemainderTheoremValues.containsKey(primeToPow)) {
            if (zValue % primeToPow != chineseRemainderTheoremValues.get(primeToPow).longValue()) {
              throw new RuntimeException("Can't be solved"); // TODO -Handle properly
            }
            chineseRemainderTheoremValues.remove(primeToPow);
          }
        }
        chineseRemainderTheoremValues.put(
            BigInteger.valueOf(primeToPow), BigInteger.valueOf(zValue % primeToPow));
      }
    }

    Optional<BigInteger[]> result =
        NumberTheory.solveChineseRemainders(chineseRemainderTheoremValues);
    return result.get()[0].add(BigInteger.valueOf(cycleStart));
  }
}
