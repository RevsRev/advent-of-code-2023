package rev.aoc.days.d17;

import rev.aoc.AocSolution;
import rev.aoc.AocTest;

import java.util.List;

public class ClumsyCruciblePartTwoATest extends AocTest<Long>
{
    @Override
    protected Long getExpected()
    {
        return 94l;
    }

    @Override
    protected AocSolution<Long> getSolution()
    {
        return new ClumsyCruciblePartTwo(List.of("17.2.A-clumsy-crucible-test"));
    }
}
