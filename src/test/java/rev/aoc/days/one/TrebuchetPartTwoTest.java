package rev.aoc.days.one;

import rev.aoc.AocSolution;
import rev.aoc.AocTest;

import java.util.List;

public class TrebuchetPartTwoTest extends AocTest<Long>
{

    @Override
    protected Long getExpected()
    {
        return (long)281;
    }

    @Override
    protected AocSolution getSolution()
    {
        return new TrebuchetPartTwo(List.of("1.2-trebuchet-test"));
    }
}
