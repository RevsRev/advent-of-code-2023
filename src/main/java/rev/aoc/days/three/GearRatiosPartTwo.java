package rev.aoc.days.three;

import org.apache.commons.lang3.tuple.Pair;

public class GearRatiosPartTwo extends GearRatios
{
    public GearRatiosPartTwo(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected boolean checkNumber(char[][] chars, int height, int width, Pair<Integer, Integer> coordinate, int length)
    {
        return false;
    }
}
