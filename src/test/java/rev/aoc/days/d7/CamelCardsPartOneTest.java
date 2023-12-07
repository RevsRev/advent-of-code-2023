package rev.aoc.days.d7;

import rev.aoc.AocSolution;
import rev.aoc.AocTest;

import java.util.List;

public class CamelCardsPartOneTest extends AocTest<Long>
{
    @Override
    protected Long getExpected()
    {
        return (long)6440;
    }

    @Override
    protected AocSolution<Long> getSolution()
    {
        return new CamelCards(List.of("7.1-camel-cards-test"));
    }
}
