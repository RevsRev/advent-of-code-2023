package rev.aoc.days.d17;

import rev.aoc.AocSolution;
import rev.aoc.AocTest;

import java.util.List;

public class ClumsyCrucibleTest extends AocTest<Long>
{
    @Override
    protected Long getExpected()
    {
        return 102l;
    }

    @Override
    protected AocSolution<Long> getSolution()
    {
        return new ClumsyCrucible(List.of("17.1-clumsy-crucible-test"));
    }
}
