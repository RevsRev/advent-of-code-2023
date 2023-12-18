package rev.aoc.days.d17;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import rev.aoc.math.vec.Vec4;

public class ClumsyCruciblePartOne extends ClumsyCrucible
{
    public ClumsyCruciblePartOne(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected int getMaximumRunLength()
    {
        return 3;
    }

    @Override
    protected int getMinimumRunLength()
    {
        return 1;
    }
}
