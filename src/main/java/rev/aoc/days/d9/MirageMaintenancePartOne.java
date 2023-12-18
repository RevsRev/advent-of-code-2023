package rev.aoc.days.d9;

import java.util.ArrayList;
import java.util.List;

public class MirageMaintenancePartOne extends MirageMaintenance
{
    public MirageMaintenancePartOne(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected long incrementResult(long[] sequence, long result)
    {
        return result + getNextTerm(sequence);
    }

    private long getNextTerm(long[] sequence)
    {
        List<long[]> sequenceWithGaps = new ArrayList<>();
        sequenceWithGaps.add(sequence);
        while (!isConstantSequence(sequenceWithGaps.get(sequenceWithGaps.size() - 1)))
        {
            long[] gaps = sequenceWithGaps.get(sequenceWithGaps.size() - 1);
            long[] nextGaps = new long[gaps.length - 1];
            for (int i = 0; i < gaps.length - 1; i++)
            {
                nextGaps[i] = gaps[i + 1] - gaps[i];
            }
            sequenceWithGaps.add(nextGaps);
        }

        long result = 0;
        for (int i = sequenceWithGaps.size() - 1; i >= 0; i--)
        {
            long[] lastGaps = sequenceWithGaps.get(i);
            result += lastGaps[lastGaps.length - 1];
        }
        return result;
    }
}
