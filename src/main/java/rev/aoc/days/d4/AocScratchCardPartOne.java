package rev.aoc.days.d4;

import java.util.Iterator;
import java.util.Map;

public class AocScratchCardPartOne extends AocScratchCard
{
    public AocScratchCardPartOne(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected long solve(Map<Integer, ScratchCard> scratchCards)
    {
        Iterator<Integer> itGameIds = scratchCards.keySet().iterator();
        long score = 0;
        while (itGameIds.hasNext())
        {
            int gameId = itGameIds.next();
            ScratchCard sc = scratchCards.get(gameId);
            score += sc.getScore();
        }
        return score;
    }
}
