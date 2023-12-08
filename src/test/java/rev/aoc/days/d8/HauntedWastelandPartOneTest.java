package rev.aoc.days.d8;

import rev.aoc.AocSolution;
import rev.aoc.AocTest;

import java.util.List;

public class HauntedWastelandPartOneTest extends AocTest<Long>
{
    @Override
    protected Long getExpected()
    {
        return (long)6;
    }

    @Override
    protected AocSolution<Long> getSolution()
    {
        return new HauntedWasteland(List.of("8.1-haunted-wasteland-test"));
    }
}
