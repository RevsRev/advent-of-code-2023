package rev.aoc.days.d12;

import org.apache.commons.lang3.tuple.Pair;

public class HotSpringsPartOne extends HotSprings
{
    public HotSpringsPartOne(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected Pair<char[], int[]> toSchematic(char[] rowSchematic, int[] brokenCounts)
    {
        return Pair.of(rowSchematic, brokenCounts);
    }
}
