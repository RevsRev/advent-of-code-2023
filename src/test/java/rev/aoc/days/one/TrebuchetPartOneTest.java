package rev.aoc.days.one;

import rev.aoc.AocSolution;
import rev.aoc.AocTest;

import java.util.List;

public class TrebuchetPartOneTest extends AocTest<Long>
{

    @Override
    protected Long getExpected()
    {
        return (long)142;
    }

    @Override
    protected AocSolution getSolution()
    {
        return new TrebuchetPartOne(List.of("1.1-trebuchet-test"));
    }
}
