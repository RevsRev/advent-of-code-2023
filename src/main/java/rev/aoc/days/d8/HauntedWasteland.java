package rev.aoc.days.d8;

import org.apache.commons.lang3.tuple.Pair;
import rev.aoc.AocSolution;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public abstract class HauntedWasteland extends AocSolution<Long>
{
    public HauntedWasteland(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected Long solveImpl() throws Exception
    {
        List<String> lines = getOneAndOnlyResourceLines();
        Pair<char[], Graph> stepsAndGraph = parse(lines);
        char[] steps = stepsAndGraph.getLeft();
        Graph g = stepsAndGraph.getRight();
        return g.traverse(steps, getStartPredicate(), getEndPredicate());
    }

    protected abstract Function<String, Boolean> getStartPredicate();
    protected abstract Function<String, Boolean> getEndPredicate();

    private Pair<char[], Graph> parse(List<String> lines)
    {
        Graph graph = new Graph(new HashMap<>());
        char[] steps = lines.get(0).toCharArray();
        for (int i=2; i<lines.size(); i++) {
            String line = lines.get(i);
            String[] nameAndConnections = line.split("=");
            String name = nameAndConnections[0].trim();
            String[] leftAndRight = nameAndConnections[1].trim().replace("(", "").replace(")", "").split(",");
            String leftName = leftAndRight[0].trim();
            String rightName = leftAndRight[1].trim();
            graph.addNode(name, leftName, rightName);
        }
        return Pair.of(steps,graph);
    }
}
