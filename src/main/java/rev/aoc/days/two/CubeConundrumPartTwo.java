package rev.aoc.days.two;

import java.util.List;
import java.util.Map;

public class CubeConundrumPartTwo extends CubeConundrum
{
    public CubeConundrumPartTwo(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected long getIncrementingAmount(Map<Integer, List<Handful>> gameHandfulMap, int gameNumber)
    {
        return 0;
    }
}
