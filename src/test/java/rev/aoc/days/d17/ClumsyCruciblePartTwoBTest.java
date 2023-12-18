package rev.aoc.days.d17;

import rev.aoc.AocSolution;
import rev.aoc.AocTest;

import java.util.List;

public class ClumsyCruciblePartTwoBTest extends AocTest<Long>
{
    @Override
    protected Long getExpected()
    {
        return 71l;
    }

    @Override
    protected AocSolution<Long> getSolution()
    {
        return new ClumsyCruciblePartTwo(List.of("17.2.B-clumsy-crucible-test"));
    }
}
