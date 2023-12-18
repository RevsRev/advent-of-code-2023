package rev.aoc.days.d4;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AocScratchCardPartTwo extends AocScratchCard
{
    public AocScratchCardPartTwo(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected long solve(Map<Integer, ScratchCard> scratchCards)
    {
        int first = 1;
        int last = scratchCards.size();

        Map<Integer, Integer> cardsWon = new HashMap<>();
        for (int i = first; i <= last; i++)
        {
            cardsWon.put(i, 1);
        }

        int totalCards = 0;
        for (int i = first; i <= last; i++)
        {
            int numWon = cardsWon.get(i);
            totalCards += numWon;
            ScratchCard sc = scratchCards.get(i);
            Set<Integer> numbersThatWin = sc.getNumbersThatWin();
            int numWinners = numbersThatWin.size();
            for (int j = i + 1; j < i + 1 + numWinners; j++)
            {
                cardsWon.compute(j, (k, v) -> v + numWon);
            }
        }

        return totalCards;
    }
}
