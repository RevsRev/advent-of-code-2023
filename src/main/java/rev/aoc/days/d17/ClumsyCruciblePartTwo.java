package rev.aoc.days.d17;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import rev.aoc.math.vec.Vec4;

public class ClumsyCruciblePartTwo extends ClumsyCrucible
{
    public ClumsyCruciblePartTwo(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected SimpleDirectedWeightedGraph<Vec4, DefaultWeightedEdge> toGraph(int[][] cityBlock)
    {
        int height = cityBlock.length;
        int width = cityBlock[0].length;

        SimpleDirectedWeightedGraph<Vec4, DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph(DefaultWeightedEdge.class);

        for (int i = 0; i< height; i++) {
            for (int j = 0; j< width; j++) {
                for (int k=0; k<=3; k++) {
                    graph.addVertex(new Vec4(i,j,k,0));
                    graph.addVertex(new Vec4(i,j,0,k));
                }
            }
        }

        for (int i = 0; i< height; i++) {
            for (int j = 0; j< width; j++) {
                for (int k=0; k<=3; k++) {

                    if (i+1< height) {
                        if (k<=2) {
                            DefaultWeightedEdge edge = graph.addEdge(new Vec4(i,j,k,0), new Vec4(i+1,j,k+1,0));
                            graph.setEdgeWeight(edge, cityBlock[i+1][j]);
                        }
                        if (k != 0) {
                            DefaultWeightedEdge edge = graph.addEdge(new Vec4(i,j,0,k), new Vec4(i+1,j,1,0));
                            graph.setEdgeWeight(edge, cityBlock[i+1][j]);
                        }

                    }
                    if (j+1< width) {
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
        return graph;
    }
}
