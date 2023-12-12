package rev.aoc.days.d10;

import rev.aoc.AocSolution;
import rev.aoc.AocTest;

import java.util.List;

public class PipeMazePartTwoBTest extends AocTest<Long>
{
    @Override
    protected Long getExpected()
    {
        return 10l;
    }

    @Override
    protected AocSolution<Long> getSolution()
    {
        return new PipeMazePartTwo(List.of("10.2.B-pipe-maze-test"));
    }
}
