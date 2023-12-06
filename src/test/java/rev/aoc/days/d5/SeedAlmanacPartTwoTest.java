package rev.aoc.days.d5;

import rev.aoc.AocSolution;
import rev.aoc.AocTest;

import java.util.List;

public class SeedAlmanacPartTwoAltTest extends AocTest<Long>
{
    @Override
    protected Long getExpected()
    {
        return (long)0;
    }

    @Override
    protected AocSolution<Long> getSolution()
    {
        return new SeedAlmanacPartTwoAlt(List.of("5.2-seed-almanac-test"));
    }
}
