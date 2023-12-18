package rev.aoc.days.d6;

import org.apache.commons.lang3.tuple.Pair;

public class BoatRacePartOne extends BoatRace {
  public BoatRacePartOne(Iterable<String> resources) {
    super(resources);
  }

  @Override
  protected Pair<long[], long[]> aggregate(String[] times, String[] distances) {
    long[] lTimes = new long[times.length];
    long[] lDistances = new long[distances.length];

    for (int i = 0; i < lTimes.length; i++) {
      lTimes[i] = Long.parseLong(times[i]);
      lDistances[i] = Long.parseLong(distances[i]);
    }
    return Pair.of(lTimes, lDistances);
  }
}
