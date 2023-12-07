package rev.aoc.days.d7;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CamelHand
{
    private final int JUNK = 0;
    private final int PAIR = 1;
    private final int TWO_PAIR = 2;
    private final int THREE_OF_A_KIND=3;
    private final int FULL_HOUSE = 4;
    private final int FOUR_OF_A_KIND = 5;


    private final long[] cards;

    public CamelHand(long[] cards) {
        this.cards = cards;
    }

    public static Comparator<CamelHand> comparator() {
        return Comparator.comparingLong(CamelHand::getRanking);
    }

    private int getRanking() {
        int type = getType();
        int base = 14;
        int factor = 1;
        int ranking = 0;
        for (int i=cards.length-1; i>=0; i--) {
            ranking += factor * cards[i];
            factor *= base;
        }
        return type * factor + ranking;
    }

    public int getType() {
        Map<Long,Integer> counts = new HashMap<>();
        for (int i=0; i<cards.length; i++) {
            counts.compute(cards[i], (k,v) -> v==null?0:v+1);
        }

        boolean threeOfAKind = false;
        int numPairs = 0;
        Iterator<Long> itKeys = counts.keySet().iterator();
        while (itKeys.hasNext()) {
            long card = itKeys.next();
            if (counts.get(card) == 4) {
                return FOUR_OF_A_KIND;
            }
            if (counts.get(card) == 3) {
                threeOfAKind = true;
            }
            if (counts.get(card) == 2) {
                numPairs += 1;
            }
        }

        if (threeOfAKind && numPairs == 1) {
            return FULL_HOUSE;
        }
        if (threeOfAKind) {
            return THREE_OF_A_KIND;
        }
        if (numPairs == 2) {
            return TWO_PAIR;
        }
        if (numPairs == 1) {
            return PAIR;
        }
        return JUNK;
    }

    public static CamelHand fromStrHand(String strHand) {
        long[] hand = new long[strHand.length()];
        for (int i=0; i<strHand.length(); i++) {
            char c = strHand.charAt(i);
            long value = toValue(c);
            hand[i] = value;
        }
        return new CamelHand(hand);
    }

    private static long toValue(char c)
    {
        switch (c) {
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
                return Long.parseLong("" + c)-1;
        }
    }

}
