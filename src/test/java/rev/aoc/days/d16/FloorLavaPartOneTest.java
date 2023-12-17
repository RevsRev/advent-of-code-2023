package rev.aoc.days.d16;

import rev.aoc.AocSolution;
import rev.aoc.AocTest;

import java.util.List;

public class FloorLavaPartOneTest extends AocTest<Long>
{
    @Override
    protected Long getExpected()
    {
        return 46l;
    }

    @Override
    protected AocSolution<Long> getSolution()
    {
        return new FloorLavaPartOne(List.of("16.1-floor-lava-test"));
    }
}
