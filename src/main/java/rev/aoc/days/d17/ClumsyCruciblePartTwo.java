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
    protected int getMaximumRunLength()
    {
        return 10;
    }

    @Override
    protected int getMinimumRunLength()
    {
        return 4;
    }
}
