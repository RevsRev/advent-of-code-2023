package rev.aoc.days.d10;

import rev.aoc.AocSolution;
import rev.aoc.AocTest;

import java.util.List;

public class PipeMazeTest extends AocTest<Long>
{
    @Override
    protected Long getExpected()
    {
        return 4l;
    }

    @Override
    protected AocSolution<Long> getSolution()
    {
        return new PipeMaze(List.of("10.1-pipe-maze-test"));
    }
}
