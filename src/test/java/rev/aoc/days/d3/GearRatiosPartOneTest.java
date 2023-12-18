package rev.aoc.days.d3;

import rev.aoc.AocSolution;
import rev.aoc.AocTest;

import java.util.List;

public class GearRatiosPartOneTest extends AocTest<Long>
{
    @Override
    protected Long getExpected()
    {
        return (long) 4361;
    }

    @Override
    protected AocSolution<Long> getSolution()
    {
        return new GearRatiosPartOne(List.of("3.1-gear-ratios-test"));
    }
}
