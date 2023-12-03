package rev.aoc.days.one;

import org.junit.jupiter.api.Test;
import rev.aoc.AocSolution;
import rev.aoc.AocTest;

import java.util.List;

public class TrebuchetTest extends AocTest<Long>
{

    @Override
    protected Long getExpected()
    {
        return (long)142;
    }

    @Override
    protected AocSolution getSolution()
    {
        return new Trebuchet(List.of("1.1-trebuchet-test"));
    }
}
