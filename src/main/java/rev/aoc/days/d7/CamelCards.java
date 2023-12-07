package rev.aoc.days.d7;

import rev.aoc.AocSolution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CamelCards extends AocSolution<Long>
{
    public CamelCards(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected Long solveImpl() throws Exception
    {
        List<String> lines = getOneAndOnlyResourceLines();
        List<CamelGame> games = parse(lines);

        Collections.sort(games, CamelGame.comparator());

        long totalWinnings = 0;
        for (int i=0; i<games.size(); i++) {
            totalWinnings += games.get(i).getScore(i+1);
        }
        return totalWinnings;
    }

    private List<CamelGame> parse(List<String> lines)
    {
        List<CamelGame> games = new ArrayList<>();

        Iterator<String> it = lines.iterator();
        while (it.hasNext()) {
            String[] line = it.next().trim().split("\\s+");
            CamelHand hand = CamelHand.fromStrHand(line[0]);
            long bet = Long.parseLong(line[1]);
            games.add(new CamelGame(bet, hand));
        }
        return games;
    }


}
