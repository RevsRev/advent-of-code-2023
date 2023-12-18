package rev.aoc.days.d16;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;
import rev.aoc.AocSolution;
import rev.aoc.math.vec.Vec2;

public abstract class FloorLava extends AocSolution<Long> {
  public static final Vec2 UP =
      new Vec2(0, -1); // top of array is y=0, increasing coordinates as we go down.
  public static final Vec2 DOWN = new Vec2(0, 1);
  public static final Vec2 LEFT = new Vec2(-1, 0);
  public static final Vec2 RIGHT = new Vec2(1, 0);

  public FloorLava(Iterable<String> resources) {
    super(resources);
  }

  @Override
  protected Long solveImpl() throws Exception {
    List<String> lines = getOneAndOnlyResourceLines();
    char[][] mirrorContraption = parse(lines);

    return solveProblem(mirrorContraption);
  }

  protected abstract long solveProblem(char[][] mirrorContraption);

  protected long getEnergy(
      char[][] mirrorContraption, int height, int width, Vec2 start, Vec2 startDir) {
    Set<Pair<Vec2, Vec2>> branches = new HashSet<>(List.of(Pair.of(start, startDir)));
    Set<Pair<Vec2, Vec2>> considered = new HashSet<>();

    while (!branches.isEmpty()) {
      Set<Pair<Vec2, Vec2>> nextBranches = new HashSet<>();
      Iterator<Pair<Vec2, Vec2>> it = branches.iterator();
      while (it.hasNext()) {
        Pair<Vec2, Vec2> head = it.next();
        if (considered.contains(head)) {
          continue;
        }
        considered.add(head);

        Vec2 coord = head.getLeft();
        Vec2 direction = head.getRight();
        Set<Vec2> nextDirections =
            getOutputForInput(mirrorContraption[(int) coord.y][(int) coord.x], direction);
        Iterator<Vec2> itNext = nextDirections.iterator();
        while (itNext.hasNext()) {
          Vec2 nextDir = itNext.next();
          Vec2 nCoord = coord.add(nextDir);
          if (!(nCoord.x < 0 || nCoord.x >= width || nCoord.y < 0 || nCoord.y >= height)) {
            nextBranches.add(Pair.of(nCoord, nextDir));
          }
        }
      }
      branches = nextBranches;
    }

    return (long) getEnergisedCoordinates(considered).size();
  }

  private void print(char[][] mirrorContraption, Set<Pair<Vec2, Vec2>> considered) {
    Vec2[] dirs = new Vec2[] {UP, DOWN, LEFT, RIGHT};
    int height = mirrorContraption.length;
    int width = mirrorContraption[0].length;
    for (int i = 0; i < height; i++) {
      char[] row = new char[width];
      for (int j = 0; j < width; j++) {
        long count = 0;
        for (int k = 0; k < dirs.length; k++) {
          if (considered.contains(Pair.of(new Vec2(j, i), dirs[k]))) {
            count++;
          }
        }
        row[j] = count > 0 ? '#' : '.';
      }
      System.out.println(
          Arrays.toString(row).replace("]", "").replace("[", "").replace(",", "").replace(" ", ""));
    }
  }

  private Set<Vec2> getEnergisedCoordinates(Set<Pair<Vec2, Vec2>> considered) {
    Set<Vec2> nodeCoords = new HashSet<>();

    Iterator<Pair<Vec2, Vec2>> it = considered.iterator();
    while (it.hasNext()) {
      nodeCoords.add(it.next().getLeft());
    }
    return nodeCoords;
  }

  private Set<Vec2> getOutputForInput(char c, Vec2 input) {
    if (c == '.') {
      return Set.of(input);
    }

    if (c == '|') {
      if (input.equals(UP) || input.equals(DOWN)) {
        return Set.of(input);
      }
      return Set.of(UP, DOWN);
    }

    if (c == '-') {
      if (input.equals(LEFT) || input.equals(RIGHT)) {
        return Set.of(input);
      }
      return Set.of(LEFT, RIGHT);
    }

    if (c == '/') {
      if (input.equals(DOWN)) {
        return Set.of(LEFT);
      }
      if (input.equals(RIGHT)) {
        return Set.of(UP);
      }
      if (input.equals(UP)) {
        return Set.of(RIGHT);
      }
      return Set.of(DOWN);
    }

    // else t == \
    if (input.equals(DOWN)) {
      return Set.of(RIGHT);
    }
    if (input.equals(RIGHT)) {
      return Set.of(DOWN);
    }
    if (input.equals(UP)) {
      return Set.of(LEFT);
    }
    return Set.of(UP);
  }

  private char[][] parse(List<String> lines) {
    char[][] contraption = new char[lines.size()][];
    for (int i = 0; i < lines.size(); i++) {
      String line = lines.get(i);
      contraption[i] = line.toCharArray();
    }
    return contraption;
  }
}
