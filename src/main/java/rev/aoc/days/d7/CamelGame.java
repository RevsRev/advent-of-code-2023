package rev.aoc.days.d7;

import java.util.Comparator;

public class CamelGame
{
    private final long bet;
    private final CamelHand hand;


    public CamelGame(long bet, CamelHand hand)
    {
        this.bet = bet;
        this.hand = hand;
    }

    public static Comparator<CamelGame> comparator() {
        return (o1, o2) -> CamelHand.comparator().compare(o1.hand, o2.hand);
    }

    public long getScore(long ordinal) {
        return ordinal * bet;
    }
}
