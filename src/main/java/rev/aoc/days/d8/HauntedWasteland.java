package rev.aoc.days.d8;

import org.apache.commons.lang3.tuple.Pair;
import rev.aoc.AocSolution;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

/**
 * Seems to take too long to run...
 */
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

    public static Pair<char[], Graph> parse(List<String> lines)
    {
        Graph graph = new Graph(new HashMap<>());
        char[] steps = lines.get(0).toCharArray();
        for (int i = 2; i < lines.size(); i++)
        {
            String line = lines.get(i);
            String[] nameAndConnections = line.split("=");
            String name = nameAndConnections[0].trim();
            String[] leftAndRight = nameAndConnections[1].trim().replace("(", "").replace(")", "").split(",");
            String leftName = leftAndRight[0].trim();
            String rightName = leftAndRight[1].trim();
            graph.addNode(name, leftName, rightName);
        }
        return Pair.of(steps, graph);
    }

    public static <T> T[] atomize(T[] list)
    {
        int size = list.length;
        for (int i = 2; i < size; i++)
        {
            if (size % i == 0)
            {
                boolean repeatedList = true;
                for (int j = 0; j < i; j++)
                {
                    T firstThing = list[j];
                    for (int k = 1; k < size / i; k++)
                    {
                        if (list[k * i + j] != firstThing)
                        {
                            repeatedList = false;
                            break;
                        }
                    }
                    if (!repeatedList)
                    {
                        break;
                    }
                }
                if (repeatedList)
                {
                    return atomize(Arrays.copyOfRange(list, 0, i));
                }
            }
        }
        return list;
    }
}
