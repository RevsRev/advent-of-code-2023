package rev.aoc.days.two;

import java.util.List;
import java.util.Map;

public class CubeConundrumPartOne extends CubeConundrum
{
    private static final int NUM_REDS = 12;
    private static final int NUM_GREENS = 13;
    private static final int NUM_BLUES = 14;

    public CubeConundrumPartOne(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected long getIncrementingAmount(Map<Integer, List<Handful>> gameHandfulMap, int gameNumber)
    {
        List<Handful> handfuls = gameHandfulMap.get(gameNumber);
        if (checkHandfuls(handfuls)) {
            return gameNumber;
        }
        return 0;
    }

    private boolean checkHandfuls(List<Handful> handfuls) {
        for (int i=0; i<handfuls.size(); i++) {
            Handful handful = handfuls.get(i);
            if (handful.red > NUM_REDS) {
                return false;
            }
            if (handful.blue > NUM_BLUES) {
                return false;
            }
            if (handful.green > NUM_GREENS) {
                return false;
            }
        }
        return true;
    }
}
