package rev.aoc.days.d18;

import java.util.List;

public class LavaductLagoonPartOne extends LavaductLagoon {
  public LavaductLagoonPartOne(Iterable<String> resources) {
    super(resources);
  }

  @Override
  protected String[][] parse(List<String> lines) {
    String[][] instructions = new String[lines.size()][];
    for (int i = 0; i < lines.size(); i++) {
      instructions[i] = lines.get(i).split("\\s+");
    }
    return instructions;
  }
}
