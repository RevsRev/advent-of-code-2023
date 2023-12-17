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

public class ClumsyCrucible extends AocSolution<Long>
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

        SimpleDirectedWeightedGraph<Vec4, DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph(DefaultWeightedEdge.class);

        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                for (int k=0; k<=3; k++) {
                    graph.addVertex(new Vec4(i,j,k,0));
                    graph.addVertex(new Vec4(i,j,0,k));
                }
            }
        }

        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                for (int k=0; k<=3; k++) {

                    if (i+1<height) {
                        if (k<=2) {
                            DefaultWeightedEdge edge = graph.addEdge(new Vec4(i,j,k,0), new Vec4(i+1,j,k+1,0));
                            graph.setEdgeWeight(edge, cityBlock[i+1][j]);
                        }
                        if (k != 0) {
                            DefaultWeightedEdge edge = graph.addEdge(new Vec4(i,j,0,k), new Vec4(i+1,j,1,0));
                            graph.setEdgeWeight(edge, cityBlock[i+1][j]);
                        }

                    }
                    if (j+1<width) {
                        if (k<=2) {
                            DefaultWeightedEdge edge = graph.addEdge(new Vec4(i,j,0,k), new Vec4(i,j+1,0,k+1));
                            graph.setEdgeWeight(edge, cityBlock[i][j+1]);
                        }
                        if (k != 0) {
                            DefaultWeightedEdge edge = graph.addEdge(new Vec4(i,j,k,0), new Vec4(i,j+1,0,1));
                            graph.setEdgeWeight(edge, cityBlock[i][j+1]);
                        }
                    }
                    if (i-1>=0) {
                        if (k<=2) {
                            DefaultWeightedEdge edge = graph.addEdge(new Vec4(i,j,k,0), new Vec4(i-1,j,k+1,0));
                            graph.setEdgeWeight(edge, cityBlock[i-1][j]);
                        }
                        if (k!=0) {
                            DefaultWeightedEdge edge = graph.addEdge(new Vec4(i,j,0,k), new Vec4(i-1,j,1,0));
                            graph.setEdgeWeight(edge, cityBlock[i-1][j]);
                        }
                    }
                    if (j-1>=0) {
                        if (k<=2) {
                            DefaultWeightedEdge edge = graph.addEdge(new Vec4(i,j,0,k), new Vec4(i,j-1,0,k+1));
                            graph.setEdgeWeight(edge, cityBlock[i][j-1]);
                        }
                        if (k!=0) {
                            DefaultWeightedEdge edge = graph.addEdge(new Vec4(i,j,k,0), new Vec4(i,j-1,0,1));
                            graph.setEdgeWeight(edge, cityBlock[i][j-1]);
                        }
                    }
                }
            }
        }

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
