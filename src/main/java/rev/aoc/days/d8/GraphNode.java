package rev.aoc.days.d8;

import lombok.Getter;
import lombok.Setter;

public class GraphNode {
  @Getter private final String name;
  @Getter @Setter private GraphNode left;
  @Getter @Setter private GraphNode right;

  public GraphNode(String name) {
    this.name = name;
  }
}
