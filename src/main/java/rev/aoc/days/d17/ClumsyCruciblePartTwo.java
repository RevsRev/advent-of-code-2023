package rev.aoc.days.d17;

public class ClumsyCruciblePartTwo extends ClumsyCrucible {
  public ClumsyCruciblePartTwo(Iterable<String> resources) {
    super(resources);
  }

  @Override
  protected int getMaximumRunLength() {
    return 10;
  }

  @Override
  protected int getMinimumRunLength() {
    return 4;
  }
}
