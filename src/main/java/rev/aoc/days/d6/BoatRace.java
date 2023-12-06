package rev.aoc.days.d6;

import org.apache.commons.lang3.tuple.Pair;
import rev.aoc.AocSolution;

import java.util.List;

public abstract class BoatRace extends AocSolution<Long>
{
    public BoatRace(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected Long solveImpl() throws Exception
    {
        List<String> lines = getOneAndOnlyResourceLines();
        Pair<long[],long[]> timeAndRecord = parse(lines);

        long[] times = timeAndRecord.getLeft();
        long[] distances = timeAndRecord.getRight();

        long ans = 1;
        for (int i=0; i<times.length; i++) {
            long time = times[i];
            long distance = distances[i];
            double l = (time - Math.sqrt(time*time - 4*distance))/2;
            double r = (time + Math.sqrt(time*time - 4*distance))/2;

            //integer solution - but we want to beat it so have to decrease the range.
            if (l==Math.floor(l)) {
                l+=1;
            }
            if (r==Math.floor(r)) {
                r-=1;
            }

            long rLong = Math.min(time,(long) Math.floor(r));
            long lLong = Math.max(0,(long) Math.ceil(l));
            if (rLong >= lLong) {
                ans *= (rLong - lLong + 1);
            }
        }
        return ans;
    }

    private Pair<long[],long[]> parse(List<String> lines)
    {
        String[] times = lines.get(0).replace("Time:","").trim().split("\\s+");
        String[] distances = lines.get(1).replace("Distance:", "").trim().split("\\s+");

        return aggregate(times, distances);
    }

    protected abstract Pair<long[], long[]> aggregate(String[] times, String[] distances);
}
