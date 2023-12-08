package rev.aoc.days.d8;

import rev.aoc.AocSolution;
import rev.aoc.AocTest;

import java.util.List;

public class HauntedWastelandPartOnePartTwoTest extends AocTest<Long>
{
    @Override
    protected Long getExpected()
    {
        return (long)6;
    }

    @Override
    protected AocSolution<Long> getSolution()
    {
        return new HauntedWastelandPartTwo(List.of("8.2-haunted-wasteland-test"));
    }
}
