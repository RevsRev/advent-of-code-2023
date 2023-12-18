package rev.aoc.days.d6;

import org.apache.commons.lang3.tuple.Pair;

public class BoatRacePartTwo extends BoatRace {
  public BoatRacePartTwo(Iterable<String> resources) {
    super(resources);
  }

  @Override
  protected Pair<long[], long[]> aggregate(String[] times, String[] distances) {
    long[] time = new long[1];
    long[] distance = new long[1];

    time[0] = Long.parseLong(String.join("", times));
    distance[0] = Long.parseLong(String.join("", distances));

    return Pair.of(time, distance);
  }
}
