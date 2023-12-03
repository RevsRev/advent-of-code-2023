package rev.aoc.days.two;

import rev.aoc.AocSolution;
import rev.aoc.AocTest;
import rev.aoc.days.one.TrebuchetPartOne;

import java.util.List;

public class CubeConundrumTest extends AocTest<Long>
{
    @Override
    protected Long getExpected()
    {
        return (long)8;
    }

    @Override
    protected AocSolution<Long> getSolution()
    {
        return new CubeConundrum(List.of("2.1-cube-conundrum-test"));
    }
}
