package rev.aoc.days.d15;

import rev.aoc.AocSolution;
import rev.aoc.AocTest;

import java.util.List;

public class LensLibraryPartOnePartOneTest extends AocTest<Long>
{
    @Override
    protected Long getExpected()
    {
        return 1320l;
    }

    @Override
    protected AocSolution<Long> getSolution()
    {
        return new LensLibraryPartOne(List.of("15.1-lens-library-test"));
    }
}
