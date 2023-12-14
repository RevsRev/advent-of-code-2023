package rev.aoc.days.d12;

import rev.aoc.AocSolution;
import rev.aoc.AocTest;

import java.util.List;

public class HotSpringsPartOneTest extends AocTest<Long>
{
    @Override
    protected Long getExpected()
    {
        return 21l;
    }

    @Override
    protected AocSolution<Long> getSolution()
    {
        return new HotSpringsPartOne(List.of("12.1-hot-springs-test"));
    }
}
