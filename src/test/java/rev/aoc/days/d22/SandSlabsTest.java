package rev.aoc.days.d22;

import rev.aoc.AocSolution;
import rev.aoc.AocTest;

import java.util.List;

public class SandSlabsTest extends AocTest<Long> {
    @Override
    protected Long getExpected() {
        return 5l;
    }

    @Override
    protected AocSolution<Long> getSolution() {
        return new SandSlabs(List.of("22.1-sand-slabs-test"));
    }
}
