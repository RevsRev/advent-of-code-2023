package rev.aoc.days.d11;

import rev.aoc.AocSolution;
import rev.aoc.AocTest;

import java.util.List;

public class CosmicExpansionPartTwoTest extends AocTest<Long>
{
    @Override
    protected Long getExpected()
    {
        return 8410l;
    }

    @Override
    protected AocSolution<Long> getSolution()
    {
        return new CosmicExpansion(List.of("11.1-cosmic-expansion-test"), 100l);
    }
}
