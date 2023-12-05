package rev.aoc.days.d5;

import rev.aoc.AocSolution;
import rev.aoc.AocTest;

import java.util.List;

public class SeedAlmanacTest extends AocTest<Long>
{
    @Override
    protected Long getExpected()
    {
        return (long)35;
    }

    @Override
    protected AocSolution<Long> getSolution()
    {
        return new SeedAlmanac(List.of("5.1-seed-almanac-test"));
    }
}
