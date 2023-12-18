package rev.aoc.days.d17;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import rev.aoc.AocSolution;
import rev.aoc.math.vec.Vec4;

import java.util.*;

public abstract class ClumsyCrucible extends AocSolution<Long>
{

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
        for (int k = -getMaximumRunLength(); k<= getMaximumRunLength(); k++) {
            if (k>-getMinimumRunLength() && k<getMinimumRunLength()) {
                continue;
            }
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

    protected abstract int getMaximumRunLength();
    protected abstract int getMinimumRunLength();

    protected SimpleDirectedWeightedGraph<Vec4, DefaultWeightedEdge> toGraph(int[][] cityBlock)
    {
        int height = cityBlock.length;
        int width = cityBlock[0].length;

        SimpleDirectedWeightedGraph<Vec4, DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph(DefaultWeightedEdge.class);

        int max = getMaximumRunLength();
        int min = getMinimumRunLength();

        for (int i = 0; i< height; i++) {
            for (int j = 0; j< width; j++) {
                for (int k=-max; k<=max; k++) {
                    graph.addVertex(new Vec4(i,j,k,0));
                    graph.addVertex(new Vec4(i,j,0,k));
                }
            }
        }

        for (int i = 0; i< height; i++) {
            for (int j = 0; j< width; j++) {
                for (int k=-max; k<=max; k++) {

                    if (i+1< height) {
                        if (0<=k && k<=max-1) {
                            DefaultWeightedEdge edge = graph.addEdge(new Vec4(i,j,k,0), new Vec4(i+1,j,k+1,0));
                            graph.setEdgeWeight(edge, cityBlock[i+1][j]);
                        }
                        if (Math.abs(k) >= min) {
                            DefaultWeightedEdge edge = graph.addEdge(new Vec4(i,j,0,k), new Vec4(i+1,j,1,0));
                            graph.setEdgeWeight(edge, cityBlock[i+1][j]);
                        }

                    }
                    if (j+1< width) {
                        if (0<=k && k<=max-1) {
                            DefaultWeightedEdge edge = graph.addEdge(new Vec4(i,j,0,k), new Vec4(i,j+1,0,k+1));
                            graph.setEdgeWeight(edge, cityBlock[i][j+1]);
                        }
                        if (Math.abs(k) >= min) {
                            DefaultWeightedEdge edge = graph.addEdge(new Vec4(i,j,k,0), new Vec4(i,j+1,0,1));
                            graph.setEdgeWeight(edge, cityBlock[i][j+1]);
                        }
                    }
                    if (i-1>=0) {
                        if (k<=0 && k>=-max+1) {
                            DefaultWeightedEdge edge = graph.addEdge(new Vec4(i,j,k,0), new Vec4(i-1,j,k-1,0));
                            graph.setEdgeWeight(edge, cityBlock[i-1][j]);
                        }
                        if (Math.abs(k) >= min) {
                            DefaultWeightedEdge edge = graph.addEdge(new Vec4(i,j,0,k), new Vec4(i-1,j,-1,0));
                            graph.setEdgeWeight(edge, cityBlock[i-1][j]);
                        }
                    }
                    if (j-1>=0) {
                        if (k<=0 && k>=-max+1) {
                            DefaultWeightedEdge edge = graph.addEdge(new Vec4(i,j,0,k), new Vec4(i,j-1,0,k-1));
                            graph.setEdgeWeight(edge, cityBlock[i][j-1]);
                        }
                        if (Math.abs(k) >= min) {
                            DefaultWeightedEdge edge = graph.addEdge(new Vec4(i,j,k,0), new Vec4(i,j-1,0,-1));
                            graph.setEdgeWeight(edge, cityBlock[i][j-1]);
                        }
                    }
                }
            }
        }
        return graph;
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
