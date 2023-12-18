package rev.aoc.days.d14;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReflectorDishPartTwo extends ReflectorDish {
  public ReflectorDishPartTwo(Iterable<String> resources) {
    super(resources);
  }

  @Override
  protected Long solveImpl() throws Exception {
    List<String> lines = getOneAndOnlyResourceLines();
    char[][] dish = parse(lines);

    Map<Integer, Integer> visitHashes = new HashMap<>();

    int iterations = 1000000000;
    for (int i = 0; i < iterations; i++) {
      cycle(dish);
      int hash = hash(dish);
      if (!visitHashes.containsKey(hash)) {
        visitHashes.put(hash, i);
      } else {
        int previousVisit = visitHashes.get(hash);
        int cycleLength = i - previousVisit;
        visitHashes = new HashMap<>(); // clear the map
        i += ((iterations - i) / cycleLength) * cycleLength;
      }
    }

    return calcLoad(dish);
  }

  private int hash(char[][] dish) {
    return Arrays.deepHashCode(dish);
  }

  private void cycle(char[][] dish) {
    slide(dish, Direction.NORTH);
    slide(dish, Direction.WEST);
    slide(dish, Direction.SOUTH);
    slide(dish, Direction.EAST);
  }
}
