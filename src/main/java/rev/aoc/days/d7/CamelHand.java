package rev.aoc.days.d7;

import java.util.Comparator;

public abstract class CamelHand
{
    protected final int JUNK = 0;
    protected final int PAIR = 1;
    protected final int TWO_PAIR = 2;
    protected final int THREE_OF_A_KIND = 3;
    protected final int FULL_HOUSE = 4;
    protected final int FOUR_OF_A_KIND = 5;
    protected final int FIVE_OF_A_KIND = 6;


    protected final long[] cards;

    public CamelHand(long[] cards)
    {
        this.cards = cards;
    }

    public static Comparator<CamelHand> comparator()
    {
        return Comparator.comparingLong(CamelHand::getRanking);
    }

    private int getRanking()
    {
        int type = getType();
        int base = 14;
        int factor = 1;
        int ranking = 0;
        for (int i = cards.length - 1; i >= 0; i--)
        {
            ranking += factor * score(cards[i]);
            factor *= base;
        }
        return type * factor + ranking;
    }

    protected abstract long score(long card);

    protected abstract int getType();

    public static CamelHand fromStrHand(String strHand, boolean jokersAsWildcards)
    {
        long[] hand = new long[strHand.length()];
        for (int i = 0; i < strHand.length(); i++)
        {
            char c = strHand.charAt(i);
            long value = toValue(c);
            hand[i] = value;
        }
        if (jokersAsWildcards)
        {
            return new CamelHandJokersWildcard(hand);
        }
        return new CamelHandJokersNormal(hand);
    }

    private static long toValue(char c)
    {
        switch (c)
        {
            case 'A':
                return 13;
            case 'K':
                return 12;
            case 'Q':
                return 11;
            case 'J':
                return 10;
            case 'T':
                return 9;
            default:
                return Long.parseLong("" + c) - 1;
        }
    }

}
