package rev.aoc.days.d18;

import java.util.List;

public class LavaductLagoonPartTwo extends LavaductLagoon {
  public LavaductLagoonPartTwo(Iterable<String> resources) {
    super(resources);
  }

  @Override
  protected String[][] parse(List<String> lines) {
    String[][] instructions = new String[lines.size()][];
    for (int i = 0; i < lines.size(); i++) {
      String[] rawInstruction = lines.get(i).split("\\s+");
      String hexAndInstruction = rawInstruction[2].replace("(", "").replace(")", "");
      String hex = hexAndInstruction.substring(0, hexAndInstruction.length() - 1);
      String code = hexAndInstruction.substring(hexAndInstruction.length() - 1);
      instructions[i] = new String[] {toLetter(code), "" + Long.decode(hex)};
    }
    return instructions;
  }

  private String toLetter(String code) {
    int i = Integer.parseInt(code);
    switch (i) {
      case 0:
        return "R";
      case 1:
        return "D";
      case 2:
        return "L";
      default:
        return "U";
    }
  }
}
