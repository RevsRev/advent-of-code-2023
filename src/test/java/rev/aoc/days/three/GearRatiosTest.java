package rev.aoc.days.three;

import rev.aoc.AocSolution;
import rev.aoc.AocTest;

import java.util.List;

public class GearRatiosTest extends AocTest<Long>
{
    @Override
    protected Long getExpected()
    {
        return (long)4361;
    }

    @Override
    protected AocSolution<Long> getSolution()
    {
        return new GearRatios(List.of("3.1-gear-ratios-test"));
    }
}
