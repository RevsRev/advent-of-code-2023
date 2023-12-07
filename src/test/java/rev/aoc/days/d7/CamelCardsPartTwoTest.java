package rev.aoc.days.d7;

import rev.aoc.AocSolution;
import rev.aoc.AocTest;

import java.util.List;

public class CamelCardsPartTwoTest extends AocTest<Long>
{
    @Override
    protected Long getExpected()
    {
        return (long)5905;
    }

    @Override
    protected AocSolution<Long> getSolution()
    {
        return new CamelCards(List.of("7.2-camel-cards-test"), true);
    }
}
