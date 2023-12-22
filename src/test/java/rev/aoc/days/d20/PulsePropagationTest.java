package rev.aoc.days.d20;

import rev.aoc.AocSolution;
import rev.aoc.AocTest;

import java.util.List;

public class PulsePropagationTest extends AocTest<Long>
{
    @Override
    protected Long getExpected()
    {
        return 32000000l;
    }

    @Override
    protected AocSolution<Long> getSolution()
    {
        return new PulsePropagation(List.of("20.1-pulse-propagation-test"));
    }
}
