package rev.aoc.days.d8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import org.apache.commons.lang3.tuple.Pair;

public class Graph {
  private HashMap<String, GraphNode> nodes;

  public Graph(HashMap<String, GraphNode> nodes) {
    this.nodes = nodes;
  }

  public GraphNode getNodeForName(String name) {
    return nodes.get(name);
  }

  public void addNode(String name, String leftChildName, String rightChildName) {
    GraphNode node = getFromGraphOrAdd(name);
    if (node.getLeft() == null) {
      GraphNode left = getFromGraphOrAdd(leftChildName);
      node.setLeft(left);
      nodes.put(leftChildName, left);
    }
    if (node.getRight() == null) {
      GraphNode right = getFromGraphOrAdd(rightChildName);
      node.setRight(right);
      nodes.put(rightChildName, right);
    }
  }

  private GraphNode getFromGraphOrAdd(String nodeName) {
    GraphNode graphNode = nodes.get(nodeName);
    if (graphNode == null) {
      graphNode = new GraphNode(nodeName);
      nodes.put(nodeName, graphNode);
    }
    return graphNode;
  }

  public Pair<GraphNode, Pair<Long, Long>> extractCycle(char[] steps, GraphNode start) {
    long index = 0;
    GraphNode node = start;
    Map<String, Map<Long, Long>> previousTraversals = new HashMap<>();
    while (true) {
      long indexModulo = index % steps.length;
      if (previousTraversals
          .getOrDefault(node.getName(), new HashMap<>())
          .containsKey(indexModulo)) {
        long startIndex = previousTraversals.get(node.getName()).get(indexModulo);
        long endIndex = index;
        return atomize(steps, node, startIndex, endIndex);
      }

      previousTraversals.computeIfAbsent(node.getName(), k -> new HashMap<>());
      previousTraversals.get(node.getName()).put(indexModulo, index);

      char step = steps[(int) indexModulo];
      if (step == 'L') {
        node = node.getLeft();
      } else {
        node = node.getRight();
      }
      index++;
    }
  }

  private Pair<GraphNode, Pair<Long, Long>> atomize(
      char[] steps, GraphNode start, long startIndex, long endIndex) {
    GraphNode node = start;
    GraphNode[] nodes = new GraphNode[(int) (endIndex - startIndex)];
    for (long i = startIndex; i < endIndex; i++) {
      nodes[(int) (i - startIndex)] = node;
      char c = steps[(int) i % steps.length];
      if (c == 'L') {
        node = node.getLeft();
      } else {
        node = node.getRight();
      }
    }
    nodes = HauntedWasteland.atomize(nodes);
    return Pair.of(start, Pair.of(startIndex, (long) nodes.length));
  }

  public long traverse(
      char[] steps,
      Function<String, Boolean> startPredicate,
      Function<String, Boolean> endPredicate) {

    List<GraphNode> nodes = getAllMatching(startPredicate); // starting nodes

    int stepIndex = 0;
    int numSteps = 0;
    while (true) {
      if (allMatch(nodes, endPredicate)) {
        return numSteps;
      }

      char step = steps[stepIndex];
      List<GraphNode> nextNodes = new ArrayList<>();
      for (int i = 0; i < nodes.size(); i++) {
        GraphNode node = nodes.get(i);

        if (step == 'L') {
          node = node.getLeft();
        } else {
          node = node.getRight();
        }
        nextNodes.add(node);
      }
      nodes = nextNodes;
      numSteps++;
      stepIndex = (stepIndex + 1) % steps.length;
    }
  }

  private boolean allMatch(List<GraphNode> nodes, Function<String, Boolean> endPredicate) {
    for (int i = 0; i < nodes.size(); i++) {
      if (!endPredicate.apply(nodes.get(i).getName())) {
        return false;
      }
    }
    return true;
  }

  public List<GraphNode> getAllMatching(Function<String, Boolean> startPredicate) {
    List<GraphNode> starts = new ArrayList<>();
    Iterator<String> it = nodes.keySet().iterator();
    while (it.hasNext()) {
      String nodeName = it.next();
      if (startPredicate.apply(nodeName)) {
        starts.add(nodes.get(nodeName));
      }
    }
    return starts;
  }
}
