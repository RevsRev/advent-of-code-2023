package rev.aoc.days.one;

import rev.aoc.AocSolution;

import java.util.List;
import java.util.Map;

public class Trebuchet extends AocSolution<Long>
{
    public Trebuchet(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected Long solveImpl() throws Exception
    {
        Map<String,List<String>> resources = loadResources();
        return (long)0; //todo - implement
    }
}
