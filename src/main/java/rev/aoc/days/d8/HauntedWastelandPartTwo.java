package rev.aoc.days.d8;

import rev.aoc.AocSolution;

import java.util.function.Function;

public class HauntedWastelandPartTwo extends HauntedWasteland
{
    public HauntedWastelandPartTwo(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected Function<String, Boolean> getStartPredicate()
    {
        return s -> s.endsWith("A");
    }

    @Override
    protected Function<String, Boolean> getEndPredicate()
    {
        return s -> s.endsWith("Z");
    }
}
