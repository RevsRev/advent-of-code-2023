package rev.aoc.days.d9;

import rev.aoc.AocSolution;

import java.util.ArrayList;
import java.util.List;

public abstract class MirageMaintenance extends AocSolution<Long>
{
    public MirageMaintenance(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected Long solveImpl() throws Exception
    {
        List<String> lines = getOneAndOnlyResourceLines();
        List<long[]> sequences = parse(lines);

        long result = 0;
        for (int i=0; i<sequences.size(); i++) {
            result = incrementResult(sequences.get(i), result);
        }
        return result;
    }

    protected abstract long incrementResult(long[] sequence, long result);

    protected boolean isConstantSequence(long[] sequence)
    {
        long first = sequence[0];
        for (int i=1; i<sequence.length; i++) {
            if (sequence[i] != first) {
                return false;
            }
        }
        return true;
    }

    private List<long[]> parse(List<String> lines) {
        List<long[]> retval = new ArrayList<>();
        for (int i=0; i<lines.size(); i++) {
            String line = lines.get(i);
            String[] numsStr = line.trim().split("\\s+");
            long[] sequence = new long[numsStr.length];
            for (int j=0; j<sequence.length; j++) {
                sequence[j] = Long.parseLong(numsStr[j]);
            }
            retval.add(sequence);
        }
        return retval;
    }
}
