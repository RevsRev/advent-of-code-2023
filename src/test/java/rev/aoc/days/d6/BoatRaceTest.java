package rev.aoc.days.d6;

import rev.aoc.AocSolution;
import rev.aoc.AocTest;

import java.util.List;

public class BoatRaceTest extends AocTest<Long>
{
    @Override
    protected Long getExpected()
    {
        return (long) 288;
    }

    @Override
    protected AocSolution<Long> getSolution()
    {
        return new BoatRace(List.of("6.1-boat-race-test"));
    }
}
