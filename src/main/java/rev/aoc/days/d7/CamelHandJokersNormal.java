package rev.aoc.days.d7;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CamelHandJokersNormal extends CamelHand {
  public CamelHandJokersNormal(long[] cards) {
    super(cards);
  }

  @Override
  protected long score(long card) {
    return card;
  }

  @Override
  protected int getType() {
    Map<Long, Integer> counts = new HashMap<>();
    for (int i = 0; i < cards.length; i++) {
      counts.compute(cards[i], (k, v) -> v == null ? 1 : v + 1);
    }

    boolean threeOfAKind = false;
    int numPairs = 0;
    Iterator<Long> itKeys = counts.keySet().iterator();
    while (itKeys.hasNext()) {
      long card = itKeys.next();
      if (counts.get(card) == 5) {
        return FIVE_OF_A_KIND;
      }
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
}
