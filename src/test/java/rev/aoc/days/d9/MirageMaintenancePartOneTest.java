package rev.aoc.days.d9;

import rev.aoc.AocSolution;
import rev.aoc.AocTest;

import java.util.List;

public class MirageMaintenancePartOneTest extends AocTest<Long>
{
    @Override
    protected Long getExpected()
    {
        return 114l;
    }

    @Override
    protected AocSolution<Long> getSolution()
    {
        return new MirageMaintenancePartOne(List.of("9.1-mirage-maintenance-test"));
    }
}
