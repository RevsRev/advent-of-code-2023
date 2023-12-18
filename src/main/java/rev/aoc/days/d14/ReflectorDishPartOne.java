package rev.aoc.days.d14;

import java.util.List;

public class ReflectorDishPartOne extends ReflectorDish {
  public ReflectorDishPartOne(Iterable<String> resources) {
    super(resources);
  }

  @Override
  protected Long solveImpl() throws Exception {
    List<String> lines = getOneAndOnlyResourceLines();
    char[][] dish = parse(lines);

    slide(dish, Direction.NORTH);
    return calcLoad(dish);
  }
}
