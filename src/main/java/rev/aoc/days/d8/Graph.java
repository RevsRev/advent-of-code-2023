package rev.aoc.days.d8;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class Graph
{
    private HashMap<String, GraphNode> nodes;
    
    public Graph(HashMap<String,GraphNode> nodes)
    {
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
    
    public long traverse(char[] steps, Function<String,Boolean> startPredicate, Function<String,Boolean> endPredicate) {

        List<GraphNode> nodes = getAllMatching(startPredicate); //starting nodes

        int stepIndex = 0;
        int numSteps = 0;
        while (true) {
            if (allMatch(nodes, endPredicate)) {
                return numSteps;
            }

            char step = steps[stepIndex];
            List<GraphNode> nextNodes = new ArrayList<>();
            for (int i=0; i<nodes.size(); i++) {
                GraphNode node =nodes.get(i);

                if (step == 'L') {
                    node = node.getLeft();
                } else {
                    node = node.getRight();
                }
                nextNodes.add(node);
            }
            nodes=nextNodes;
            numSteps++;
            stepIndex = (stepIndex + 1)%steps.length;
        }
    }

    private boolean allMatch(List<GraphNode> nodes, Function<String, Boolean> endPredicate)
    {
        for (int i=0; i<nodes.size(); i++) {
            if (!endPredicate.apply(nodes.get(i).getName())) {
                return false;
            }
        }
        return true;
    }

    private List<GraphNode> getAllMatching(Function<String, Boolean> startPredicate)
    {
        List<GraphNode> starts = new ArrayList<>();
        Iterator<String> it = nodes.keySet().iterator();
        while (it.hasNext()) {
            String nodeName = it.next();
            if (startPredicate.apply(nodeName)){
                starts.add(nodes.get(nodeName));
            }
        }
        return starts;
    }

}
