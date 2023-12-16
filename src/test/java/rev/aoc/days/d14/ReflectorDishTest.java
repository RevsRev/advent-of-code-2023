package rev.aoc.days.d14;

import rev.aoc.AocSolution;
import rev.aoc.AocTest;

import java.util.List;

public class ReflectorDishTest extends AocTest<Long>
{
    @Override
    protected Long getExpected()
    {
        return 136l;
    }

    @Override
    protected AocSolution<Long> getSolution()
    {
        return new ReflectorDish(List.of("14.1-reflector-dish"));
    }
}
