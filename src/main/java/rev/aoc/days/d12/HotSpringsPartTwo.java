package rev.aoc.days.d12;

import org.apache.commons.lang3.tuple.Pair;

public class HotSpringsPartTwo extends HotSprings
{
    public HotSpringsPartTwo(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected Pair<char[], int[]> toSchematic(String[] rowSchematic, int[] brokenCounts)
    {
        Pair<char[], int[]> springsAndBrokenCounts = Pair.of(rowSchematic[0].toCharArray(), brokenCounts);
        return springsAndBrokenCounts;
    }
}
