package rev.aoc.days.d10;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    long windingNumber = getWindingNumber(loop);
    Vec3 planeNorm = new Vec3(0, 0, 1 * windingNumber);

    Set<Vec2> interior = getPointsBetweenPipes(loop, planeNorm);

    return interior.size();
  }
}
