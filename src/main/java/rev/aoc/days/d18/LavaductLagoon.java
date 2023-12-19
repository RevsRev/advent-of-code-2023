package rev.aoc.days.d18;

import java.util.ArrayList;
import java.util.List;
import rev.aoc.AocSolution;
import rev.aoc.math.topology.Loop;
import rev.aoc.math.vec.Vec2;

public class LavaductLagoon extends AocSolution<Long> {
  public LavaductLagoon(Iterable<String> resources) {
    super(resources);
  }

  @Override
  protected Long solveImpl() throws Exception {
    List<String> lines = getOneAndOnlyResourceLines();
    String[][] instructions = parse(lines);
    List<Vec2> loop = new ArrayList<>();
    Vec2 start = new Vec2(0, 0);
    Vec2 node = start;

    int instrIndex = 0;
    do {
      loop.add(node);
      Vec2 direction = getDirection(instructions[instrIndex][0]);
      int steps = Integer.parseInt(instructions[instrIndex][1]);
      for (int i = 0; i < steps - 1; i++) {
        node = node.add(direction);
        loop.add(node);
      }
      node = node.add(direction);
      instrIndex++;

    } while (node != start && instrIndex < instructions.length);
    return Loop.getArea(loop);
  }

  private Vec2 getDirection(String direction) {
    switch (direction) {
      case "U":
        return new Vec2(0, 1);
      case "D":
        return new Vec2(0, -1);
      case "R":
        return new Vec2(1, 0);
      default:
        return new Vec2(-1, 0);
    }
  }

  private String[][] parse(List<String> lines) {
    String[][] instructions = new String[lines.size()][];
    for (int i = 0; i < lines.size(); i++) {
      instructions[i] = lines.get(i).split("\\s+");
    }
    return instructions;
  }
}
