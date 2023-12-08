package rev.aoc.days.d8;

import org.apache.commons.lang3.tuple.Pair;
import rev.aoc.AocSolution;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class HauntedWastelandPartOne extends HauntedWasteland
{
    public HauntedWastelandPartOne(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected Function<String, Boolean> getStartPredicate()
    {
        return s -> "AAA".equals(s);
    }

    @Override
    protected Function<String, Boolean> getEndPredicate()
    {
        return s -> "ZZZ".equals(s);
    }
}
