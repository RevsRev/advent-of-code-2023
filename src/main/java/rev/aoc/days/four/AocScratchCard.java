package rev.aoc.days.four;

import rev.aoc.AocSolution;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AocScratchCard extends AocSolution<Long>
{

    public AocScratchCard(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected Long solveImpl() throws Exception
    {
        List<String> lines = getOneAndOnlyResourceLines();
        Map<Integer, ScratchCard> scratchCards = loadScratchCards(lines);

        Iterator<Integer> itGameIds = scratchCards.keySet().iterator();
        long score = 0;
        while (itGameIds.hasNext()) {
            int gameId = itGameIds.next();
            ScratchCard sc = scratchCards.get(gameId);
            score += sc.getScore();
        }
        return score;
    }

    private Map<Integer, ScratchCard> loadScratchCards(List<String> lines)
    {
        Map<Integer, ScratchCard> retval = new HashMap<>();
        for (int i=0; i<lines.size(); i++) {
            ScratchCard sc = ScratchCard.fromGameLine(lines.get(i));
            retval.put(sc.getId(), sc);
        }
        return retval;
    }
}
