package rev.aoc.days.two;

import rev.aoc.AocSolution;
import rev.aoc.AocTest;

import java.util.List;

public class CubeConundrumPartTwoTest extends AocTest<Long>
{
    @Override
    protected Long getExpected()
    {
        return (long)2286;
    }

    @Override
    protected AocSolution<Long> getSolution()
    {
        return new CubeConundrumPartOne(List.of("2.2-cube-conundrum-test"));
    }
}
