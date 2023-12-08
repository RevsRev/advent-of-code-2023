package rev.aoc.days.d8;


import java.util.HashMap;

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
    
    public long traverse(char[] steps, GraphNode start, GraphNode end) {
        
        GraphNode node = start;
        int stepIndex = 0;
        int numSteps = 0;
        while (true) {
            if (node.equals(end)) {
                return numSteps;
            }
            
            char step = steps[stepIndex];
            if (step == 'L') {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
            numSteps++;
            stepIndex = (stepIndex + 1)%steps.length;
        }
    }
    
}
