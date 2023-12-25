package rev.aoc.days.d22;

import rev.aoc.AocSolution;
import rev.aoc.AocTest;

import java.util.List;

public class SandSlabsPartTwoTest extends AocTest<Long> {
    @Override
    protected Long getExpected() {
        return 7l;
    }

    @Override
    protected AocSolution<Long> getSolution() {
        return new SandSlabsPartTwo(List.of("22.1-sand-slabs-test"));
    }
}
