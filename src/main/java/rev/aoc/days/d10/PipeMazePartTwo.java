package rev.aoc.days.d10;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import rev.aoc.math.topology.Loop;
import rev.aoc.math.vec.Vec2;
import rev.aoc.math.vec.Vec3;

public class PipeMazePartTwo extends PipeMaze {
  public PipeMazePartTwo(Iterable<String> resources) {
    super(resources);
  }

  @Override
  public long solveProblemForLoop(char[][] mazeMap, List<Vec2> loop) {
    Set<Vec2> loopParts = new HashSet<>();
    loopParts.addAll(loop);

    Set<Vec2> interior = Loop.getInterior(loop);

    return interior.size();
  }
}
