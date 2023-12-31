package rev.aoc.days.d10;

import java.util.List;
import rev.aoc.math.vec.Vec2;

public class PipeMazePartOne extends PipeMaze {
  public PipeMazePartOne(Iterable<String> resources) {
    super(resources);
  }

  @Override
  public long solveProblemForLoop(char[][] mazeMap, List<Vec2> loop) {
    return (long) (loop.size() + 1) / 2;
  }
}
