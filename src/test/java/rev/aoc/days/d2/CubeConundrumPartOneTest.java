package rev.aoc.days.d2;

import rev.aoc.AocSolution;
import rev.aoc.AocTest;

import java.util.List;

public class CubeConundrumPartOneTest extends AocTest<Long>
{
    @Override
    protected Long getExpected()
    {
        return (long)8;
    }

    @Override
    protected AocSolution<Long> getSolution()
    {
        return new CubeConundrumPartOne(List.of("2.1-cube-conundrum-test"));
    }
}
