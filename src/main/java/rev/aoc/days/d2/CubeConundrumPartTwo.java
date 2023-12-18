package rev.aoc.days.d2;

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
        long minRed = 0;
        long minGreen = 0;
        long minBlue = 0;
        List<Handful> handfuls = gameHandfulMap.get(gameNumber);
        for (int i = 0; i < handfuls.size(); i++)
        {
            Handful handful = handfuls.get(i);
            minRed = Math.max(minRed, handful.red);
            minGreen = Math.max(minGreen, handful.green);
            minBlue = Math.max(minBlue, handful.blue);
        }
        return minRed * minGreen * minBlue;
    }
}
