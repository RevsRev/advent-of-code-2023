package rev.aoc.days.d22;

import org.apache.commons.lang3.tuple.Pair;
import rev.aoc.math.vec.Vec3;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class SandSlabsPartTwo extends SandSlabs{
    public SandSlabsPartTwo(Iterable<String> resources) {
        super(resources);
    }

    @Override
    protected Long solveImpl() throws Exception {
        List<String> lines = getOneAndOnlyResourceLines();
        Pair<int[][][], Map<Integer, Set<Vec3>>> slabsAndCoords = parse(lines);
        int[][][] slabs = slabsAndCoords.getLeft();
        Map<Integer,Set<Vec3>> slabCoords = slabsAndCoords.getRight();

        drop(slabs,slabCoords);
        return calculateDisintegrationChainReaction(slabs,slabCoords);
    }
}
