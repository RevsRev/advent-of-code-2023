package rev.aoc.days.d5;

public class SeedAlmanacPartTwo extends SeedAlmanacPartOne {
  public SeedAlmanacPartTwo(Iterable<String> resources) {
    super(resources);
  }

  @Override
  public long solveSeeds() {
    long lowestLocation = Long.MAX_VALUE;
    for (int i = 0; i < getSeeds().size(); i += 2) {
      long start = getSeeds().get(i);
      long end = start - 1 + getSeeds().get(i + 1);
      for (long j = start; j <= end; j++) {
        lowestLocation = Math.min(lowestLocation, getLocation(j));
      }
    }
    return lowestLocation;
  }
}
