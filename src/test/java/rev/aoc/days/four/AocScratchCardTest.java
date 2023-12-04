package rev.aoc.days.four;

import rev.aoc.AocSolution;
import rev.aoc.AocTest;

import java.util.List;

public class AocScratchCardTest extends AocTest<Long>
{
    @Override
    protected Long getExpected()
    {
        return (long)13;
    }

    @Override
    protected AocSolution<Long> getSolution()
    {
        return new AocScratchCard(List.of("4.1-scratch-card-test"));
    }
}
