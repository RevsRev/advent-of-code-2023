package rev.aoc.days.d17;

import org.apache.commons.lang3.tuple.Pair;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.graph.SimpleWeightedGraph;
import rev.aoc.AocSolution;
import rev.aoc.math.vec.Vec2;
import rev.aoc.math.vec.Vec4;

import java.util.*;

public abstract class ClumsyCrucible extends AocSolution<Long>
{
    private static final int UNSET = -2;
    private static final int INVALID = -1;

    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;

    public ClumsyCrucible(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected Long solveImpl() throws Exception
    {
        List<String> lines = getOneAndOnlyResourceLines();
        int[][] cityBlock = parse(lines);
        int height = cityBlock.length;
        int width = cityBlock[0].length;

        SimpleDirectedWeightedGraph<Vec4, DefaultWeightedEdge> graph = toGraph(cityBlock);

        long result = Long.MAX_VALUE;
        for (int k=0; k<=3; k++) {
            GraphPath<Vec4, DefaultWeightedEdge> pathBetween = DijkstraShortestPath.findPathBetween(graph, new Vec4(0, 0, 0, 0), new Vec4(height - 1, width - 1, k, 0));
            if (pathBetween != null) {
                result = Math.min(result, (long)pathBetween.getWeight());
            }
            pathBetween = DijkstraShortestPath.findPathBetween(graph, new Vec4(0, 0, 0, 0), new Vec4(height - 1, width - 1, 0, k));
            if (pathBetween != null) {
                result = Math.min(result, (long)pathBetween.getWeight());
            }
        }
        return result;
    }

    protected abstract SimpleDirectedWeightedGraph<Vec4, DefaultWeightedEdge> toGraph(int[][] cityBlock);
    private int[][] parse(List<String> lines)
    {
        int[][] cityBlock = new int[lines.size()][];
        for (int i=0; i<lines.size(); i++) {
            String line = lines.get(i);
            cityBlock[i] = new int[line.length()];
            for (int j=0; j<line.length(); j++) {
                cityBlock[i][j] = Integer.parseInt("" + line.charAt(j));
            }
        }
        return cityBlock;
    }
}
