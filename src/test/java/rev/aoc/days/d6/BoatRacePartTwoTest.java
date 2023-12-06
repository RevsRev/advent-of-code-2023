package rev.aoc.days.d6;

import rev.aoc.AocSolution;
import rev.aoc.AocTest;

import java.util.List;

public class BoatRacePartTwoTest extends AocTest<Long>
{
    @Override
    protected Long getExpected()
    {
        return (long) 288;
    }

    @Override
    protected AocSolution<Long> getSolution()
    {
        return new BoatRacePartTwo(List.of("6.2-boat-race-test"));
    }
}
