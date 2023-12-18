package rev.aoc.days.d7;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CamelHandJokersWildcard extends CamelHand {
  public CamelHandJokersWildcard(long[] cards) {
    super(cards);
  }

  @Override
  protected long score(long card) {
    if (card == 10) {
      return 0;
    }
    return card;
  }

  @Override
  protected int getType() {
    Map<Long, Integer> counts = new HashMap<>();
    for (int i = 0; i < cards.length; i++) {
      counts.compute(cards[i], (k, v) -> v == null ? 1 : v + 1);
    }

    int numJokers = counts.getOrDefault((long) 10, 0);
    if (numJokers == 4 || numJokers == 5) {
      return FIVE_OF_A_KIND;
    }

    boolean threeOfAKind = false;
    int numPairs = 0;
    int state = JUNK;
    Iterator<Long> itKeys = counts.keySet().iterator();
    while (itKeys.hasNext()) {
      long card = itKeys.next();
      if (card == 10) {
        continue;
      }
      if (counts.get(card) == 5) {
        state = FIVE_OF_A_KIND;
      }
      if (counts.get(card) == 4) {
        state = FOUR_OF_A_KIND;
      }
      if (counts.get(card) == 3) {
        threeOfAKind = true;
      }
      if (counts.get(card) == 2) {
        numPairs += 1;
      }
    }

    if (threeOfAKind && numPairs == 1) {
      state = FULL_HOUSE;
    } else if (threeOfAKind) {
      state = THREE_OF_A_KIND;
    } else if (numPairs == 2) {
      state = TWO_PAIR;
    } else if (numPairs == 1) {
      state = PAIR;
    }

    if (numJokers == 0) {
      return state;
    }

    if (numJokers == 1) {
      if (state == FOUR_OF_A_KIND) {
        return FIVE_OF_A_KIND;
      }
      if (state == THREE_OF_A_KIND) {
        return FOUR_OF_A_KIND;
      }
      if (state == TWO_PAIR) {
        return FULL_HOUSE;
      }
      if (state == PAIR) {
        return THREE_OF_A_KIND;
      }
      return PAIR;
    }

    if (numJokers == 2) {
      if (state == THREE_OF_A_KIND) {
        return FIVE_OF_A_KIND;
      }
      if (state == PAIR) {
        return FOUR_OF_A_KIND;
      }
      return THREE_OF_A_KIND;
    }

    if (state == PAIR) {
      return FIVE_OF_A_KIND;
    }
    return FOUR_OF_A_KIND;
  }
}
